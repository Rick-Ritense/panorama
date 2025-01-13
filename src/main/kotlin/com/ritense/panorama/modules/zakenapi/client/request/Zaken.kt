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

import com.ritense.panorama.modules.zakenapi.domain.Zaak
import java.util.UUID

interface Zaken {
    fun search(): SearchZaken

    fun get(id: UUID): GetZaak
}

interface SearchZaken : PagedRetrieve<SearchZaken, Zaak>, AuthenticationFilter<SearchZaken> {
    fun ofZaakType(zaakType: String): SearchZaken

//    fun ofZaakTypes(zaakType: List<UUID>): SearchZaken

    fun isOpen(open: Boolean): SearchZaken

    fun ofIdentificatie(identificatie: String): SearchZaken

    fun ofVestigingsNummer(vestigingNummer: String): SearchZaken
}

interface GetZaak : Retrieve<Zaak>