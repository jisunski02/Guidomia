package dev.jaysonguillen.guidomia.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.jaysonguillen.guidomia.domain.usecase.GetCarsJsonUseCase
import dev.jaysonguillen.guidomia.domain.usecase.GetCarsUseCase
import dev.jaysonguillen.guidomia.domain.usecase.SaveCarsUseCase

@Suppress("UNCHECKED_CAST")
class CarsViewModelFactory(
    private val saveCarsUseCase: SaveCarsUseCase,
    private val getCarsUseCase: GetCarsUseCase,
    private val getCarsJsonUseCase: GetCarsJsonUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CarsViewModel(saveCarsUseCase, getCarsUseCase, getCarsJsonUseCase) as T
    }
}