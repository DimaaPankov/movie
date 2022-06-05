package com.sekvenie.model.network.dataimpl

//import com.sekvenie.model.network.Api.retrofitService
import com.sekvenie.model.network.service.Data
import com.sekvenie.mainpresemter.DataApi
import com.sekvenie.model.network.ApiService
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class DataImpl : DataApi<Data> {
    private var data: Data? = null

    override suspend fun getData(): Data {
        data = Data(listOf())
        coroutineScope {
            launch {
                try {
                    data = ApiService.getInstance().getPhotos()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return data!!
    }
}