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

import mu.KotlinLogging
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClientResponseException
import org.springframework.web.reactive.function.client.awaitBody

class HaalCentraalBrpClient(
    private val haalCentraalClientProvider: HaalCentraalClientProvider,
) {
    suspend fun <T : PersoonRequest> zoekPersonen(
        zoekPersonenRequest: T
    ): ZoekPersonenResponse? {
        try {
            return haalCentraalClientProvider.webClient()
                .post()
                .uri { builder ->
                    builder
                        .path("/brp/personen")
                        .build()
                }
                .body(BodyInserters.fromValue(zoekPersonenRequest))
                .retrieve()
                .awaitBody()
        } catch (ex: WebClientResponseException) {
            logger.error("${ex.statusCode}: ${ex.responseBodyAsString}", ex)
            return null
        }
    }

    companion object {
        private val logger = KotlinLogging.logger {}
    }
}