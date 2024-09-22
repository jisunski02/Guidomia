package dev.jaysonguillen.guidomia.data.utils

import dev.jaysonguillen.guidomia.R

enum class CarMake(val imageResource: Int) {
    LAND_ROVER(R.drawable.range_rover),
    ALPINE(R.drawable.alpine_roadster),
    BMW(R.drawable.bmw_330i),
    MERCEDES_BENZ(R.drawable.mercedez_benz_glc);

    companion object {
        fun fromString(make: String): CarMake? {
            return entries.find { it.name.replace("_", " ").equals(make, ignoreCase = true) }
        }
    }
}