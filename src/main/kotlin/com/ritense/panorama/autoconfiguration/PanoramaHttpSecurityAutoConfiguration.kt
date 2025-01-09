package com.ritense.panorama.autoconfiguration

import com.ritense.panorama.security.PanoramaTokenAuthenticationProvider
import org.springframework.beans.factory.annotation.Value
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

@EnableWebSecurity
@Configuration
class PanoramaHttpSecurityAutoConfiguration {
    @Bean
    fun panoramaTokenAuthenticationProvider(
        @Value("\${panorama.config.authentication.api-key}") panoramaApiKey: String,
    ): AuthenticationProvider {
        return PanoramaTokenAuthenticationProvider(panoramaApiKey)
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
    ): SecurityFilterChain {
        return httpSecurity
            .cors { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.NEVER)
            }
            .addFilterAfter(requestHeaderAuthenticationFilter(authenticationManager), HeaderWriterFilter::class.java)
            .authorizeHttpRequests {
                it.anyRequest().authenticated()
            }
            .build()
    }

    fun requestHeaderAuthenticationFilter(
        panoramaAuthenticationManager: AuthenticationManager
    ): RequestHeaderAuthenticationFilter {
        return RequestHeaderAuthenticationFilter().apply {
            setPrincipalRequestHeader("X-API-KEY")
            setRequiresAuthenticationRequestMatcher(
                AntPathRequestMatcher("/api/**")
            )
            setAuthenticationManager(panoramaAuthenticationManager)
        }
    }
}
