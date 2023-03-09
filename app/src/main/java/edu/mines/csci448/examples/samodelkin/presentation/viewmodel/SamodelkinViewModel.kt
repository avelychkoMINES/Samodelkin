package edu.mines.csci448.examples.samodelkin.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter
import edu.mines.csci448.examples.samodelkin.data.SamodelkinRepo
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class SamodelkinViewModel(private val samodelkinRepo: SamodelkinRepo) : ViewModel(),
    ISamodelkinViewModel {
    companion object {
        private const val LOG_TAG = "448.SamodelkinViewModel"
    }

    //private val mCharacters = characters.toMutableStateList()
    private val mCharacters: MutableStateFlow<List<SamodelkinCharacter>> =
        MutableStateFlow(emptyList())

    /**
     * holds list of all characters stored within the view model
     */
    override val characterListState: StateFlow<List<SamodelkinCharacter>>
        get() = mCharacters.asStateFlow()

    private val mCurrentCharacterState: MutableStateFlow<SamodelkinCharacter?> = MutableStateFlow(null)

    override val currentCharacterState: StateFlow<SamodelkinCharacter?>
        get() = mCurrentCharacterState.asStateFlow()

    private val mCurrentCharacterIdState: MutableStateFlow<UUID> =
        MutableStateFlow(UUID.randomUUID())

    init {
        viewModelScope.launch {
            samodelkinRepo.getCharacters().collect { characterList ->
                mCharacters.update { characterList }
            }
        }
        viewModelScope.launch {
            mCurrentCharacterIdState
                .map { uuid -> samodelkinRepo.getCharacter(uuid) }
                .collect { character -> mCurrentCharacterState.update { character } }
        }
    }

    /**
     * Loads a character by id into currentCharacterState, if it exists.  If id is not found
     * in list of characters, then sets currentCharacterState to null.
     * @param uuid id to use for character lookup
     */
    override fun loadCharacterByUUID(uuid: UUID) {
        Log.d(LOG_TAG, "loadCharacterByUUID($uuid)")
        mCurrentCharacterIdState.update { uuid }
//        mCurrentCharacterState.value = null
//        mCharacters.forEach { character ->
//            if(character.id == uuid) {
//                Log.d(LOG_TAG, "Character found! $character")
//                mCurrentCharacterState.value = character
//                return
//            }
//        }
//        Log.d(LOG_TAG, "Character not found")
//        return
    }

    /**
     * Adds the given character to the list of characters.
     * @param characterToAdd character to add to the list
     */
    override fun addCharacter(characterToAdd: SamodelkinCharacter) {
        Log.d(LOG_TAG, "adding character $characterToAdd")
        samodelkinRepo.addCharacter(characterToAdd)
    }

    /**
     * Deletes corresponding character from the list of characters, if it exists in the list.
     * Matches characters by id.  If character is not found in the list, does nothing.
     * @param characterToDelete character to delete from list
     */
    override fun deleteCharacter(characterToDelete: SamodelkinCharacter) {
        Log.d(LOG_TAG, "deleting character $characterToDelete")
        samodelkinRepo.deleteCharacter(characterToDelete)
//        mCharacters.forEach { character ->
//            if(character.id == characterToDelete.id) {
//                mCharacters.remove(character)
//                if(mCurrentCharacterState.value == character) {
//                    mCurrentCharacterState.value = null
//                }
//                Log.d(LOG_TAG, "character deleted")
//                return
//            }
//        }
//        Log.d(LOG_TAG, "Character not found")
    }
}