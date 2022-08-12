package com.example.githubrepositories.ui.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.githubrepositories.data.model.RepositoriesResponse
import com.example.githubrepositories.data.util.Resource
import com.example.githubrepositories.domain.usecase.GetRepositoriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class RepositoriesViewModel(
    private val app: Application,
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) : AndroidViewModel(app) {
    val repositories: MutableLiveData<Resource<RepositoriesResponse>> = MutableLiveData()

    fun getGithubRepositories(country: String, page: Int) = viewModelScope.launch(Dispatchers.IO) {
        repositories.postValue(Resource.Loading())
        try{
            if(isNetworkAvailable(app)) {

                val apiResult = getRepositoriesUseCase.execute(page)
                repositories.postValue(apiResult)
            }else{
                repositories.postValue(Resource.Error("Internet is not available"))
            }

        }catch (e: Exception){
            repositories.postValue(Resource.Error(e.message.toString()))
        }

    }

    private fun isNetworkAvailable(context: Context?):Boolean{
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false

    }


}
