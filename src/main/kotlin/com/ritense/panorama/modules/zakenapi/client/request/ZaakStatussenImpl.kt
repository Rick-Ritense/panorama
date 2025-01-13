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

package com.ritense.panorama.modules.zakenapi.client.request

import com.ritense.panorama.modules.zakenapi.client.ZakenApiClient
import com.ritense.panorama.modules.zakenapi.client.handleStatus
import com.ritense.panorama.modules.zakenapi.domain.ResultPage
import com.ritense.panorama.modules.zakenapi.domain.ZaakStatus
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.client.awaitBody
import java.util.UUID

class ZaakStatussenImpl(val zakenApiClient: ZakenApiClient) : ZaakStatussen {
    override fun search(): SearchZaakStatussen {
        return SearchZaakStatussenImpl(zakenApiClient)
    }

    override fun get(id: UUID): GetZaakStatus {
        return GetZaakStatussenImpl(zakenApiClient, id)
    }
}

class GetZaakStatussenImpl(val zakenApiClient: ZakenApiClient, val id: UUID) : GetZaakStatus {
    override suspend fun retrieve(): ZaakStatus {
        return this.zakenApiClient.webClient.get()
            .uri("/zaken/api/v1/statussen/$id")
            .retrieve()
            .handleStatus()
            .awaitBody()
    }
}

class SearchZaakStatussenImpl(val zakenApiClient: ZakenApiClient) : SearchZaakStatussen {
    val queryParams: MultiValueMap<String, String> = LinkedMultiValueMap()

    override fun forZaak(zaakUrl: String): SearchZaakStatussen {
        queryParams.add("zaak", zaakUrl)
        return this
    }

    override fun forZaak(zaakId: UUID): SearchZaakStatussen {
        queryParams.add("zaak", zakenApiClient.getZaakUrl(zaakId))
        return this
    }

    override fun forStatustype(statustype: String): SearchZaakStatussen {
        queryParams.add("statustype", statustype)
        return this
    }

    override fun page(page: Int): SearchZaakStatussen {
        queryParams.add("page", page.toString())
        return this
    }

    override suspend fun retrieve(): ResultPage<ZaakStatus> {
        return this.zakenApiClient.webClient.get()
            .uri { it.path("/zaken/api/v1/statussen").queryParams(queryParams).build() }
            .retrieve()
            .handleStatus()
            .awaitBody()
    }
}