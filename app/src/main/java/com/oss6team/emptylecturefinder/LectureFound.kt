package com.oss6team.emptylecturefinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        val campus = intent.getIntExtra("campus", 0)
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
        data.forEach { classroom ->
            if (classroom.times.isNotEmpty()) { // 추가된 부분
                val row = TableRow(this)
                val layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)
                row.layoutParams = layoutParams

                val textViewClassroom = TextView(this)
                textViewClassroom.text = classroom.classroom
                row.addView(textViewClassroom)

                val textViewDay = TextView(this)
                textViewDay.text = classroom.day
                row.addView(textViewDay)

                val textViewTimes = TextView(this)
                textViewTimes.text = classroom.times.joinToString(", ")
                row.addView(textViewTimes)

                tableLayout.addView(row)
            }
        }
    }


    data class Classroom(val classroom: String, val day: String, val times: List<Int>)
}
