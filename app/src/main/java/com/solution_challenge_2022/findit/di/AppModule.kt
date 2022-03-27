package com.solution_challenge_2022.findit.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.solution_challenge_2022.findit.findit_feature.data.repository.BuildingDetailImpl
import com.solution_challenge_2022.findit.findit_feature.data.repository.PlaceInfoImpl
import com.solution_challenge_2022.findit.findit_feature.domain.repository.BuildingDetailRepository
import com.solution_challenge_2022.findit.findit_feature.domain.repository.PlaceInfoRepository
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
    fun provideFiresbaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideCampusInfoRepository(db: FirebaseFirestore): PlaceInfoRepository {
        return PlaceInfoImpl(db)
    }

    @Provides
    @Singleton
    fun provideBuildingDetailRepository(db: FirebaseFirestore): BuildingDetailRepository {
        return BuildingDetailImpl(db)
    }
}