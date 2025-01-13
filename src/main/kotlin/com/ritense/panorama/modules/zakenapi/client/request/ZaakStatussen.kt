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

import com.ritense.panorama.modules.zakenapi.domain.ZaakStatus
import java.util.UUID

interface ZaakStatussen {
    fun search(): SearchZaakStatussen

    fun get(id: UUID): GetZaakStatus
}

interface SearchZaakStatussen : PagedRetrieve<SearchZaakStatussen, ZaakStatus> {
    fun forZaak(zaakUrl: String): SearchZaakStatussen

    fun forZaak(zaakId: UUID): SearchZaakStatussen

    fun forStatustype(statustype: String): SearchZaakStatussen
}

interface GetZaakStatus : Retrieve<ZaakStatus>