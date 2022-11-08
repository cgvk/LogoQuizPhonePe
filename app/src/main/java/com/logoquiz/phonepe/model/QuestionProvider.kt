package com.logoquiz.phonepe.model

import com.logoquiz.phonepe.common.Utils

/**
 * Created by Vinod Kumar on 08/11/22.
 */

class QuestionProvider {
    companion object {
        fun generateRandomList(randomSet: HashSet<Int>): List<QuestionModel> {
            val randomQuestionList = mutableListOf<QuestionModel>()
            randomSet.forEach { index->
                randomQuestionList.add(Utils.questionsList[index])
            }
            return randomQuestionList
        }
    }
}