package dev.jaysonguillen.guidomia.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.jaysonguillen.guidomia.domain.usecase.GetCarsJsonUseCase
import dev.jaysonguillen.guidomia.domain.usecase.GetCarsUseCase
import dev.jaysonguillen.guidomia.domain.usecase.SaveCarsUseCase
import dev.jaysonguillen.guidomia.presentation.viewmodel.CarsViewModelFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun providesCarsViewModelFactory(
        saveCarsUseCase: SaveCarsUseCase,
        getCarsUseCase: GetCarsUseCase,
        getCarsJsonUseCase: GetCarsJsonUseCase
    ): CarsViewModelFactory {
        return CarsViewModelFactory(saveCarsUseCase, getCarsUseCase, getCarsJsonUseCase)
    }
}