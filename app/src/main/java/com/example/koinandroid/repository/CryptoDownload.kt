package com.example.koinandroid.repository

import com.example.koinandroid.model.CryptoModel
import com.example.koinandroid.util.Resource

interface CryptoDownload {

    suspend fun downloadCryptos(): Resource<List<CryptoModel>>
}