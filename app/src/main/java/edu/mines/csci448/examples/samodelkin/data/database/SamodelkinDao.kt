package edu.mines.csci448.examples.samodelkin.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface SamodelkinDao {
    @Insert
    fun addCharacter(character: SamodelkinCharacter)
    @Query("SELECT * FROM character")
    fun getCharacters(): Flow<List<SamodelkinCharacter>>
    @Query("SELECT * FROM character WHERE id=(:id)")
    fun getCharacterById(id: UUID): SamodelkinCharacter?
    @Delete
    fun deleteCharacter(character: SamodelkinCharacter)
}