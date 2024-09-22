package dev.jaysonguillen.guidomia.presentation.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.components.SingletonComponent
import dev.jaysonguillen.guidomia.presentation.adapter.CarsAdapter
import dev.jaysonguillen.guidomia.presentation.adapter.ConsAdapter
import dev.jaysonguillen.guidomia.presentation.adapter.MakeAdapter
import dev.jaysonguillen.guidomia.presentation.adapter.ModelAdapter
import dev.jaysonguillen.guidomia.presentation.adapter.ProsAdapter
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
class AdapterModule {

    @ActivityRetainedScoped
    @Provides
    fun providesCarsAdapter(
        context: Context,
        consAdapter: ConsAdapter,
        prosAdapter: ProsAdapter): CarsAdapter{
        return CarsAdapter(context, consAdapter, prosAdapter)
    }

    @ActivityRetainedScoped
    @Provides
    fun providesConsAdapter(): ConsAdapter{
        return ConsAdapter()
    }

    @ActivityRetainedScoped
    @Provides
    fun providesMakeAdapter(): MakeAdapter{
        return MakeAdapter()
    }

    @ActivityRetainedScoped
    @Provides
    fun providesModelAdapter(): ModelAdapter{
        return ModelAdapter()
    }

    @ActivityRetainedScoped
    @Provides
    fun providesProsAdapter(): ProsAdapter{
        return ProsAdapter()
    }
}