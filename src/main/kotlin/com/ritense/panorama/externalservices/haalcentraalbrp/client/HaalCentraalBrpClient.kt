package com.ritense.panorama.externalservices.haalcentraalbrp.client

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