package com.oss6team.emptylecturefinder

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.widget.*
import androidx.annotation.RequiresApi
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader


class LectureFound : AppCompatActivity() {

    private lateinit var tableLayout: TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lecture_found)

        tableLayout = findViewById(R.id.tableLayout)

        val saveButton: Button = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            saveCheckedItems()
        }

        val campus = intent.getIntExtra("campus",0)
        val building = intent.getStringExtra("building")
        val lecture = intent.getIntegerArrayListExtra("lecture") ?: arrayListOf()

        val fileResId = if (campus == 0) R.raw.seoul_remaining else R.raw.global_remaining

        val data = readDataFromFile(fileResId)

        val filteredByBuilding = if (building != null) {
            data.filter { it.classroom.startsWith(building.toString()) }
        } else {
            data
        }

        val filteredByLecture = if (lecture.isNotEmpty()) {
            val checkedTimes = lecture.mapIndexed { index, isChecked ->
                if (isChecked == 1) index + 1 else null
            }.filterNotNull()

            val andOr = intent.getIntExtra("andOr", 1)

            if (andOr == 0) {
                filteredByBuilding.filter { classroom ->
                    checkedTimes.all { time -> classroom.times.contains(time) }
                }
            } else {
                filteredByBuilding.filter { classroom ->
                    checkedTimes.any { time -> classroom.times.contains(time) }
                }
            }
        } else {
            filteredByBuilding
        }

        displayDataInTable(filteredByLecture)
    }


    private fun readDataFromFile(resId: Int): List<Classroom> {
        val inputStream = resources.openRawResource(resId)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val data = mutableListOf<Classroom>()

        reader.useLines { lines ->
            lines.forEach {
                val parts = it.split(", ")
                if (parts.size >= 3) {
                    val classroom = parts[0]
                    val day = parts[1]
                    val times = if (parts[2].isNotEmpty()) parts[2].split(" ").map { it.toInt() } else listOf()
                    data.add(Classroom(classroom, day, times))
                }
            }
        }

        return data
    }

    private fun displayDataInTable(data: List<Classroom>) {
        val headerRow = createHeaderRow()
        tableLayout.addView(headerRow)

        data.forEach { classroom ->
            if (classroom.times.isNotEmpty()) {
                val row = createDataRow(classroom)
                tableLayout.addView(row)
            }
        }
    }

    private fun createHeaderRow(): TableRow {
        val headerRow = TableRow(this)
        val layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)
        headerRow.layoutParams = layoutParams

        val columnHeaderCheckBox = createTableHeaderTextView(R.string.Found_Check)
        val columnHeaderClassroom = createTableHeaderTextView(R.string.Found_Classroom)
        val columnHeaderDay = createTableHeaderTextView(R.string.Found_Day)
        val columnHeaderTimes = createTableHeaderTextView(R.string.Found_Times)

        headerRow.addView(columnHeaderCheckBox)
        headerRow.addView(columnHeaderClassroom)
        headerRow.addView(columnHeaderDay)
        headerRow.addView(columnHeaderTimes)

        return headerRow
    }

    private fun createTableHeaderTextView(textResource: Int): TextView {
        val contextThemeWrapper = ContextThemeWrapper(this, R.style.HeaderViewStyle)
        val textView = TextView(contextThemeWrapper)
        textView.text = getString(textResource)

        val layoutParams = TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(2.dpToPx(), 2.dpToPx(), 2.dpToPx(), 2.dpToPx())
        textView.layoutParams = layoutParams

        return textView
    }

    private fun createDataRow(classroom: Classroom): TableRow {
        val contextThemeWrapper = ContextThemeWrapper(this, R.style.TableRowStyle)

        val row = TableRow(contextThemeWrapper)

        val checkbox = CheckBox(contextThemeWrapper)
        row.addView(checkbox)

        val layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)
        row.layoutParams = layoutParams

        val textViewClassroom = createDataTextView(classroom.classroom)
        row.addView(textViewClassroom)

        val textViewDay = createDataTextView(classroom.day)
        row.addView(textViewDay)

        val textViewTimes = createDataTextView(classroom.times.joinToString(", "))
        row.addView(textViewTimes)

        return row
    }

    private fun createDataTextView(text: String): TextView {
        val contextThemeWrapper = ContextThemeWrapper(this, R.style.TextViewStyle)
        val textView = TextView(contextThemeWrapper)
        textView.text = text
        return textView
    }

    private fun saveCheckedItems() {
        val dataToSave = mutableListOf<Classroom>()

        // Traverse table rows
        for (i in 0 until tableLayout.childCount) {
            val row = tableLayout.getChildAt(i) as? TableRow
            val checkbox = row?.getChildAt(0) as? CheckBox // assuming checkbox is the first child
            if (checkbox?.isChecked == true) {
                // Get data from other views in the row and create Classroom object
                val classroomName = (row.getChildAt(1) as? TextView)?.text.toString()
                val classroomDay = (row.getChildAt(2) as? TextView)?.text.toString()
                val classroomTimes = (row.getChildAt(3) as? TextView)?.text.toString()
                    .split(", ").map { it.toInt() }
                dataToSave.add(Classroom(classroomName, classroomDay, classroomTimes))
            }
        }

        // Save data to file
        saveDataToFile(dataToSave)

        // Show toast message
        Toast.makeText(this, R.string.Save_Complete, Toast.LENGTH_SHORT).show()

        // Launch new activity
        val intent = Intent(this, Favorites::class.java)
        startActivity(intent)
    }

    private fun saveDataToFile(data: List<Classroom>) {
        val file = File(getExternalFilesDir(null), "favorites.txt")
        file.writeText(data.joinToString("\n") { "${it.classroom}, ${it.day}, ${it.times.joinToString(" ")}" })
    }

    private fun Int.dpToPx(): Int {
        return (this * resources.displayMetrics.density).toInt()
    }

    data class Classroom(val classroom: String, val day: String, val times: List<Int>)
}
