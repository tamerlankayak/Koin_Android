package com.example.koinandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koinandroid.model.CryptoModel
import com.example.koinandroid.service.CryptoAPI
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CryptoViewModel : ViewModel() {

    val cryptoList = MutableLiveData<List<CryptoModel>>()
    val cryptoError = MutableLiveData<Boolean>()
    val cryptoLoading = MutableLiveData<Boolean>()

    private var job: Job? = null

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
        cryptoError.value = true
    }

    fun getDataFromAPI() {
        cryptoLoading.value = true

        val BASE_URL = "https://raw.githubusercontent.com/"

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoAPI::class.java)

        /*
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {

        }
        */

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = retrofit.getData()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    cryptoError.value = false
                    cryptoLoading.value = false
                    response.body()?.let {
                        println(it)
                        cryptoList.value = it
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}