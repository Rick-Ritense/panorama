package com.ritense.panorama.security

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class ApiKeyAuthentication(
    val apiKey: String,
    authorities: List<GrantedAuthority>
): AbstractAuthenticationToken(authorities) {

    init {
        isAuthenticated = true
    }

    override fun getCredentials(): Any { return apiKey }
    override fun getPrincipal(): Any {
        return "ApiKey"
    }
}