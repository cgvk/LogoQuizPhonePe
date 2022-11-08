package com.logoquiz.phonepe.common

import android.content.Context
import com.google.gson.Gson
import com.logoquiz.phonepe.model.QuestionModel

/**
 * Created by Vinod Kumar on 08/11/22.
 */
object Utils {
    var questionsList: List<QuestionModel> = emptyList()
    fun setLogoResponseList(context: Context) {
        questionsList = Gson().fromJson(context.assets.open("response.json").bufferedReader()
            .use { it.readText() }, Array<QuestionModel>::class.java
        ).toList()
    }

    var alphabet_character = arrayOf(
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
        "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x",
        "y", "z"
    )
}