package core.di

import core.data.env.HOST
import core.data.env.PATH
import core.data.env.PUBLIC_API_KEY
import core.data.exceptions.ServerException
import core.data.models.ErrorResponse
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.datetime.Clock
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val NetworkModule = module {
    single {
        HttpClient {
            expectSuccess = true
            HttpResponseValidator {
                validateResponse {
                    /*
                    * NOTE: This only intercepts successful responses.
                    */
                }
                handleResponseExceptionWithRequest { exception, _ ->
                    val clientException = exception as? ClientRequestException
                        ?: return@handleResponseExceptionWithRequest
                    /*
                    * NOTE: This only intercepts failed responses.
                    */
                    val exceptionResponse = clientException.response.body<ErrorResponse>()
                    throw ServerException(
                        message = exceptionResponse.message,
                        code = exceptionResponse.code
                    )
                }
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.d("Logger Ktor => $message")
                    }
                }
                level = LogLevel.ALL
            }

            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                url {
                    protocol = URLProtocol.HTTPS
                    host = HOST
                    path(PATH)
                    val timestamp = Clock.System.now().epochSeconds
                    parameters.append("ts", timestamp.toString())
                    parameters.append("apikey", PUBLIC_API_KEY)
                    // TODO: Implement md5Hash in Multiplatform
                    // val hash = md5Hash(timestamp.toString() + PRIVATE_API_KEY + PUBLIC_API_KEY)
                    val hash = ""
                    parameters.append("hash", hash)
                }
            }
        }
    }
}