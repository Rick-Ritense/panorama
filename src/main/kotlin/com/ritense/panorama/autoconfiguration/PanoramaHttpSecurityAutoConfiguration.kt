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

import com.ritense.panorama.contract.HttpSecurityConfigurer
import com.ritense.panorama.security.PanoramaAuthorizationConfig
import com.ritense.panorama.security.PanoramaTokenAuthenticationProvider
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter
import org.springframework.security.web.header.HeaderWriterFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@EnableWebSecurity
@EnableConfigurationProperties(PanoramaAuthorizationConfig::class)
@Configuration
class PanoramaHttpSecurityAutoConfiguration {
    @Bean
    fun panoramaTokenAuthenticationProvider(
        panoramaAuthorizationConfig: PanoramaAuthorizationConfig,
    ): AuthenticationProvider {
        return PanoramaTokenAuthenticationProvider(panoramaAuthorizationConfig)
    }

    @Bean
    fun panoramaAuthenticationManager(
        authenticationProviders: List<AuthenticationProvider>,
    ): AuthenticationManager =
        ProviderManager(authenticationProviders)

    @Bean
    fun filterChain(
        httpSecurity: HttpSecurity,
        authenticationManager: AuthenticationManager,
        httpSecurityConfigurers: List<HttpSecurityConfigurer>
    ): SecurityFilterChain {
        httpSecurityConfigurers.forEach { it.configure(httpSecurity) }

        return httpSecurity
            .cors {
                it.configurationSource(corsConfigurationSource())
            }
            .csrf { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .addFilterAfter(requestHeaderAuthenticationFilter(authenticationManager), HeaderWriterFilter::class.java)
            .build()
    }

    fun requestHeaderAuthenticationFilter(
        panoramaAuthenticationManager: AuthenticationManager
    ): RequestHeaderAuthenticationFilter {
        return RequestHeaderAuthenticationFilter().apply {
            setPrincipalRequestHeader("X-API-KEY")
            setExceptionIfHeaderMissing(false)
            setRequiresAuthenticationRequestMatcher(
                AntPathRequestMatcher("/api/**")
            )
            setAuthenticationManager(panoramaAuthenticationManager)
        }
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("http://localhost:4200")
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
        configuration.allowedHeaders = listOf("Authorization", "Content-Type", "X-API-KEY")
        configuration.allowCredentials = true

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}
