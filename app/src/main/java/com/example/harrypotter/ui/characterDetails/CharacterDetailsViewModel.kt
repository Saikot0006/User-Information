package com.example.harrypotter.ui.characterDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotter.models.CharacterDetailsResponse
import com.example.harrypotter.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(private val repository: AppRepository): ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>().apply {
        value = true
    }

    val isLoading = _isLoading

    private val _characterDetails = MutableLiveData<CharacterDetailsResponse.CharacterDetailsResponseItem>().apply {
        value = null
    }

    val characterDetails : LiveData<CharacterDetailsResponse.CharacterDetailsResponseItem> = _characterDetails


    fun getCharacterDetails(id : String){
        try {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response = repository.getCharacterDetails(id)
                    withContext(Dispatchers.Main){
                        _isLoading.value = false
                        if(response.isSuccessful){
                            if(!response.body().isNullOrEmpty()){
                                _characterDetails.value = response.body()?.get(0)
                            }else{
                                _characterDetails.value = null
                            }
                        }else{
                            _characterDetails.value = null
                        }
                    }
                }catch (e:Exception){
                    withContext(Dispatchers.Main){
                        _isLoading.value = false
                        _characterDetails.value = null
                    }
                }
            }
        }catch (e:Exception){
            _isLoading.value = false
            _characterDetails.value = null
        }
    }
}