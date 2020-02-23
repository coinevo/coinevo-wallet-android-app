package io.wookey.wallet.feature.wallet

import android.arch.lifecycle.MutableLiveData
import io.wookey.wallet.R
import io.wookey.wallet.base.BaseViewModel
import io.wookey.wallet.core.EVORepository
import io.wookey.wallet.core.EVOWalletController
import io.wookey.wallet.data.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BackupKeyViewModel : BaseViewModel() {

    private val repository = EVORepository()

    val publicViewKey = MutableLiveData<String>()
    val secretViewKey = MutableLiveData<String>()
    val publicSpendKey = MutableLiveData<String>()
    val secretSpendKey = MutableLiveData<String>()
    val address = MutableLiveData<String>()

    val showLoading = MutableLiveData<Boolean>()
    val hideLoading = MutableLiveData<Boolean>()
    val toast = MutableLiveData<String>()
    val toastRes = MutableLiveData<Int>()

    fun openWallet(walletId: Int, password: String) {
        showLoading.postValue(true)
        uiScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    val wallet = AppDatabase.getInstance().walletDao().getWalletById(walletId)
                    if (wallet == null) {
                        toastRes.postValue(R.string.data_exception)
                    } else {
                        val path = repository.getWalletFilePath(wallet.name)
                        EVOWalletController.openWallet(path, password)
                        publicViewKey.postValue(EVOWalletController.getPublicViewKey())
                        secretViewKey.postValue(EVOWalletController.getSecretViewKey())
                        publicSpendKey.postValue(EVOWalletController.getPublicSpendKey())
                        secretSpendKey.postValue(EVOWalletController.getSecretSpendKey())
                        address.postValue(wallet.address)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                toast.postValue(e.message)
            } finally {
                hideLoading.postValue(true)
            }
        }
    }
}