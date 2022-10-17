package com.cursosant.android.stores.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cursosant.android.stores.common.entities.StoreEntity
import com.cursosant.android.stores.common.util.Constants
import com.cursosant.android.stores.mainModule.model.MainInteraction

class MainViewModel: ViewModel() {
    private var interactor: MainInteraction = MainInteraction()
    private var storeList: MutableList<StoreEntity> = mutableListOf()

    private val showProgres: MutableLiveData<Boolean> = MutableLiveData()


    private val stores: MutableLiveData<MutableList<StoreEntity>> by lazy {
        MutableLiveData<MutableList<StoreEntity>>().also {
            loadStores()
        }
    }

    fun getStores(): LiveData<MutableList<StoreEntity>> {
        return stores
    }

    fun isShowProgress(): LiveData<Boolean> {
        return showProgres
    }

    private fun loadStores() {
        showProgres.value = Constants.SHOW
        interactor. getStores {
            showProgres.value = Constants.HIDE
            stores.value = it
            storeList = it
        }
    }

    fun deleteStore(storeEntity: StoreEntity){
        interactor.deleteStore(storeEntity) {
            val index = storeList.indexOf(storeEntity)
            if (index != -1) {
                storeList.removeAt(index)
                stores.value = storeList
            }
        }
    }

    fun updateStore(storeEntity: StoreEntity){
        storeEntity.isFavorite = !storeEntity.isFavorite
        interactor.deleteStore(storeEntity) {
            val index = storeList.indexOf(storeEntity)
            if (index != -1) {
                storeList[index] = storeEntity
                stores.value = storeList
            }
        }
    }



}