package com.logoquiz.phonepe.viewModel

import androidx.lifecycle.MutableLiveData
import com.logoquiz.phonepe.model.QuestionModel
import com.logoquiz.phonepe.model.repository.QuestionsRepository

/**
 * Created by Vinod Kumar on 08/11/22.
 */

class GetResult(private val questionsRepository: QuestionsRepository) {
    fun getRandomQuestion(randomSet:HashSet<Int>):MutableLiveData<QuestionModel>{
        return questionsRepository.getRandomQuestion(randomSet)
    }
}