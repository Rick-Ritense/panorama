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

package com.ritense.panorama.security

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken

class PanoramaTokenAuthenticationProvider(
    private val panoramaAuthorizationConfig: PanoramaAuthorizationConfig,
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication {
        val credentials = authentication?.principal?.toString()

        if (credentials == null) {
            throw BadCredentialsException("Bad Request Credentials")
        } else {
            return panoramaAuthorizationConfig
                .clients
                .singleOrNull {
                    it.apiKey == credentials
                }
                ?.let { client ->
                    ApiKeyAuthentication(
                        clientId = client.clientId,
                        apiKey = client.apiKey,
                        authorities = client.roles.map { SimpleGrantedAuthority(it) },
                    )
                }
                ?: throw BadCredentialsException("Bad Request Credentials")
        }
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return authentication == PreAuthenticatedAuthenticationToken::class.java
    }

}