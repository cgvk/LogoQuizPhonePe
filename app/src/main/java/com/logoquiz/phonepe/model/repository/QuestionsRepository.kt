package com.logoquiz.phonepe.model.repository

import androidx.lifecycle.MutableLiveData
import com.logoquiz.phonepe.model.QuestionModel

/**
 * Created by Vinod Kumar on 08/11/22.
 */

interface QuestionsRepository {
    fun getRandomQuestion(randomSet:HashSet<Int>):MutableLiveData<QuestionModel>
}