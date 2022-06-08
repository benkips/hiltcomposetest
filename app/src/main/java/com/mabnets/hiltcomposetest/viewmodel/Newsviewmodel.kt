package com.mabnets.hiltcomposetest.viewmodel

import androidx.lifecycle.*
import com.mabnets.hiltcomposetest.Network.Resource
import com.mabnets.hiltcomposetest.Repo.Repostuff
import com.mabnets.hiltcomposetest.data.local.Entity.Mydata
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class Newsviewmodel @Inject constructor(private  val repostuff: Repostuff) : ViewModel(){
    private val _stateFlow = MutableStateFlow<Resource<List<Mydata>>>(Resource.Loading())
    val stateFlow: StateFlow<Resource<List<Mydata>>>
        get() = _stateFlow

    fun search(q:String){
        viewModelScope.launch {
           repostuff.getnewsresults(q).collect {
               _stateFlow.value
        }

    }


}