package dev.jaysonguillen.guidomia.presentation.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.jaysonguillen.guidomia.data.repository.datasource.CarsAssetsDataSource
import dev.jaysonguillen.guidomia.data.repository.datasourceimpl.CarsAssetsDataSourceImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AssetsDataModule {

    @Provides
    @Singleton
    fun providesContext(@ApplicationContext context: Context): Context {
        return context
    }


    @Provides
    @Singleton
    fun providesCarsAssetsDataSource(context: Context): CarsAssetsDataSource{
        return CarsAssetsDataSourceImpl(context)
    }
}