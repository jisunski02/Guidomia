package dev.jaysonguillen.guidomia.data.repository.datasource

import dev.jaysonguillen.guidomia.data.model.Cars
import kotlinx.coroutines.flow.Flow

interface CarsAssetsDataSource {
     fun getCarsJson(): Flow<List<Cars>>
}