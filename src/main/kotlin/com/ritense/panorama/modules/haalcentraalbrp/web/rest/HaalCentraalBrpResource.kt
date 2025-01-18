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

package com.ritense.panorama.modules.haalcentraalbrp.web.rest

import com.ritense.panorama.contract.AuthorizedRole
import com.ritense.panorama.modules.haalcentraalbrp.client.RaadpleegMetBurgerservicenummerField
import com.ritense.panorama.modules.haalcentraalbrp.domain.Persoon
import com.ritense.panorama.modules.haalcentraalbrp.service.HaalCentraalBrpService
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api/v1/profile"])
@ConditionalOnProperty(
    prefix = "panorama.config.modules.haalcentraal-brp",
    name = ["enabled"],
    havingValue = "true"
)
class HaalCentraalBrpResource(private val haalCentraalBrpService: HaalCentraalBrpService) {


    @AuthorizedRole("HAALCENTRAAL_BRP_GET_PERSOON")
    @GetMapping("/{burgerservicenummer}/persoon")
    suspend fun getPersoon(
        @PathVariable("burgerservicenummer") bsn: String,
    ): ResponseEntity<Persoon?> {
        val result = haalCentraalBrpService.findPersoonByBurgerservicenummer(bsn)

        return ResponseEntity.ok().body(result)
    }

    @AuthorizedRole("HAALCENTRAAL_BRP_GET_PERSONEN")
    @GetMapping("/personen")
    suspend fun getPersonen(
        @RequestParam("burgerservicenummers") burgerservicenummers: List<String> = emptyList(),
        @RequestParam("fields") fields: List<String> = RaadpleegMetBurgerservicenummerField.asList(),
    ): ResponseEntity<List<Persoon>?> {
        if (burgerservicenummers.isEmpty()) {
            return ResponseEntity.badRequest().body(null)
        }
        if (fields.isEmpty()) {
            return ResponseEntity.badRequest().body(null)
        }

        val requestedFields =
            runCatching {
                fields.map {
                    RaadpleegMetBurgerservicenummerField.fromFieldKey(it)
                }
            }
                .getOrElse {
                    return ResponseEntity.badRequest().build()
                }
                .filterNotNull()

        val result = haalCentraalBrpService.findPersonenByBurgerservicenummer(burgerservicenummers, requestedFields)

        return ResponseEntity.ok().body(result)
    }
}