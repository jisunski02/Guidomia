package dev.jaysonguillen.guidomia.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.jaysonguillen.guidomia.data.db.CarsDao
import dev.jaysonguillen.guidomia.data.repository.datasource.CarsLocalDataSource
import dev.jaysonguillen.guidomia.data.repository.datasourceimpl.CarsLocalDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun providesCarsLocalDataSource(carsDao: CarsDao): CarsLocalDataSource{
        return CarsLocalDataSourceImpl(carsDao)
    }
}