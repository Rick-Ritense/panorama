/*
 * Copyright 2025 Ritense BV, the Netherlands.
 *
 * Licensed under EUPL, Version 1.2 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-eupl-12
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ritense.panorama.modules.zakenapi.client

import com.ritense.panorama.modules.zakenapi.autoconfigure.ZakenApiModuleConfiguration
import com.ritense.panorama.modules.zakenapi.service.IdTokenGenerator
import io.netty.handler.logging.LogLevel
import com.ritense.panorama.modules.zakenapi.client.request.ZaakStatussen
import com.ritense.panorama.modules.zakenapi.client.request.ZaakStatussenImpl
import com.ritense.panorama.modules.zakenapi.client.request.Zaken
import com.ritense.panorama.modules.zakenapi.client.request.ZakenImpl
import com.ritense.panorama.modules.zakenapi.client.request.ZoekenImpl
import org.springframework.http.HttpStatus
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient
import reactor.netty.transport.logging.AdvancedByteBufFormat

class ZakenApiClient(
    private val zakenApiConfig: ZakenApiModuleConfiguration,
    webClientBuilder: WebClient.Builder,
) {
    val webClient: WebClient

    init {
        this.webClient =
            webClientBuilder
                .clone()
                .baseUrl(zakenApiConfig.properties.url!!)
                .clientConnector(
                    ReactorClientHttpConnector(
                        HttpClient.create().wiretap(
                            "reactor.netty.http.client.HttpClient",
                            LogLevel.TRACE, AdvancedByteBufFormat.TEXTUAL,
                        ),
                    ),
                )
                .filter(
                    ExchangeFilterFunction.ofRequestProcessor {
                        Mono.just(
                            ClientRequest.from(it).header("Authorization", "Bearer ${getToken()}").build(),
                        )
                    },
                )
                .defaultHeader("Accept-Crs", "EPSG:4326")
                .defaultHeader("Content-Crs", "EPSG:4326")
                .build()
    }

    private fun getToken(): String {
        return IdTokenGenerator().generateToken(
            zakenApiConfig.properties.secret!!,
            zakenApiConfig.properties.clientId!!,
        )
    }

    fun getZaakUrl(zaakId: Any): String {
        return "${zakenApiConfig.properties.url}/zaken/api/v1/zaken/$zaakId"
    }

    fun zaken(): Zaken {
        return ZakenImpl(this)
    }

    fun zoeken(): Zaken {
        return ZoekenImpl(this)
    }

    fun zaakStatussen(): ZaakStatussen {
        return ZaakStatussenImpl(this)
    }
}

fun WebClient.ResponseSpec.handleStatus() =
    this
        .onStatus(
            { httpStatus -> HttpStatus.NOT_FOUND == httpStatus },
            { throw ResponseStatusException(HttpStatus.NOT_FOUND) },
        )
        .onStatus(
            { httpStatus -> HttpStatus.UNAUTHORIZED == httpStatus },
            { throw ResponseStatusException(HttpStatus.UNAUTHORIZED) },
        )
        .onStatus(
            { httpStatus -> HttpStatus.INTERNAL_SERVER_ERROR == httpStatus },
            {
                throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)
            },
        )