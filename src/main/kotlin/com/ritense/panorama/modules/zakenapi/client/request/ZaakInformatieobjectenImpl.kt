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
import com.ritense.panorama.modules.zakenapi.domain.ZaakDocument
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.client.awaitBody
import java.util.UUID

class ZakenInformatieobjectenImpl(val zakenApiClient: ZakenApiClient) : ZaakInformatieobjecten {
    override fun search(): SearchZaakInformatieobjecten {
        return SearchZaakInformatieobjectenImpl(zakenApiClient)
    }

    override fun get(id: UUID): GetZaakInformatieobject {
        return GetZaakInformatieobjectImpl(zakenApiClient, id)
    }
}

class GetZaakInformatieobjectImpl(val zakenApiClient: ZakenApiClient, val id: UUID) : GetZaakInformatieobject {
    override suspend fun retrieve(): ZaakDocument {
        return this.zakenApiClient.webClient.get()
            .uri("/zaken/api/v1/zaakinformatieobjecten/$id")
            .retrieve()
            .handleStatus()
            .awaitBody()
    }
}

class SearchZaakInformatieobjectenImpl(val zakenApiClient: ZakenApiClient) : SearchZaakInformatieobjecten {
    val queryParams: MultiValueMap<String, String> = LinkedMultiValueMap()

    override fun forZaak(zaakuri: String): SearchZaakInformatieobjecten {
        queryParams.add("zaak", zaakuri)
        return this
    }

    override fun forZaak(id: UUID): SearchZaakInformatieobjecten {
        queryParams.add("zaak", zakenApiClient.getZaakUrl(id))
        return this
    }

    override fun ofInformatieobject(informatieobjectUri: String): SearchZaakInformatieobjecten {
        queryParams.add("informatieobject", informatieobjectUri)
        return this
    }

    override suspend fun retrieve(): List<ZaakDocument> {
        return this.zakenApiClient.webClient.get()
            .uri { it.path("/zaken/api/v1/zaakinformatieobjecten").queryParams(queryParams).build() }
            .retrieve()
            .handleStatus()
            .awaitBody()
    }
}