package dev.jaysonguillen.guidomia.presentation.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.jaysonguillen.guidomia.data.db.CarsDao
import dev.jaysonguillen.guidomia.data.db.CarsDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun providesCarsDatabase(app: Application): CarsDatabase{
        return Room.databaseBuilder(app, CarsDatabase::class.java,"cars_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesCarsDao(carsDatabase: CarsDatabase): CarsDao{
        return carsDatabase.getCarsDao()
    }

}