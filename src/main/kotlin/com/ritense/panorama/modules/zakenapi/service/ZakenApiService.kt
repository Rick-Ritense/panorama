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

class ZakenApiService(
    private val zakenApiClient: ZakenApiClient
) {
    suspend fun getLopendeZakenByBurgerservicenummer(
        burgerservicenummer: String
    ): ResultPage<Zaak> {
        val request =
            zakenApiClient
                .zoeken()
                .search()
                .withBsn(burgerservicenummer)
                .isOpen(true)

        return request.retrieve()
    }
}