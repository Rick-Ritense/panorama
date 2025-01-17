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

package com.ritense.panorama.autoconfiguration

import com.ritense.panorama.security.config.PanoramaHttpSecurityConfigurer
import com.ritense.panorama.service.PanoramaModulesService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping

@Configuration
class PanoramaAutoConfiguration {

    @Bean
    fun panoramaHttpSecurityConfigurer(): PanoramaHttpSecurityConfigurer {
        return PanoramaHttpSecurityConfigurer()
    }

    @Bean
    fun panoramaModulesService(
        requestMappingHandlerMapping: RequestMappingHandlerMapping
    ): PanoramaModulesService {
        return PanoramaModulesService(requestMappingHandlerMapping)
    }
}