package dev.jaysonguillen.guidomia.domain.usecase

import dev.jaysonguillen.guidomia.data.model.Cars
import dev.jaysonguillen.guidomia.domain.repository.CarsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCarsJsonUseCase @Inject constructor(private val carsRepository: CarsRepository) {

    fun execute(): Flow<List<Cars>>{
        return carsRepository.getCarsJson()
    }

}