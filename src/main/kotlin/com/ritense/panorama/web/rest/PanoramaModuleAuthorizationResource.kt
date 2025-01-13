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

package com.ritense.panorama.web.rest

import com.ritense.panorama.service.PanoramaModulesService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api/v1/authorization"])
class PanoramaModuleAuthorizationResource(
    private val panoramaModulesService: PanoramaModulesService
) {

    @GetMapping("/modules")
    fun getAuthorizedModules(
        authentication: Authentication,
    ): ResponseEntity<Map<String, List<String>>> {
        return ResponseEntity.ok(panoramaModulesService.getAuthorizedModules(authentication))
    }
}