package dev.jaysonguillen.guidomia.data

import dev.jaysonguillen.guidomia.data.model.Cars
import dev.jaysonguillen.guidomia.data.repository.datasource.CarsAssetsDataSource
import dev.jaysonguillen.guidomia.data.repository.datasource.CarsLocalDataSource
import dev.jaysonguillen.guidomia.domain.repository.CarsRepository
import kotlinx.coroutines.flow.Flow

class CarsRepositoryImpl(
    private val carsLocalDataSource: CarsLocalDataSource,
    private val carsAssetsDataSource: CarsAssetsDataSource
): CarsRepository {

    override suspend fun saveCars(cars: List<Cars>) {
        return carsLocalDataSource.saveCars(cars)
    }

    override fun getCars(): Flow<List<Cars>> {
        return carsLocalDataSource.getCars()
    }

    override fun getCarsJson(): Flow<List<Cars>> {
        return carsAssetsDataSource.getCarsJson()
    }
}