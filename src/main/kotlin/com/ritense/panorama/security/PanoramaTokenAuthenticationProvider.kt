package com.ritense.panorama.security

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken

class PanoramaTokenAuthenticationProvider(
    private val panoramaApiKey: String
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication? {
        val credentials = authentication?.principal?.toString()

        if (credentials == null) {
            throw BadCredentialsException("Bad Request Header Credentials")
        } else {
            if (credentials == panoramaApiKey) {
                return ApiKeyAuthentication(
                    credentials,
                    emptyList(),
                )
            } else {
                return null
            }
        }
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return authentication == PreAuthenticatedAuthenticationToken::class.java
    }

}