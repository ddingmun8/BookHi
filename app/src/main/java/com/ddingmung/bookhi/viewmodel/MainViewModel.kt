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

    fun searchBook(query: String){
        viewModelScope.launch {
            val response = repository.searchImage(query,"accuracy")
            myCustomPosts.value = response
        }
    }

}