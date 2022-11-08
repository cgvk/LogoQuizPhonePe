package com.logoquiz.phonepe.model.repository

import androidx.lifecycle.MutableLiveData
import com.logoquiz.phonepe.model.QuestionModel
import com.logoquiz.phonepe.model.QuestionProvider

/**
 * Created by Vinod Kumar on 08/11/22.
 */

class QuestionRepositoryImpl: QuestionsRepository {
    private val randomQuestion = MutableLiveData<QuestionModel>()
    private var questionNumber = 0
    override fun getRandomQuestion(randomSet:HashSet<Int>): MutableLiveData<QuestionModel> {
        val randomQuestionList = QuestionProvider.generateRandomList(randomSet)
        randomQuestion.postValue(randomQuestionList[questionNumber])
        questionNumber++
        return randomQuestion
    }
}