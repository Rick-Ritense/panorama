package com.ritense.panorama.externalservices.haalcentraalbrp.autoconfiguration

import com.ritense.panorama.externalservices.haalcentraalbrp.client.HaalCentraalBrpClient
import com.ritense.panorama.externalservices.haalcentraalbrp.client.HaalCentraalClientProvider
import com.ritense.panorama.externalservices.haalcentraalbrp.service.HaalCentraalBrpService
import com.ritense.panorama.externalservices.haalcentraalbrp.web.rest.HaalCentraalBrpResource
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@EnableConfigurationProperties(
    HaalCentraalBrpModuleConfiguration::class,
)
@Configuration
@ConditionalOnProperty(
    prefix = "panorama.config.external-services.haalcentraal-brp",
    name = ["enabled"],
    havingValue = "true"
)
class HaalCentraalBrpAutoConfiguration {

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