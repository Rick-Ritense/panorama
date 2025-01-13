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

package com.ritense.panorama.modules.zakenapi.autoconfigure

import com.ritense.panorama.contract.PanoramaModuleConfiguration
import com.ritense.panorama.modules.zakenapi.module.ZakenApiModuleRole
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "panorama.config.modules.zakenapi")
class ZakenApiModuleConfiguration(
    var enabled: Boolean = false,
    var properties: ZakenApiConfig,
    override val moduleRoles: List<ZakenApiModuleRole> = ZakenApiModuleRole.entries,
) : PanoramaModuleConfiguration {
    init {
        if (enabled) {
            requireNotNull(properties.url) {
                "ZakenApi url is not configured"
            }
            requireNotNull(properties.clientId) {
                "ZakenApi clientId is not configured"
            }
            requireNotNull(properties.secret) {
                "ZakenApi secret is not configured"
            }
        }
    }

    data class ZakenApiConfig(
        var url: String? = "",
        var clientId: String? = "",
        var secret: String? = ""
    )
}