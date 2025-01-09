package com.ritense.panorama.externalservices.zakenapi.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "panorama.config.external-services.zakenapi")
class ZakenApiModuleConfiguration(
    var enabled: Boolean = false,
    var properties: ZakenApiConfig,
) {
    init {
        if(enabled) {
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
