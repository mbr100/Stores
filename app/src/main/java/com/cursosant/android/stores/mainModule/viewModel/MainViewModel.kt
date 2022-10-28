package com.cursosant.android.stores.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursosant.android.stores.common.entities.StoreEntity
import com.cursosant.android.stores.common.util.Constants
import com.cursosant.android.stores.common.util.TypeError
import com.cursosant.android.stores.mainModule.model.MainInteraction
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private var interactor: MainInteraction = MainInteraction()
    private val showProgres: MutableLiveData<Boolean> = MutableLiveData()
    private val typeError: MutableLiveData<TypeError> = MutableLiveData()


    private val stores = interactor.stores

    fun getStores(): LiveData<MutableList<StoreEntity>> {
        return stores
    }

    fun getTypeError(): MutableLiveData<TypeError> = typeError

    fun isShowProgress(): LiveData<Boolean> {
        return showProgres
    }

    fun deleteStore(storeEntity: StoreEntity){
        executeAction { interactor.deleteStore(storeEntity) }
    }

    fun updateStore(storeEntity: StoreEntity){
        storeEntity.isFavorite = !storeEntity.isFavorite
        executeAction {  interactor.updatesStore(storeEntity) }

    }

    private fun executeAction (block: suspend () ->Unit): Job {
        return  viewModelScope.launch {
            try {
                block()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            } finally {
                showProgres.value = Constants.HIDE
            }
        }
    }



}