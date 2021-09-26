package com.github.danishjamal104.pizzaordy.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.danishjamal104.pizzaordy.data.helper.ServiceResult
import com.github.danishjamal104.pizzaordy.data.model.Pizza
import com.github.danishjamal104.pizzaordy.data.repository.HomeRepository
import com.github.danishjamal104.pizzaordy.ui.EventInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val homeRepository: HomeRepository
): ViewModel(), EventInterface<HomeEvent> {

    private val _homeState = MutableLiveData<HomeState<Pizza>>()
    val authState: LiveData<HomeState<Pizza>>
        get() = _homeState

    override fun setEvent(event: HomeEvent) {
        viewModelScope.launch {
            when(event) {
                HomeEvent.GetPizza -> getPizza()
            }
        }
    }

    suspend fun getPizza() {
        _homeState.value = HomeState.Loading
        val result = homeRepository.getPizza()
        if(result is ServiceResult.Error) {
            postError(result.exception)
        } else {
            postState(HomeState.DataLoaded(
                (result as ServiceResult.Success).data
                )
            )
        }
    }

    private fun postError(exception: Exception) {
        _homeState.postValue(HomeState.Error("" + exception.localizedMessage))
    }

    private fun postState(homeState: HomeState<Pizza>) {
        _homeState.postValue(homeState)
    }

}

sealed class HomeState<out T> {
    object Loading: HomeState<Nothing>()
    data class DataLoaded<out T>(val pizza: Pizza): HomeState<T>()
    data class Error(val friendlyErrorMessage: String): HomeState<Nothing>()
}

sealed class HomeEvent {
    object GetPizza: HomeEvent()
}