package dev.jaysonguillen.guidomia.data.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cars_tbl")
data class Cars(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "cons_list")
    val consList: List<String>,  // List will be automatically converted to String for Room
    @ColumnInfo(name = "customer_price")
    val customerPrice: Double,
    @ColumnInfo(name = "make")
    val make: String,
    @ColumnInfo(name = "market_price")
    val marketPrice: Double,
    @ColumnInfo(name = "model")
    val model: String,
    @ColumnInfo(name = "pros_list")
    val prosList: List<String>,  // List will be automatically converted to String for Room
    @ColumnInfo(name = "rating")
    val rating: Int
)