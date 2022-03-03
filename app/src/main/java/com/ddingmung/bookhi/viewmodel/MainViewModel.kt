package com.ddingmung.bookhi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddingmung.bookhi.model.BookSearchResponse
import com.ddingmung.bookhi.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository : Repository) : ViewModel() {

    val myCustomPosts : MutableLiveData<Response<BookSearchResponse>> = MutableLiveData()

    fun searchBook(){
        viewModelScope.launch {
            val response = repository.searchImage("미움받을 용기","accuracy")
            myCustomPosts.value = response
        }
    }

}