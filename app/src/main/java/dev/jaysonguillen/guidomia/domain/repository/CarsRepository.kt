package dev.jaysonguillen.guidomia.domain.repository

import dev.jaysonguillen.guidomia.data.model.Cars
import kotlinx.coroutines.flow.Flow

interface CarsRepository {
    suspend fun saveCars(cars: List<Cars>)
    fun getCars(): Flow<List<Cars>>
    fun getCarsJson(): Flow<List<Cars>>
}