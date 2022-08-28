package com.mabnets.hiltcomposetest.presentation.Home


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mabnets.hiltcomposetest.domain.usecase.Getlocalnewscase
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onEach
import com.mabnets.hiltcomposetest.data.Result
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch


@HiltViewModel
class Newsviewmodel @Inject constructor(
    private val getlocalnewsusecase: Getlocalnewscase)
    : ViewModel() {
    var state by mutableStateOf(NewsState(isLoading = true))
        private set

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    /*fun getNews(q: String) {
        viewModelScope.launch {
            getnewsusecase(q).onEach { result ->
                when (result) {
                    is Result.Success -> {
                        state = state.copy(
                            NewsItems = result.data ?: emptyList()
                        )
                    }
                    is Result.Error -> {
                        state = state.copy(
                            isLoading = false
                        )
                        _eventFlow.emit(
                            UIEvent.ShowSnackBar(
                                result.message ?: "Unknown error"
                            )
                        )
                    }
                    is Result.Loading -> {
                        state = state.copy(
                            isLoading = true
                        )
                    }
                }
            }.launchIn(this)
        }

    }*/
    fun getNews(q: String){
        viewModelScope.launch {
            getlocalnewsusecase.invoke(q).onEach { result ->
                when (result) {
                    is Result.Success -> {
                        state = state.copy(
                            NewsItems = result.data ?: emptyList()
                        )
                    }
                    is Result.Error -> {
                        state = state.copy(
                            isLoading = false,
                            error = result.message!!
                        )
                        _eventFlow.emit(
                            UIEvent.ShowSnackBar(
                                result.message ?: "Unknown error"
                            )
                        )
                    }
                    is Result.Loading -> {
                        state = state.copy(
                            isLoading = true
                        )
                    }
                }
            }.launchIn(this)
        }
    }
    sealed class UIEvent {
        data class ShowSnackBar(val message: String) : UIEvent()
    }


}