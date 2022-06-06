package com.mabnets.hiltcomposetest.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.mabnets.hiltcomposetest.Network.Resource
import com.mabnets.hiltcomposetest.Repo.Repostuff
import com.mabnets.hiltcomposetest.Utils.UIEvent
import com.mabnets.hiltcomposetest.models.Mydata
import com.mabnets.hiltcomposetest.network.Errorcodez
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class Newsviewmodel @Inject constructor(private  val repostuff: Repostuff) : ViewModel(){
    private val _state = mutableStateOf(NewsState())
    val state: State<NewsState> = _state

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun search(q:String){
        //_searchQuery.value = q
        // Set loading state
        _state.value = state.value.copy(
            isLoading = true,
            isError = false,
            errorMessage = null,
            errorCode = null,
        )
        viewModelScope.launch(Dispatchers.IO){
           repostuff.getnewsresults(q).onEach {result->
                when(result){
                    is Resource.Success<*> -> {
                        _state.value = state.value.copy(
                            MydataItems = result.data as List<Mydata>? ?: emptyList(),
                            isLoading = false,
                            isError = false
                        )
                    }
                    is Resource.Error<*> -> {
                        _state.value = state.value.copy(
                            MydataItems = result.data as List<Mydata>? ?: emptyList(),
                            isLoading = false,
                            isError = true,
                            errorMessage = result.message,
                            errorCode = result.errorCode
                        )

                        // Show snackbar if error is not WORD_NOT_FOUND
                        if (result.errorCode != Errorcodez.WORD_NOT_FOUND) {
                            _eventFlow.emit(
                                UIEvent.ShowSnackbar(
                                    result.message ?: "Unknown error"
                                )
                            )
                        }
                    }
                    is Resource.Loading<*> -> {
                        _state.value = state.value.copy(
                            MydataItems = result.data as List<Mydata>? ?: emptyList(),
                            isLoading = true
                        )
                    }
                }

            }
        }

    }


}