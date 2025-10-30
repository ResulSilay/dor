package org.dor.data.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.dor.data.remote.mapper.IPMapper
import org.dor.data.remote.service.IPService
import org.dor.data.repository.IPRepositoryImpl
import org.dor.domain.repository.IPRepository
import org.koin.dsl.module

val dataModule = module {

    single {
        HttpClient {
            install(plugin = ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                        prettyPrint = false
                        isLenient = true
                    }
                )
            }

            install(plugin = Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                       print(message)
                    }
                }
            }

            install(plugin = HttpTimeout) {
                requestTimeoutMillis = 15_000
                connectTimeoutMillis = 10_000
            }

            defaultRequest {
                url(urlString = "http://ip-api.com/")

                headers.append(
                    name = "Accept",
                    value = "application/json"
                )
            }
        }
    }

    single {
        IPService(client = get())
    }

    single {
        IPMapper()
    }

    single<IPRepository> {
        IPRepositoryImpl(api = get(), mapper = get())
    }
}
