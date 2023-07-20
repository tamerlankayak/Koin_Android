package com.example.koinandroid.repository

import com.example.koinandroid.model.CryptoModel
import com.example.koinandroid.service.CryptoAPI
import com.example.koinandroid.util.Resource
import java.lang.Exception

class CryptoDownloadImpl(private val api: CryptoAPI) : CryptoDownload {

    override suspend fun downloadCryptos(): Resource<List<CryptoModel>> {
        return try {
            val response = api.getData()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error", null)
            } else {
                Resource.error("Error", null)
            }
        } catch (e: Exception) {
            Resource.error("No data", null)
        }
    }
}