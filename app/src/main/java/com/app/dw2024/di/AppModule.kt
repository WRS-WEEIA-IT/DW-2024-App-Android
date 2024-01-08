package com.app.dw2024.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.app.dw2024.repository.impl.EventsRepositoryImpl
import com.app.dw2024.repository.impl.TasksRepositoryImpl
import com.app.dw2024.repository.impl.UserRepositoryImpl
import com.app.dw2024.repository.interfaces.EventsRepository
import com.app.dw2024.repository.interfaces.TasksRepository
import com.app.dw2024.repository.interfaces.UserRepository
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideTasksRepository(
        userRepository: UserRepository,
        db: FirebaseFirestore,
        sharedPreferences: SharedPreferences
    ): TasksRepository {
        return TasksRepositoryImpl(userRepository, db, sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideUserRepository(sharedPreferences: SharedPreferences, db: FirebaseFirestore): UserRepository {
        return UserRepositoryImpl(sharedPreferences, db)
    }
}