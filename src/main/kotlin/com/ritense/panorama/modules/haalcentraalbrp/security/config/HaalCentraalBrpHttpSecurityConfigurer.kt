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
package com.ritense.panorama.modules.haalcentraalbrp.security.config

import com.ritense.panorama.contract.HttpSecurityConfigurer
import com.ritense.panorama.modules.haalcentraalbrp.module.HaalCentraalModuleRole.HAALCENTRAAL_BRP_GET_PERSONEN
import com.ritense.panorama.modules.haalcentraalbrp.module.HaalCentraalModuleRole.HAALCENTRAAL_BRP_GET_PERSOON
import org.springframework.http.HttpMethod.GET
import org.springframework.security.config.annotation.web.builders.HttpSecurity

class HaalCentraalBrpHttpSecurityConfigurer : HttpSecurityConfigurer {
    override fun configure(http: HttpSecurity) {
        http.authorizeHttpRequests { auth ->
            auth.requestMatchers(GET, "/api/v1/profile/{burgerservicenummer}/persoon")
                .hasAnyAuthority(HAALCENTRAAL_BRP_GET_PERSOON.toString())
            auth.requestMatchers(GET, "/api/v1/profile/personen")
                .hasAnyAuthority(HAALCENTRAAL_BRP_GET_PERSONEN.toString())
        }
    }
}