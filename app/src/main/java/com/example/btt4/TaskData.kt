package com.example.btt4

data class TaskItem(
    val id: Int,
    val title: String,
    val category: String,
    val time: String,
    val isCompleted: Boolean = false
) {
    fun toggle(): TaskItem = copy(isCompleted = !isCompleted)
}

data class ListTask(
    val id: Int,
    val title: String,
    val category: String,
    val dueText: String,
    val isDone: Boolean = false,
    val priority: String = "medium",
    val deadlineText: String = dueText,
    val remindText: String = "Trước 15 phút",
    val repeatText: String = "Không",
    val note: String = "",
    val subtasks: List<TaskSubtask> = emptyList(),
    val date: String = "",
    val time: String = dueText.substringAfterLast(", "),
    val dayChoice: String = dueText.substringBefore(", ")
)

data class TaskSubtask(val id: Int, val title: String, val isDone: Boolean = false)

data class CategoryData(val title: String, val remainingCount: Int, val isStudy: Boolean)

data class DayProgressData(
    val day: String,
    val heightDp: Int,
    val isHighlighted: Boolean = false,
    val isSecondary: Boolean = false
)

fun dateForDayChoice(choice: String): String {
    val calendar = java.util.Calendar.getInstance()
    when (choice) {
        "Ngày mai" -> calendar.add(java.util.Calendar.DAY_OF_YEAR, 1)
        "Tuần này" -> {
            val daysUntilSunday = (java.util.Calendar.SUNDAY - calendar.get(java.util.Calendar.DAY_OF_WEEK) + 7) % 7
            calendar.add(java.util.Calendar.DAY_OF_YEAR, if (daysUntilSunday == 0) 7 else daysUntilSunday)
        }
    }
    return java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).format(calendar.time)
}
