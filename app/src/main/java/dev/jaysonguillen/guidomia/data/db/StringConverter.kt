package dev.jaysonguillen.guidomia.data.db

import androidx.room.TypeConverter

class StringConverter {
    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.joinToString(",") // Convert List<String> to a single comma-separated String
    }

    @TypeConverter
    fun toList(data: String): List<String> {
        return data.split(",") // Convert the comma-separated String back to List<String>
    }
}