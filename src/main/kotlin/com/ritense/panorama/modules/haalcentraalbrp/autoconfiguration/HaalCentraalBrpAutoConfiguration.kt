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

package com.ritense.panorama.modules.haalcentraalbrp.autoconfiguration

import com.ritense.panorama.modules.haalcentraalbrp.client.HaalCentraalBrpClient
import com.ritense.panorama.modules.haalcentraalbrp.client.HaalCentraalClientProvider
import com.ritense.panorama.modules.haalcentraalbrp.security.config.HaalCentraalBrpHttpSecurityConfigurer
import com.ritense.panorama.modules.haalcentraalbrp.service.HaalCentraalBrpService
import com.ritense.panorama.modules.haalcentraalbrp.web.rest.HaalCentraalBrpResource
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@EnableConfigurationProperties(
    HaalCentraalBrpModuleConfiguration::class,
)
@Configuration
@ConditionalOnProperty(
    prefix = "panorama.config.modules.haalcentraal-brp",
    name = ["enabled"],
    havingValue = "true"
)
class HaalCentraalBrpAutoConfiguration {

    @Bean
    fun haalCentraalBrpHttpSecurityConfigurer(): HaalCentraalBrpHttpSecurityConfigurer {
        return HaalCentraalBrpHttpSecurityConfigurer()
    }

    @Bean
    fun haalCentraalClientProvider(
        haalCentraalBrpServiceConfig: HaalCentraalBrpModuleConfiguration
    ): HaalCentraalClientProvider {
        return HaalCentraalClientProvider(haalCentraalBrpServiceConfig)
    }

    @Bean
    fun haalCentraalBrpClient(haalCentraalClientProvider: HaalCentraalClientProvider): HaalCentraalBrpClient {
        return HaalCentraalBrpClient(haalCentraalClientProvider)
    }

    @Bean
    fun haalCentraalBrpService(haalCentraalBrpClient: HaalCentraalBrpClient): HaalCentraalBrpService {
        return HaalCentraalBrpService(haalCentraalBrpClient)
    }

    @Bean
    fun haalCentraalBrpResource(
        haalCentraalBrpService: HaalCentraalBrpService
    ): HaalCentraalBrpResource {
        return HaalCentraalBrpResource(haalCentraalBrpService)
    }
}