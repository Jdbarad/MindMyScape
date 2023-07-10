package com.builditcreative.mindmyscape.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.builditcreative.mindmyscape.data.AppRepository
import com.builditcreative.mindmyscape.data.Data
import com.builditcreative.mindmyscape.data.GetTherapiesResultModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: AppRepository) :
    ViewModel() {

    private val _getTherapiesResponse = MutableLiveData<GetTherapiesResultModel>()
    val getTherapiesResponse: LiveData<GetTherapiesResultModel> = _getTherapiesResponse

    private val _getTherapiesApi = MutableLiveData<GetTherapiesResultModel>()

    fun getTherapies() = viewModelScope.launch  {
        _getTherapiesResponse.postValue(GetTherapiesResultModel(isLoading = true, message = "Loading..."))
        _getTherapiesResponse.postValue(repository.getTherapies().body())
        _getTherapiesApi.postValue(repository.getTherapies().body())
    }

    fun searchTherapies(query: String) {
        _getTherapiesResponse.postValue(GetTherapiesResultModel(isLoading = true, message = "Loading..."))
        if (query.isEmpty()) {
            _getTherapiesResponse.postValue(GetTherapiesResultModel(
                data = _getTherapiesApi.value?.data as ArrayList<Data>,
                isLoading = false
            ))
            return
        }
        val filteredList = mutableListOf<Data>()
        _getTherapiesApi.value?.data?.let { list ->
            list.forEach { data ->
                if (data.name!!.contains(query, true)) {
                    filteredList.add(data)
                }
            }
        }
        _getTherapiesResponse.postValue(GetTherapiesResultModel(
            data = filteredList as ArrayList<Data>,
            isLoading = false
        ))
    }

}
