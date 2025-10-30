package org.dor.presentation.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.dor.domain.resource.Resource
import org.dor.domain.usecase.GetIPAddressUseCase
import org.dor.domain.usecase.GetIPInfoUseCase
import org.dor.presentation.ext.collectResource

class SearchViewModel(
    private val getIPAddress: GetIPAddressUseCase,
    private val getIPInfo: GetIPInfoUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SearchUiState())
    internal val state: StateFlow<SearchUiState> = _state

    init {
        fetchIPAddress()
    }

    private fun fetchIPAddress() {
        viewModelScope.launch {
            delay(timeMillis = 2000)

            getIPAddress().collectLatest { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _state.update { state ->
                            state.copy(ipAddress = resource.data.ipAddress)
                        }

                        fetchIPInfo(ipAddress = resource.data.ipAddress)
                    }

                    else -> Unit
                }
            }
        }
    }

    private fun fetchIPInfo(ipAddress: String) {
        viewModelScope.collectResource(
            flow = { getIPInfo(ipAddress = ipAddress) },
            state = _state
        ) { resource ->
            when (resource) {
                is Resource.Loading -> copy(isLoading = resource.isLoading)

                is Resource.Success -> copy(ipInfo = resource.data)

                is Resource.Error -> copy(errorMessage = resource.message)
            }
        }
    }

    internal fun search(ipAddress: String) {
        _state.update { state ->
            state.copy(ipAddress = ipAddress)
        }

        fetchIPInfo(ipAddress = ipAddress)
    }
}
