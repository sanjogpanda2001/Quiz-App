package com.example.quizapp

object constants{
    const val USER_NAME:String="user_name"
    const val  TOTAL_QUESTIONS="total_questions"
    const val  CORRECT_ANSWERS="correct_answrs"

    fun getQuestions(): ArrayList<Questioned>{
        val questionsList=ArrayList<Questioned>()
        val que1=Questioned(1,
        "Brand of the logo",
        R.drawable.adidas,
        "Nike",
        "Reebok",
        "Adidas",
        "Puma",
        3)
        questionsList.add(que1)

        val que2=Questioned(2,
            "Brand of the logo",
            R.drawable.channel,
            "Chanel",
            "Gucci",
            "Armani",
            "Stylex",
            1)
        questionsList.add(que2)

        val que3=Questioned(3,
            "Brand of the logo",
            R.drawable.louis_vuitton,
            "Highlander",
            "Van Heusen",
            "Integrity",
            "Louis Vuitton",
            4)
        questionsList.add(que3)

        val que4=Questioned(4,
            "Brand of the logo",
            R.drawable.toyota,
            "Davidley Harleyson",
            "Toyota",
            "Maruti",
            "Hyundai",
            2)
        questionsList.add(que4)
        return questionsList
    }

}