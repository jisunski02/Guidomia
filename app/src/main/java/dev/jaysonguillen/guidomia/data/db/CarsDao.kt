package dev.jaysonguillen.guidomia.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.jaysonguillen.guidomia.data.model.Cars
import kotlinx.coroutines.flow.Flow

@Dao
interface CarsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCars(cars: List<Cars>)

    @Query("SELECT * FROM cars_tbl")
    fun getCars(): Flow<List<Cars>>

}