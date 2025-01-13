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

package com.ritense.panorama.service

import com.ritense.panorama.contract.AuthorizedRole
import org.springframework.security.core.Authentication
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping


class PanoramaModulesService(
    private val requestMappingHandlerMapping: RequestMappingHandlerMapping
) {
    fun getAuthorizedModules(authentication: Authentication): Map<String, List<String>> {
        val handlerMethods = requestMappingHandlerMapping.handlerMethods

        val allowedMethods =
            handlerMethods
                .filter {
                    authentication isAuthorizedFor it.value &&
                        it.value.beanType.packageName.startsWith("$PANORAMA_MODULE_PACKAGE.")
                }
                .map {
                    it.value.beanType.packageName.substringAfter("$PANORAMA_MODULE_PACKAGE.")
                        .substringBefore(".") to it.key.toString()
                }
                .groupBy { it.first }
                .mapValues { entry -> entry.value.map { it.second } }

        return allowedMethods
    }

    private infix fun Authentication.isAuthorizedFor(handlerMethod: HandlerMethod): Boolean {
        val authorities = this.authorities.map { it.authority }
        val methodRequiredRoles = handlerMethod.method.annotations.filterIsInstance<AuthorizedRole>()

        if (methodRequiredRoles.isEmpty()) return true

        return true in methodRequiredRoles.map { authorizedRole -> authorizedRole.role in authorities }
    }

    companion object {
        const val PANORAMA_MODULE_PACKAGE = "com.ritense.panorama.modules"
    }
}