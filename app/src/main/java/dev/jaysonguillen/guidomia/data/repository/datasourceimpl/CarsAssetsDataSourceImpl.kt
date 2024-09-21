package dev.jaysonguillen.guidomia.data.repository.datasourceimpl

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.jaysonguillen.guidomia.data.model.Cars
import dev.jaysonguillen.guidomia.data.repository.datasource.CarsAssetsDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okio.IOException

class CarsAssetsDataSourceImpl(private val context: Context): CarsAssetsDataSource {

    override fun getCarsJson(): Flow<List<Cars>> = flow {
        try {
            val inputStream = context.assets.open("car_list.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            val jsonString = String(buffer, Charsets.UTF_8)

            // Parse the JSON string to a list of Car objects
            val gson = Gson()
            val listCarType = object : TypeToken<List<Cars>>() {}.type
            val carList: List<Cars> = gson.fromJson(jsonString, listCarType)

            // Emit the list of cars
            emit(carList)
        } catch (e: IOException) {
            Log.e("ErrorHere", "$e")
            emit(emptyList())
        }
    }
        .flowOn(Dispatchers.IO)
}