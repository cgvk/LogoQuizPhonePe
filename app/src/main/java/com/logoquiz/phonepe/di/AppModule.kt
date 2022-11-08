package com.logoquiz.phonepe.di

import com.logoquiz.phonepe.model.repository.QuestionRepositoryImpl
import com.logoquiz.phonepe.model.repository.QuestionsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Vinod Kumar on 08/11/22.
 */

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun questionRepository(): QuestionsRepository = QuestionRepositoryImpl()

}