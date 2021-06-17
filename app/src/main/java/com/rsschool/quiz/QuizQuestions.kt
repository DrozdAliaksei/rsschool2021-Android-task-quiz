package com.rsschool.quiz

enum class QuizQuestions(
    val title: String,
    val question: String,
    val answers: Array<String>,
    val theme: Int,
    val indexOfTrueAnswer: Int
) {
    One(
        "Первый вопрос",
        "Первый вопрос",
        arrayOf(
            "1", "2", "3", "4", "5"
        ),
        R.style.Theme_Quiz_Four,
        2
    ),
    Two(
        "Второй вопрос",
        "Первый вопрос",
        arrayOf(
            "w", "2w", "3w", "4w", "5w"
        ),
        R.style.Theme_Quiz_First,
        4
    ),
    Three(
        "Третий вопрос",
        "Первый вопрос",
        arrayOf(
            "1", "2", "3", "4", "5"
        ),
        R.style.Theme_Quiz_Second,
        1
    ),
    Four(
        "Четвертый вопрос",
        "Первый вопрос",
        arrayOf(
            "1", "2", "3", "4", "5"
        ),
        R.style.Theme_Quiz_Third,
        0
    ),
    Five(
        "Пятый вопрос",
        "Первый вопрос",
        arrayOf(
            "1", "2", "3", "4", "5"
        ),
        R.style.Theme_Quiz,
        4
    )
}
