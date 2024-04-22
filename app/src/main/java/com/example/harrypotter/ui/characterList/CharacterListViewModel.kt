package com.example.harrypotter.ui.characterList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotter.models.CharacterDetailsResponse
import com.example.harrypotter.models.CharacterListResponse
import com.example.harrypotter.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(private val repository: AppRepository): ViewModel(){

    private val _isLoading = MutableLiveData<Boolean>().apply {
        value = true
    }

    val isLoading = _isLoading

    private val _characterList = MutableLiveData<List<CharacterListResponse.CharacterListResponseItem>>()
    val characterList : LiveData<List<CharacterListResponse.CharacterListResponseItem>> = _characterList

    fun getCharacters(){
        try {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response = repository.getCharacters()
                    withContext(Dispatchers.Main){
                        _isLoading.value = false
                        if(response.isSuccessful){
                            if(!response.body().isNullOrEmpty()){
                                _characterList.value = response.body()
                            }else{
                                _characterList.value = listOf()
                            }
                        }else{
                            _characterList.value = listOf()
                        }
                    }
                }catch (_:Exception){
                    withContext(Dispatchers.Main){
                        _isLoading.value = false
                        _characterList.value = listOf()
                    }
                }
            }
        }catch (_:Exception){
            _isLoading.value = false
            _characterList.value = listOf()
        }
    }
}