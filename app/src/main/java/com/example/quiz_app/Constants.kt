package com.example.quiz_app

object Constants {

    const val USER_NAME : String = "user_name"
    const val TOTAL_QUESTIONS : String = "total_questions"
    const val CORRECT_ANSWERS : String = "correct_answers"

    fun getQuestions():ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        // Astrologian
        val que1 = Question(
            1, "What job is this?",
            R.drawable.ic_astrologian,
            "White Mage", "Scholar",
            "Bard", "Astrologian",
            4
        )
        questionsList.add(que1)

        // Monk
        val que2 = Question(
            2, "What job is this?",
            R.drawable.ic_monk,
            "Monk", "Dark Knight",
            "Samurai", "Ninja",
            1
        )
        questionsList.add(que2)

        // Red Mage
        val que3 = Question(
            3, "What job is this?",
            R.drawable.ic_red_mage,
            "White Mage", "Blue Mage",
            "Red Mage", "Black Mage",
            3
        )
        questionsList.add(que3)

        // Dark Knight
        val que4 = Question(
            4, "What job is this?",
            R.drawable.ic_dark_knight,
            "Paladin", "Dark Knight",
            "Red Mage", "Gunbreaker",
            2
        )
        questionsList.add(que4)

        // Machinist
        val que5 = Question(
            5, "What job is this?",
            R.drawable.ic_machinist,
            "Machinist", "Gunbreaker",
            "Dancer", "Sage",
            1
        )
        questionsList.add(que5)

        // Dragoon
        val que6 = Question(
            6, "What job is this?",
            R.drawable.ic_dragoon,
            "Dancer", "Dark Knight",
            "Dragoon", "Summoner",
            3
        )
        questionsList.add(que6)

        // White Mage
        val que7 = Question(
            7, "What job is this?",
            R.drawable.ic_white_mage,
            "Scholar", "Warrior",
            "Paladin", "White Mage",
            4
        )
        questionsList.add(que7)

        // Blue Mage
        val que8 = Question(
            8, "What job is this?",
            R.drawable.ic_blue_mage,
            "Blue Mage", "Samurai",
            "Scholar", "Ninja",
            1
        )
        questionsList.add(que8)

        return questionsList

    }

}