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
        "6/2(1+2) = ?",
        arrayOf(
            "-1", "1", "9", "0", "5"
        ),
        R.style.Theme_Quiz_Second,
        2
    ),
    Two(
        "Второй вопрос",
        """1 + 4 = 5
            | 2 + 5 = 12
            | 3 + 6 = 21
            | 8 + 11 = ?
        """.trimMargin(),
        arrayOf(
            "12", "40", "96", "40 или 96", "96 или 12"
        ),
        R.style.Theme_Quiz_Third,
        3
    ),
    Three(
        "Третий вопрос",
        "Какое расшинение имеет Android приложение?",
        arrayOf(
            ".zip", ".exe", ".apk", ".sh", ".exe"
        ),
        R.style.Theme_Quiz_Four,
        2
    ),
    Four(
        "Четвертый вопрос",
        "Какой класс в Kotlin можно назвать эквивалентом ключевого слова void в Java?",
        arrayOf(
            "Any()", "Void()", "Unit()", "Nothing()", "Something()"
        ),
        R.style.Theme_Quiz_Five,
        2
    ),
    Five(
        "Пятый вопрос",
        "Какой интерфейс гарантирует отсутствие дубликатов и доступ к элементам в натуральном порядке?",
        arrayOf(
            "List", "Map", "Deque", "HashMap", "Set"
        ),
        R.style.Theme_Quiz_First,
        4
    )
}
