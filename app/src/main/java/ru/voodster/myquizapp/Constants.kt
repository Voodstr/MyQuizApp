package ru.voodster.myquizapp

object Constants{
    fun getQuestions():ArrayList<Question>{
        val questionList = ArrayList<Question>()
        val que1 = Question(
            1,
            "What country this flag belong to?",
            R.drawable.ic_flag_of_australia,
            "Kuwait",
            "Latvia",
            "Bulgaria",
            "Australia",
            4
        )
        questionList.add(que1)
        val que2 = Question(
            2,
            "What country this flag belong to?",
            R.drawable.ic_flag_of_argentina,
            "Ukraine",
            "Czech",
            "Argentina",
            "Serbia",
            3
        )
        questionList.add(que2)
        val que3 = Question(
            3,
            "What country this flag belong to?",
            R.drawable.ic_flag_of_belgium,
            "Belgium",
            "Austria",
            "New Zealand",
            "France",
            1
        )
        questionList.add(que3)
        return questionList
    }
}