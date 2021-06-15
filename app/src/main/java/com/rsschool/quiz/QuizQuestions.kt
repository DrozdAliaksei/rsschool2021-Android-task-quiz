package com.rsschool.quiz

enum class QuizQuestions(
    val title: String,
    val question: String,
    val answers: Array<String>,
    val indexOfTrueAnswer: Int
) {
    One(
        "Первый вопрос",
        "Первый вопрос",
        arrayOf(
            "1", "2", "3", "4", "5"
        ),
        3
    ),
    Two(
        "Второй вопрос",
        "Первый вопрос",
        arrayOf(
            "w", "2w", "3w", "4w", "5w"
        ),
        5
    ),
    Three(
        "Третий вопрос",
        "Первый вопрос",
        arrayOf(
            "1", "2", "3", "4", "5"
        ),
        2
    ),
    Four(
        "Четвертый вопрос",
        "Первый вопрос",
        arrayOf(
            "1", "2", "3", "4", "5"
        ),
        1
    ),
    Five(
        "Пятый вопрос",
        "Первый вопрос",
        arrayOf(
            "1", "2", "3", "4", "5"
        ),
        5
    )
}
