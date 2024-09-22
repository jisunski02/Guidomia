package dev.jaysonguillen.guidomia.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dev.jaysonguillen.guidomia.domain.usecase.GetCarsJsonUseCase
import dev.jaysonguillen.guidomia.domain.usecase.GetCarsUseCase
import dev.jaysonguillen.guidomia.domain.usecase.SaveCarsUseCase
import dev.jaysonguillen.guidomia.presentation.viewmodel.CarsViewModelFactory

@Module
@InstallIn(ActivityRetainedComponent::class)
class FactoryModule {

    @ActivityRetainedScoped
    @Provides
    fun providesCarsViewModelFactory(
        saveCarsUseCase: SaveCarsUseCase,
        getCarsUseCase: GetCarsUseCase,
        getCarsJsonUseCase: GetCarsJsonUseCase
    ): CarsViewModelFactory {
        return CarsViewModelFactory(saveCarsUseCase, getCarsUseCase, getCarsJsonUseCase)
    }
}