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
package com.ritense.panorama.modules.zakenapi.security.config

import com.ritense.panorama.contract.HttpSecurityConfigurer
import com.ritense.panorama.modules.zakenapi.module.ZakenApiModuleRole.ZAKEN_API_GET_ZAKEN
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.GET
import org.springframework.security.config.annotation.web.builders.HttpSecurity

@Configuration
class ZaakDocumentResourceHttpSecurityConfigurer : HttpSecurityConfigurer {
    override fun configure(http: HttpSecurity) {
        http.authorizeHttpRequests { authorize ->
            authorize.requestMatchers(GET, "/api/v1/profile/lopende-zaken/{burgerservicenummer}")
                .hasAnyAuthority(ZAKEN_API_GET_ZAKEN.toString())
        }
    }
}