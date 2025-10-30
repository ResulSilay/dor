package org.dor.presentation.screen.search

import org.dor.domain.model.IPInfo

internal data class SearchUiState(
    val ipAddress: String = "",
    val ipInfo: IPInfo = IPInfo(),
    val errorMessage: String = "",
    val isLoading: Boolean = true
)
