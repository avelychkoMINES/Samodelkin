package edu.mines.csci448.examples.samodelkin.data.database

import androidx.room.TypeConverter
import java.util.*

class SamodelkinTypeConverter {
    @TypeConverter
    fun fromUUID(uuid: UUID?) = uuid?.toString()
    @TypeConverter
    fun toUUID(uuid: String?) = UUID.fromString(uuid)

}