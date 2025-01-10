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
package com.ritense.panorama.modules.haalcentraalbrp.client

import com.ritense.panorama.modules.haalcentraalbrp.autoconfiguration.HaalCentraalBrpModuleConfiguration
import io.netty.handler.logging.LogLevel
import mu.KLogger
import mu.KotlinLogging
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.transport.logging.AdvancedByteBufFormat

class HaalCentraalClientProvider(
    private val haalCentraalBrpServiceConfig: HaalCentraalBrpModuleConfiguration,
) {
    fun webClient(): WebClient {
        return WebClient.builder()
            .clientConnector(
                ReactorClientHttpConnector(
                    HttpClient.create().wiretap(
                        "reactor.netty.http.client.HttpClient",
                        LogLevel.TRACE,
                        AdvancedByteBufFormat.TEXTUAL,
                    ),
                ),
            )
            .baseUrl(haalCentraalBrpServiceConfig.properties.apiUrl)
            .apply { builder ->
                haalCentraalBrpServiceConfig.properties.apiKey?.let { key ->
                    builder.defaultHeader("X-API-KEY", key)
                    logger.debug { "X-API-KEY was set for Haal Centraal client" }
                }
            }
            .build()
    }

    companion object {
        private val logger: KLogger = KotlinLogging.logger {}
    }
}