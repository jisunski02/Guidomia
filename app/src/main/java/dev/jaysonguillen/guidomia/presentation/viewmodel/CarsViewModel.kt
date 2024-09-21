package dev.jaysonguillen.guidomia.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dev.jaysonguillen.guidomia.data.model.Cars
import dev.jaysonguillen.guidomia.domain.usecase.GetCarsJsonUseCase
import dev.jaysonguillen.guidomia.domain.usecase.GetCarsUseCase
import dev.jaysonguillen.guidomia.domain.usecase.SaveCarsUseCase
import kotlinx.coroutines.launch

class CarsViewModel(
    private val saveCarsUseCase: SaveCarsUseCase,
    private val getCarsUseCase: GetCarsUseCase,
    private val getCarsJsonUseCase: GetCarsJsonUseCase
): ViewModel() {

    fun saveCars(cars: List<Cars>) = viewModelScope.launch {
        saveCarsUseCase.execute(cars)
    }


    fun getCars() = liveData {
        getCarsUseCase.execute().collect{
            emit(it)
        }
    }


    fun getCarsJson() = liveData {
        getCarsJsonUseCase.execute().collect{
            emit(it)
        }
    }


}