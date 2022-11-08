package com.logoquiz.phonepe.viewModel

import com.logoquiz.phonepe.model.repository.QuestionRepositoryImpl
import com.logoquiz.phonepe.model.repository.QuestionsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by Vinod Kumar on 08/11/22.
 */

@Module
@InstallIn(ViewModelComponent::class)
class MainModuleProvider {
    @Provides
    @ViewModelScoped
    fun getQuestionProvider(
        questionsRepository: QuestionsRepository
    ) = QuestionRepositoryImpl()
}