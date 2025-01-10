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
import com.ritense.panorama.modules.zakenapi.domain.Zaak
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.client.awaitBody
import java.util.UUID

class ZakenImpl(val zakenApiClient: ZakenApiClient) : Zaken {
    override fun search(): SearchZaken {
        return SearchZakenImpl(zakenApiClient)
    }

    override fun get(id: UUID): GetZaak {
        return GetZaakImpl(zakenApiClient, id)
    }
}

class GetZaakImpl(val zakenApiClient: ZakenApiClient, val id: UUID) : GetZaak {
    override suspend fun retrieve(): Zaak {
        return zakenApiClient.webClient
            .get()
            .uri("/zaken/api/v1/zaken/$id")
            .retrieve()
            .handleStatus()
            .awaitBody()
    }
}

class SearchZakenImpl(val zakenApiClient: ZakenApiClient) : SearchZaken {
    val queryParams: MultiValueMap<String, String> = LinkedMultiValueMap()

    override fun withBsn(bsn: String): SearchZaken {
        queryParams.add("rol__betrokkeneIdentificatie__natuurlijkPersoon__inpBsn", bsn)
        return this
    }

    override fun withKvk(kvk: String): SearchZaken {
        queryParams.add("rol__betrokkeneIdentificatie__nietNatuurlijkPersoon__annIdentificatie", kvk)
        return this
    }

    override fun withUid(uid: String): SearchZaken {
        queryParams.add("rol__betrokkeneIdentificatie__natuurlijkPersoon__anpIdentificatie", uid)
        return this
    }

    override fun isOpen(open: Boolean): SearchZaken {
        queryParams.add("einddatum__isnull", open.toString())
        return this
    }

    override fun ofZaakType(zaakType: String): SearchZaken {
        queryParams.add("zaaktype", zaakType)
        return this
    }

    override fun ofIdentificatie(identificatie: String): SearchZaken {
        queryParams.add("identificatie", identificatie)
        return this
    }

    override fun ofVestigingsNummer(vestigingsNummer: String): SearchZaken {
        queryParams.add("rol__betrokkeneIdentificatie__vestiging__vestigingsNummer", vestigingsNummer)
        return this
    }

    override fun page(page: Int): SearchZaken {
        queryParams.add("page", page.toString())
        return this
    }

    override suspend fun retrieve(): ResultPage<Zaak> {
        return this.zakenApiClient.webClient.get()
            .uri { it.path("/zaken/api/v1/zaken").queryParams(queryParams).build() }
            .retrieve()
            .handleStatus()
            .awaitBody()
    }
}