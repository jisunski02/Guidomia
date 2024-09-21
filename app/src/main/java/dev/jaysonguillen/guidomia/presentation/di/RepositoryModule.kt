package dev.jaysonguillen.guidomia.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.jaysonguillen.guidomia.data.CarsRepositoryImpl
import dev.jaysonguillen.guidomia.data.repository.datasource.CarsAssetsDataSource
import dev.jaysonguillen.guidomia.data.repository.datasource.CarsLocalDataSource
import dev.jaysonguillen.guidomia.domain.repository.CarsRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesCarsRepository(carsLocalDataSource: CarsLocalDataSource,
                                carsAssetsDataSource: CarsAssetsDataSource): CarsRepository{
        return CarsRepositoryImpl(carsLocalDataSource, carsAssetsDataSource)
    }

}