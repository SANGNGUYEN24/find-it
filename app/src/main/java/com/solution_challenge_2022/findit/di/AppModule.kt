package com.solution_challenge_2022.findit.di

import com.google.firebase.firestore.FirebaseFirestore
import com.solution_challenge_2022.findit.findit_feature.data.repository.CampusInfoImpl
import com.solution_challenge_2022.findit.findit_feature.domain.repository.CampusInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFirestoreInstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideCampusInfoRepository(db: FirebaseFirestore ): CampusInfoRepository{
        return CampusInfoImpl(db)
    }
}