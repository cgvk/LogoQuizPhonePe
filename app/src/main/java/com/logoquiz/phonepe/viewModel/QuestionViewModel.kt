package com.logoquiz.phonepe.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.logoquiz.phonepe.model.QuestionModel
import com.logoquiz.phonepe.model.repository.QuestionRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Vinod Kumar on 08/11/22.
 */
@HiltViewModel
class QuestionViewModel @Inject constructor(private val questionsRepository: QuestionRepositoryImpl):ViewModel() {
    //private val repositoryQuestion:QuestionsRepository = QuestionRepositoryImpl()
    fun getRandomQuestion(randomSet:HashSet<Int>):MutableLiveData<QuestionModel>{
        return questionsRepository.getRandomQuestion(randomSet)
    }
}