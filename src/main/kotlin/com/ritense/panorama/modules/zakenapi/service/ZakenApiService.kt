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
package com.ritense.panorama.modules.zakenapi.service

import com.ritense.panorama.modules.zakenapi.client.ZakenApiClient
import com.ritense.panorama.modules.zakenapi.domain.ResultPage
import com.ritense.panorama.modules.zakenapi.domain.Zaak
import com.ritense.panorama.modules.zakenapi.domain.ZaakStatus
import java.util.UUID

class ZakenApiService(
    private val zakenApiClient: ZakenApiClient,
    private val catalogiApiService: CatalogiApiService
) {
    suspend fun fetchAndPopulateZaak(zaak: Zaak): Zaak {
        val zaakStatus = zaak.status?.let { getZaakStatus(it) }
        val statusOmschrijving = zaakStatus?.statustype?.let { catalogiApiService.getZaakStatusType(it).omschrijving}
        val statusGeschiedenis = fetchStatusGeschiedenis(zaak.uuid)

        return zaak.copy(
            statusOmschrijving = statusOmschrijving,
            statusGeschiedenis = statusGeschiedenis
        )
    }

    suspend fun fetchStatusGeschiedenis(zaakId: UUID): List<ZaakStatus> {
        val statusHistory = getZaakStatusHistory(zaakId)

        return statusHistory.map { status ->
            val statusOmschrijving = status.statustype.let {
                catalogiApiService.getZaakStatusType(it).omschrijving
            }
            status.copy(statusOmschrijving = statusOmschrijving)
        }
    }

    suspend fun getLopendeZakenByBurgerservicenummer(
        burgerservicenummer: String
    ): ResultPage<Zaak> {
        val result =
            zakenApiClient
                .zoeken()
                .search()
                .withBsn(burgerservicenummer)
                .isOpen(true)
                .retrieve()

        val populatedZaken = result.results.map { fetchAndPopulateZaak(it) }

        return result.copy(results = populatedZaken)
    }

    private suspend fun getZaakStatus(statusUrl: String): ZaakStatus {
        return zakenApiClient.zaakStatussen().get(extractId(statusUrl)).retrieve()
    }

    private suspend fun getZaakStatusHistory(zaakId: UUID): List<ZaakStatus> {
        return zakenApiClient.zaakStatussen().search().forZaak(zaakId).retrieveAll()
    }

    private fun extractId(url: String): UUID {
        return UUID.fromString(url.substringAfterLast("/"))
    }
}
