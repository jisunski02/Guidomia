package dev.jaysonguillen.guidomia.domain.usecase

import dev.jaysonguillen.guidomia.data.model.Cars
import dev.jaysonguillen.guidomia.domain.repository.CarsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveCarsUseCase @Inject constructor(private val carsRepository: CarsRepository) {

   suspend fun execute(cars: List<Cars>) {
        return carsRepository.saveCars(cars)
    }

}