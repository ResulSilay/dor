package org.dor.domain.di

import org.dor.domain.usecase.GetIPAddressUseCase
import org.dor.domain.usecase.GetIPInfoUseCase
import org.koin.dsl.module

val domainModule = module {

    single {
        GetIPAddressUseCase(repository = get())
    }

    single {
        GetIPInfoUseCase(repository = get())
    }
}