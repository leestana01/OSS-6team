package com.oss6team.emptylecturefinder

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStreamReader


class LectureFound : AppCompatActivity() {

    private lateinit var tableLayout: TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lecture_found)

        tableLayout = findViewById(R.id.tableLayout)

        val campus = intent.getStringExtra("campus")
        val building = intent.getStringExtra("building")
        val lecture = intent.getIntegerArrayListExtra("lecture") ?: arrayListOf()

        val fileResId = if (campus == "0") R.raw.seoul_remaining else R.raw.global_remaining

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

        val columnHeaderClassroom = createTableHeaderTextView("Classroom")
        headerRow.addView(columnHeaderClassroom)

        val columnHeaderDay = createTableHeaderTextView("Day")
        headerRow.addView(columnHeaderDay)

        val columnHeaderTimes = createTableHeaderTextView("Times")
        headerRow.addView(columnHeaderTimes)

        return headerRow
    }

    private fun createTableHeaderTextView(text: String): TextView {
        val textView = TextView(this)
        textView.text = text
        textView.setPadding(5.dpToPx(), 5.dpToPx(), 5.dpToPx(), 5.dpToPx())
        textView.setTextColor(Color.WHITE)
        textView.setTypeface(null, Typeface.BOLD)
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20.0f)
        val layoutParams = TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(2.dpToPx(), 2.dpToPx(), 2.dpToPx(), 2.dpToPx())
        textView.layoutParams = layoutParams

        textView.setBackgroundResource(R.drawable.cell_background)

        return textView
    }

    private fun createDataRow(classroom: Classroom): TableRow {
        val row = TableRow(this)
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
        val textView = TextView(this)
        textView.text = text
        textView.setPadding(5.dpToPx(), 5.dpToPx(), 5.dpToPx(), 5.dpToPx())
        return textView
    }

    private fun Int.dpToPx(): Int {
        return (this * resources.displayMetrics.density).toInt()
    }


    data class Classroom(val classroom: String, val day: String, val times: List<Int>)
}
