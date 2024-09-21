package dev.jaysonguillen.guidomia.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.jaysonguillen.guidomia.data.model.Cars

@Database(entities = [Cars::class], version = 1, exportSchema = false)
@TypeConverters(StringConverter::class)
abstract class CarsDatabase: RoomDatabase() {
    abstract fun getCarsDao(): CarsDao
}