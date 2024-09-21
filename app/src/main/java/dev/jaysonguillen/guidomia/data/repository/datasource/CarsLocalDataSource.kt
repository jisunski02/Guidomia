package dev.jaysonguillen.guidomia.data.repository.datasource

import dev.jaysonguillen.guidomia.data.model.Cars
import kotlinx.coroutines.flow.Flow

interface CarsLocalDataSource {
    suspend fun saveCars(cars: List<Cars>)
    fun getCars(): Flow<List<Cars>>
}