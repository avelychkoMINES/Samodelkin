package edu.mines.csci448.examples.samodelkin.util.api

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class SamodelkinFetchr {
    companion object {
        private const val LOG_TAG = "448.SamodelkinFetchr"
    }

    //private val samodelkinApiService: SamodelkinApiService
    private val mCharacterState: MutableStateFlow<SamodelkinCharacter?> = MutableStateFlow(null)
    val characterState: StateFlow<SamodelkinCharacter?>
        get() = mCharacterState.asStateFlow()

    val retrofit = Retrofit
        .Builder()
        .baseUrl("https://cs-courses.mines.edu/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val samodelkinApiService =
        retrofit.create(SamodelkinApiService::class.java)

    fun getCharacter() {
        val samodelkinRequest = samodelkinApiService.getCharacter()

        samodelkinRequest.enqueue(object : Callback<SamodelkinCharacter> {
            override fun onFailure(call: Call<SamodelkinCharacter>, t: Throwable) {
                Log.e(LOG_TAG, "onFailure() called $t")
                mCharacterState.update { null }
            }

            override fun onResponse(
                call: Call<SamodelkinCharacter>,
                response: Response<SamodelkinCharacter>
            ) {
                Log.d(LOG_TAG, "onResponse() called")
                val responseCharacter = response.body()
                Log.d(LOG_TAG, "$responseCharacter")
                if (responseCharacter == null) {
                    Log.d(LOG_TAG, "responseCharacter is null")
                    mCharacterState.update { null }
                } else {
                    val newCharacter = responseCharacter.copy(
                        avatarAssetPath = "file:///android_asset/characters/${responseCharacter.avatarAssetPath}",
                        id = UUID.randomUUID()
                    )
                    Log.d(LOG_TAG, "$newCharacter")
                    mCharacterState.update { newCharacter }
                }
            }
        })
    }
}