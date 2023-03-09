package edu.mines.csci448.examples.samodelkin.data

import android.content.Context
import android.util.Log
import edu.mines.csci448.examples.samodelkin.data.database.SamodelkinDao
import edu.mines.csci448.examples.samodelkin.data.database.SamodelkinDatabase
import edu.mines.csci448.examples.samodelkin.util.CharacterGenerator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.*

class SamodelkinRepo private constructor(private val samodelkinDao: SamodelkinDao,
                                         private val coroutineScope: CoroutineScope = GlobalScope
) {
    companion object {
        private const val LOG_TAG = "448.SamodelkinRepo"
        private var INSTANCE: SamodelkinRepo? = null

        /**
         * @param context
         */
        fun getInstance(context: Context): SamodelkinRepo {
            var instance = INSTANCE
            if(instance == null) {
                val database = SamodelkinDatabase.getInstance(context)
                instance = SamodelkinRepo(database.samodelkinDao)
                INSTANCE = instance
            }
            return instance
        }
    }

//    val characters: List<SamodelkinCharacter>
//
//    init {
//        Log.d(LOG_TAG, "initializing repository list")
//        val characterList = mutableListOf<SamodelkinCharacter>()
//        for(i in 1..10) {
//            characterList += CharacterGenerator.generateRandomCharacter(context)
//        }
//        characters = characterList.toList()
//    }

    fun getCharacters(): Flow<List<SamodelkinCharacter>> = samodelkinDao.getCharacters()
    suspend fun getCharacter(id: UUID): SamodelkinCharacter? = samodelkinDao.getCharacterById(id)
    fun addCharacter(samodelkinCharacter: SamodelkinCharacter) {
        coroutineScope.launch { samodelkinDao.addCharacter(samodelkinCharacter) }
    }
    fun deleteCharacter(samodelkinCharacter: SamodelkinCharacter) {
        coroutineScope.launch { samodelkinDao.deleteCharacter(samodelkinCharacter) }
    }

}