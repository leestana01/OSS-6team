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
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader


class LectureFound : AppCompatActivity() {

    // UI 구성요소 선언
    private lateinit var tableLayout: TableLayout
    private lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lecture_found)

        // 광고를 로드합니다.
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        // 테이블 레이아웃 참조
        tableLayout = findViewById(R.id.tableLayout)

        // 저장 버튼에 대한 클릭 리스너 설정
        val saveButton: Button = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            saveCheckedItems()
        }

        // 인텐트로부터 데이터 추출
        val campus = intent.getIntExtra("campus",0)
        val building = intent.getStringExtra("building")
        val lecture = intent.getIntegerArrayListExtra("lecture") ?: arrayListOf()

        // 캠퍼스에 따른 데이터 파일 선택
        val fileResId = if (campus == 0) R.raw.seoul_remaining else R.raw.global_remaining

        // 파일에서 데이터 읽기
        val data = readDataFromFile(fileResId)

        // 건물에 따라 데이터 필터링
        val filteredByBuilding = if (building != null) {
            data.filter { it.classroom.startsWith(building.toString()) }
        } else {
            data
        }

        // 강의 시간에 따라 데이터 필터링
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

        // 데이터를 테이블에 표시
        displayDataInTable(filteredByLecture)
    }

    // 파일에서 데이터를 읽어 Classroom 객체 목록을 생성하는 함수
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

    // 데이터를 테이블에 표시하는 함수
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

    // 테이블 헤더 로우를 생성하는 함수
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

    // 테이블 헤더 텍스트 뷰를 생성하는 함수
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

    // 데이터 로우를 생성하는 함수
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

    // 데이터 텍스트 뷰를 생성하는 함수
    private fun createDataTextView(text: String): TextView {
        val contextThemeWrapper = ContextThemeWrapper(this, R.style.TextViewStyle)
        val textView = TextView(contextThemeWrapper)
        textView.text = text
        return textView
    }

    // 체크된 항목을 저장하는 함수
    private fun saveCheckedItems() {
        val dataToSave = mutableListOf<Classroom>()

        // 테이블의 모든 행을 순회
        for (i in 0 until tableLayout.childCount) {
            val row = tableLayout.getChildAt(i) as? TableRow
            val checkbox = row?.getChildAt(0) as? CheckBox // 체크박스가 첫 번째 자식이라고 가정
            if (checkbox?.isChecked == true) {
                // 행의 다른 뷰에서 데이터를 가져와 Classroom 객체를 생성
                val classroomName = (row.getChildAt(1) as? TextView)?.text.toString()
                val classroomDay = (row.getChildAt(2) as? TextView)?.text.toString()
                val classroomTimes = (row.getChildAt(3) as? TextView)?.text.toString()
                    .split(", ").map { it.toInt() }
                dataToSave.add(Classroom(classroomName, classroomDay, classroomTimes))
            }
        }

        // 데이터를 파일에 저장
        saveDataToFile(dataToSave)

        // 토스트 메시지를 보여줌
        Toast.makeText(this, R.string.Save_Complete, Toast.LENGTH_SHORT).show()

        // 새로운 액티비티를 시작
        val intent = Intent(this, Favorites::class.java)
        startActivity(intent)
    }


    // 파일에 데이터를 저장하는 함수
    private fun saveDataToFile(data: List<Classroom>) {
        val file = File(getExternalFilesDir(null), "favorites.txt")
        file.writeText(data.joinToString("\n") { "${it.classroom}, ${it.day}, ${it.times.joinToString(" ")}" })
    }

    // dp 값을 px 값으로 변환하는 확장 함수
    private fun Int.dpToPx(): Int {
        return (this * resources.displayMetrics.density).toInt()
    }

    // Classroom 데이터 클래스 선언
    data class Classroom(val classroom: String, val day: String, val times: List<Int>)
}

