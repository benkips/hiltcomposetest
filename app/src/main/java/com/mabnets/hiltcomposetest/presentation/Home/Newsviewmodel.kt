package com.mabnets.hiltcomposetest.presentation.Home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import androidx.compose.runtime.State
import com.mabnets.hiltcomposetest.domain.usecase.Getnewsusecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

import kotlinx.coroutines.launch
import javax.inject.Inject
import com.mabnets.hiltcomposetest.data.Result
import com.mabnets.hiltcomposetest.domain.model.News


@HiltViewModel
class Newsviewmodel @Inject constructor(private val getnewsusecase: Getnewsusecase) : ViewModel() {
    private val _state = mutableStateOf(NewsState())
    val state: State<NewsState> = _state


    init {
        search("tuko")
    }

    fun search(q: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getnewsusecase(query = q).onEach { results ->
                when (results) {
                    is Result.Success -> {
                        _state.value = NewsState(
                            NewsItems = results.data ?: emptyList()
                        )
                    }
                    is Result.Error -> {
                        _state.value =  NewsState(
                            error = results.message ?: "An unexpected error occured"
                        )
                    }
                    is Result.Loading -> {
                        _state.value = NewsState(isLoading = true)
                    }

                }

            }
        }
    }

}