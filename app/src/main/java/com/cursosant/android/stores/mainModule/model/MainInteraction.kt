package com.cursosant.android.stores.mainModule.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.cursosant.android.stores.StoreApplication
import com.cursosant.android.stores.common.entities.StoreEntity
import com.cursosant.android.stores.common.util.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class MainInteraction {

    val stores: LiveData<MutableList<StoreEntity>> = liveData {
        delay(1_000)//Temporal para pruebas
        val storesLiveData = StoreApplication.database.storeDao().getAllStores()
        emitSource(storesLiveData.map { stores ->
            stores.sortedBy { it.name }.toMutableList()
        })
    }

    suspend fun deleteStore(storeEntity: StoreEntity) {
        delay(1_500)//Temporal para pruebas
        StoreApplication.database.storeDao().deleteStore(storeEntity)
    }

    suspend fun updatesStore(storeEntity: StoreEntity){
        delay(300)//Temporal para pruebas
        StoreApplication.database.storeDao().updateStore(storeEntity)
    }
}