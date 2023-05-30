package com.oss6team.emptylecturefinder

import android.os.Bundle
import android.util.TypedValue
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

class Favorites : AppCompatActivity() {

    private lateinit var tableLayout: TableLayout
    private lateinit var deleteButton: Button
    private val checkboxMap = mutableMapOf<CheckBox, LectureFound.Classroom>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        tableLayout = findViewById(R.id.tableLayout)
        deleteButton = findViewById(R.id.deleteButton)

        val file = File(getExternalFilesDir(null), "favorites.txt")
        val data = readDataFromFile(file)

        deleteButton.setOnClickListener {
            deleteCheckedRows()
        }

        displayDataInTable(data)
    }

    private fun readDataFromFile(file: File): List<LectureFound.Classroom> {
        val inputStream = file.inputStream()
        val reader = BufferedReader(InputStreamReader(inputStream))
        val data = mutableListOf<LectureFound.Classroom>()

        reader.useLines { lines ->
            lines.forEach {
                val parts = it.split(", ")
                if (parts.size >= 3) {
                    val classroom = parts[0]
                    val day = parts[1]
                    val times = if (parts[2].isNotEmpty()) parts[2].split(" ").map { it.toInt() } else listOf()
                    data.add(LectureFound.Classroom(classroom, day, times))
                }
            }
        }

        return data
    }

    private fun displayDataInTable(data: List<LectureFound.Classroom>) {
        val headerRow = createHeaderRow()
        tableLayout.addView(headerRow)

        data.forEach { classroom ->
            if (classroom.times.isNotEmpty()) {
                val row = createDataRow(classroom)
                tableLayout.addView(row)
                checkboxMap[row.getChildAt(0) as CheckBox] = classroom
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

    private fun createDataRow(classroom: LectureFound.Classroom): TableRow {
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

    private fun Int.dpToPx(): Int {
        return (this * resources.displayMetrics.density).toInt()
    }

    private fun deleteCheckedRows() {
        val classroomsToRemove = mutableListOf<LectureFound.Classroom>()
        for ((checkbox, classroom) in checkboxMap) {
            if (checkbox.isChecked) {
                tableLayout.removeView(checkbox.parent as View)
                classroomsToRemove.add(classroom)
            }
        }

        removeClassroomsFromFile(classroomsToRemove)
        classroomsToRemove.forEach { checkboxMap.values.remove(it) }

        Toast.makeText(this, R.string.Delete_Complete, Toast.LENGTH_SHORT).show()
    }

    private fun removeClassroomsFromFile(classroomsToRemove: List<LectureFound.Classroom>) {
        val file = File(getExternalFilesDir(null), "favorites.txt")
        val remainingClassrooms = readDataFromFile(file).filterNot { it in classroomsToRemove }
        file.writeText(remainingClassrooms.joinToString("\n") {
            "${it.classroom}, ${it.day}, ${it.times.joinToString(" ")}"
        })
    }

}
