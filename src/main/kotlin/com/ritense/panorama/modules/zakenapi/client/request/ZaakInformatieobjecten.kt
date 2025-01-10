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

import com.ritense.panorama.modules.zakenapi.domain.ZaakDocument
import java.util.UUID

interface ZaakInformatieobjecten {
    fun search(): SearchZaakInformatieobjecten

    fun get(id: UUID): GetZaakInformatieobject
}

interface SearchZaakInformatieobjecten : Retrieve<List<ZaakDocument>> {
    fun forZaak(zaakUri: String): SearchZaakInformatieobjecten

    fun forZaak(id: UUID): SearchZaakInformatieobjecten

    fun ofInformatieobject(informatieobjectUri: String): SearchZaakInformatieobjecten
}

interface GetZaakInformatieobject : Retrieve<ZaakDocument>