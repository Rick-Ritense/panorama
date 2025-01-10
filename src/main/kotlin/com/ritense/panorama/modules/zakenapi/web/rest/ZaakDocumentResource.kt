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
package com.ritense.panorama.modules.zakenapi.web.rest

import com.ritense.panorama.modules.zakenapi.domain.ResultPage
import com.ritense.panorama.modules.zakenapi.domain.Zaak
import com.ritense.panorama.modules.zakenapi.service.ZakenApiService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/v1/profile"])
class ZaakDocumentResource(
    private val zakenApiService: ZakenApiService,
) {
    @GetMapping(value = ["{burgerservicenummer}/lopende-zaken"])
    suspend fun getContentStreaming(
        @PathVariable burgerservicenummer: String
    ): ResultPage<Zaak> {
        return zakenApiService
            .getLopendeZakenByBurgerservicenummer(
                burgerservicenummer
            )
    }
}