package com.example.harrypotter.api

import com.example.harrypotter.models.CharacterDetailsResponse
import com.example.harrypotter.models.CharacterListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("characters")
    suspend fun getCharacters() : Response<CharacterListResponse>

    @GET("character/{id}")
    suspend fun getCharacterDetails(@Path("id") id : String) : Response<CharacterDetailsResponse>
}