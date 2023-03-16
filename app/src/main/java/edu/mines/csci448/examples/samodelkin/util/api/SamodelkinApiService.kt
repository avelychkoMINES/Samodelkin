package edu.mines.csci448.examples.samodelkin.util.api

import edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter
import retrofit2.Call
import retrofit2.http.GET

interface SamodelkinApiService {
    companion object {
        const val BASE_API_URL = "https://cs-courses.mines.edu"
    }

    @GET("csci448/samodelkin/")
    fun getCharacter(): Call<SamodelkinCharacter>
}