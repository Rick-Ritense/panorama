/*
 * Copyright 2024 Ritense BV, the Netherlands.
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
package com.ritense.panorama.externalservices.haalcentraalbrp.autoconfiguration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "panorama.config.external-services.haalcentraal-brp")
data class HaalCentraalBrpModuleConfiguration(
    var enabled: Boolean = false,
    var properties: HaalCentraalBrpConfig,
) {
    init {
        if (enabled) {
            requireNotNull(properties.apiUrl) {
                "Haal Centraal BRP API URL not configured"
            }
            requireNotNull(properties.apiKey) {
                "Haal Centraal BRP API token not configured"
            }
        }
    }

    data class HaalCentraalBrpConfig(
        var apiUrl: String = "",
        val apiKey: String? = null,
    )
}