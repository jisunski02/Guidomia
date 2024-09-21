package dev.jaysonguillen.guidomia.data.repository.datasourceimpl

import dev.jaysonguillen.guidomia.data.db.CarsDao
import dev.jaysonguillen.guidomia.data.model.Cars
import dev.jaysonguillen.guidomia.data.repository.datasource.CarsLocalDataSource
import kotlinx.coroutines.flow.Flow

class CarsLocalDataSourceImpl(
    private val carsDao: CarsDao
): CarsLocalDataSource {

    override suspend fun saveCars(cars: List<Cars>) {
        return carsDao.saveCars(cars)
    }

    override fun getCars(): Flow<List<Cars>> {
        return carsDao.getCars()
    }

}