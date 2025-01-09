/*
 * Copyright 2015-2023 Ritense BV, the Netherlands.
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
package com.ritense.panorama.externalservices.zakenapi.autoconfigure

import com.ritense.panorama.externalservices.zakenapi.security.config.HttpSecurityConfigurer
import com.ritense.panorama.externalservices.zakenapi.client.ZakenApiClient
import com.ritense.panorama.externalservices.zakenapi.security.config.ZaakDocumentResourceHttpSecurityConfigurer
import com.ritense.panorama.externalservices.zakenapi.service.ZakenApiService
import com.ritense.panorama.externalservices.zakenapi.web.rest.ZaakDocumentResource
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@EnableConfigurationProperties(
    ZakenApiModuleConfiguration::class,
)
@Configuration
@ConditionalOnProperty(
    prefix = "panorama.config.external-services.zakenapi",
    name = ["enabled"],
    havingValue = "true"
)
class ZakenApiAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(ZakenApiService::class)
    fun zakenApiService(
        zakenApiClient: ZakenApiClient
    ): ZakenApiService {
        return ZakenApiService(
            zakenApiClient
        )
    }

    @Bean
    fun zakenApiConfig(): ZakenApiModuleConfiguration.ZakenApiConfig {
        return ZakenApiModuleConfiguration.ZakenApiConfig()
    }

    @Bean
    fun zakenApiClient(
        zakenApiConfig: ZakenApiModuleConfiguration,
        webClientBuilder: WebClient.Builder,
    ): ZakenApiClient {
        return ZakenApiClient(zakenApiConfig, webClientBuilder)
    }

    @Bean
    @ConditionalOnMissingBean(ZaakDocumentResource::class)
    fun zaakDocumentResource(zakenApiService: ZakenApiService): ZaakDocumentResource {
        return ZaakDocumentResource(zakenApiService)
    }

    @Bean
    @ConditionalOnMissingBean(ZaakDocumentResourceHttpSecurityConfigurer::class)
    fun zaakDocumentResourceHttpSecurityConfigurer(): HttpSecurityConfigurer {
        return ZaakDocumentResourceHttpSecurityConfigurer()
    }
}
