package com.oss6team.emptylecturefinder

import android.os.Bundle
import android.util.TypedValue
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

class Favorites : AppCompatActivity() {

    private lateinit var tableLayout: TableLayout
    private lateinit var deleteButton: Button
    private val checkboxMap = mutableMapOf<CheckBox, LectureFound.Classroom>()
    private lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        // 애드몹 광고 불러오기
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        // 테이블 레이아웃과 삭제 버튼을 초기화
        tableLayout = findViewById(R.id.tableLayout)
        deleteButton = findViewById(R.id.deleteButton)

        // 즐겨찾기 데이터를 저장한 파일을 찾아 없으면 새로 생성
        val file = File(getExternalFilesDir(null), "favorites.txt")
        if (!file.exists()) {
            file.createNewFile()
        }
        val data = readDataFromFile(file)

        // 삭제 버튼 클릭 리스너 설정: 체크된 행 삭제
        deleteButton.setOnClickListener {
            deleteCheckedRows()
        }

        // 테이블에 데이터 표시
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

    // 데이터를 테이블 형태로 화면에 표시
    private fun displayDataInTable(data: List<LectureFound.Classroom>) {
        // 먼저 헤더 행을 생성하고 테이블 레이아웃에 추가
        val headerRow = createHeaderRow()
        tableLayout.addView(headerRow)

        // 각각의 데이터를 순회하면서 강의 시간이 있는 경우에만 행을 생성하고 추가
        // 생성된 행의 체크박스와 강의실 정보를 맵에 저장
        data.forEach { classroom ->
            if (classroom.times.isNotEmpty()) {
                val row = createDataRow(classroom)
                tableLayout.addView(row)
                checkboxMap[row.getChildAt(0) as CheckBox] = classroom
            }
        }
    }

    // 테이블의 헤더 행을 생성
    private fun createHeaderRow(): TableRow {
        val headerRow = TableRow(this) // TableRow 객체를 생성
        val layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)
        headerRow.layoutParams = layoutParams // 레이아웃 파라미터를 설정

        // 각 열 헤더에 해당하는 텍스트 뷰를 생성하고 행에 추가
        val columnHeaderCheckBox = createTableHeaderTextView(R.string.Found_Check)
        val columnHeaderClassroom = createTableHeaderTextView(R.string.Found_Classroom)
        val columnHeaderDay = createTableHeaderTextView(R.string.Found_Day)
        val columnHeaderTimes = createTableHeaderTextView(R.string.Found_Times)

        headerRow.addView(columnHeaderCheckBox)
        headerRow.addView(columnHeaderClassroom)
        headerRow.addView(columnHeaderDay)
        headerRow.addView(columnHeaderTimes)

        return headerRow // 생성된 행을 반환
    }

    // 이 함수는 테이블 헤더의 텍스트 뷰를 생성
    private fun createTableHeaderTextView(textResource: Int): TextView {
        val contextThemeWrapper = ContextThemeWrapper(this, R.style.HeaderViewStyle) // 테마를 적용한 컨텍스트
        val textView = TextView(contextThemeWrapper) // TextView 객체를 생성하고 텍스트를 설정
        textView.text = getString(textResource)

        // 레이아웃 파라미터를 생성하고 마진을 설정
        val layoutParams = TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(2.dpToPx(), 2.dpToPx(), 2.dpToPx(), 2.dpToPx())
        textView.layoutParams = layoutParams // 레이아웃 파라미터를 텍스트 뷰에 설정

        return textView // 생성된 텍스트 뷰를 반환
    }

    // 데이터 행을 생성
    private fun createDataRow(classroom: LectureFound.Classroom): TableRow {
        val contextThemeWrapper = ContextThemeWrapper(this, R.style.TableRowStyle) // 테마를 적용한 컨텍스트
        val row = TableRow(contextThemeWrapper) // TableRow 객체를 생성

        // 체크박스를 생성하고 행에 추가
        val checkbox = CheckBox(contextThemeWrapper)
        row.addView(checkbox)

        val layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)
        row.layoutParams = layoutParams // 레이아웃 파라미터를 설정

        // 각 데이터 필드에 해당하는 텍스트 뷰를 생성하고 행에 추가
        val textViewClassroom = createDataTextView(classroom.classroom)
        row.addView(textViewClassroom)
        val textViewDay = createDataTextView(classroom.day)
        row.addView(textViewDay)
        val textViewTimes = createDataTextView(classroom.times.joinToString(", "))
        row.addView(textViewTimes)

        return row // 생성된 행을 반환
    }

    // 텍스트 뷰를 생성하고 주어진 텍스트를 설정
    private fun createDataTextView(text: String): TextView {
        val contextThemeWrapper = ContextThemeWrapper(this, R.style.TextViewStyle) // 테마를 적용한 컨텍스트
        val textView = TextView(contextThemeWrapper) // TextView 객체를 생성
        textView.text = text // 텍스트를 설정
        return textView // 생성된 텍스트 뷰를 반환
    }

    // dp 단위를 픽셀 단위로 변환
    private fun Int.dpToPx(): Int {
        return (this * resources.displayMetrics.density).toInt()
    }

    // 선택된 행 삭제
    private fun deleteCheckedRows() {
        val classroomsToRemove = mutableListOf<LectureFound.Classroom>() // 삭제할 강의실을 저장할 리스트
        // 체크박스와 강의실 맵을 순회하면서 체크된 항목이 있으면 테이블에서 해당 행을 삭제하고 리스트에 추가
        for ((checkbox, classroom) in checkboxMap) {
            if (checkbox.isChecked) {
                tableLayout.removeView(checkbox.parent as View)
                classroomsToRemove.add(classroom)
            }
        }

        // 파일에서 선택된 강의실을 삭제하고 맵에서도 삭제
        removeClassroomsFromFile(classroomsToRemove)
        classroomsToRemove.forEach { checkboxMap.values.remove(it) }

        // 삭제 완료 메시지를 표시
        Toast.makeText(this, R.string.Delete_Complete, Toast.LENGTH_SHORT).show()
    }

    // 파일에서 주어진 강의실 삭제
    private fun removeClassroomsFromFile(classroomsToRemove: List<LectureFound.Classroom>) {
        val file = File(getExternalFilesDir(null), "favorites.txt") // 파일을 가져옴
        // 파일에서 데이터를 읽고 주어진 강의실을 제외한 나머지 데이터를 다시 파일에 씀
        val remainingClassrooms = readDataFromFile(file).filterNot { it in classroomsToRemove }
        file.writeText(remainingClassrooms.joinToString("\n") {
            "${it.classroom}, ${it.day}, ${it.times.joinToString(" ")}"
        })
    }


}
