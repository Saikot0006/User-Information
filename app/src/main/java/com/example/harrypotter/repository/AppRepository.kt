package com.example.harrypotter.repository

import com.example.harrypotter.api.ApiService
import javax.inject.Inject

class AppRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getCharacters() = apiService.getCharacters()

    suspend fun getCharacterDetails(id : String) = apiService.getCharacterDetails(id)
}