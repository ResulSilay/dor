package org.dor.presentation.ext

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.dor.domain.resource.Resource

internal fun <T, S> CoroutineScope.collectResource(
    flow: suspend () -> Flow<Resource<T>>,
    state: MutableStateFlow<S>,
    mapper: S.(Resource<T>) -> S,
) {
    launch {
        flow().run {
            collectLatest { resource ->
                state.update { state -> state.mapper(resource) }
            }
        }
    }
}
