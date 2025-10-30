package org.dor.presentation.di

import org.dor.presentation.screen.search.SearchViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val viewModelModule = module {
    factoryOf(::SearchViewModel)
}
