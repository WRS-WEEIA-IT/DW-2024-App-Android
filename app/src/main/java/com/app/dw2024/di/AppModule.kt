package com.app.dw2024.di

import com.app.dw2024.repository.impl.EventsRepositoryImpl
import com.app.dw2024.repository.interfaces.EventsRepository
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
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
    fun provideFirebaseFirestoreInstance(): FirebaseFirestore {
        return Firebase.firestore
    }

    @Provides
    @Singleton
    fun provideEventsRepository(db: FirebaseFirestore): EventsRepository {
        return EventsRepositoryImpl(db)
    }
}