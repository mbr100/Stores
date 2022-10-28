package com.cursosant.android.stores.editModule.model

import com.cursosant.android.stores.StoreApplication
import com.cursosant.android.stores.common.entities.StoreEntity
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class EditStoreInteractor {
    fun saveStore(storeEntity: StoreEntity, callback: (Long) -> Unit) {
        doAsync {
            val newID = StoreApplication.database.storeDao().addStore(storeEntity)
            uiThread {
                callback(newID)
            }
        }
    }

    fun updateStore(storeEntity: StoreEntity, callback: (StoreEntity) -> Unit) {
        /*doAsync {
            StoreApplication.database.storeDao().updateStore(storeEntity)
            uiThread {
                callback(storeEntity)
            }
        }*/
    }


}