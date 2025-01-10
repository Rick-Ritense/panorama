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

package com.ritense.panorama.modules.haalcentraalbrp.domain

data class VerblijfplaatsBuitenland(
    override val type: String = "VerblijfplaatsBuitenland",
    val verblijfadres: Any? = null,
    val datumVan: AbstractDatum? = null,
    val datumIngangGeldigheid: Any? = null,
    val inOnderzoek: Any? = null,
) : AbstractVerblijfplaats {
    data class VerblijfadresBuitenland(
        val regel1: String? = null,
        val regel2: String? = null,
        val regel3: String? = null,
        val land: Waardetabel? = null,
        val inOnderzoek: VerblijfadresBuitenlandInOnderzoek? = null,
    ) {
        data class VerblijfadresBuitenlandInOnderzoek(
            val datumIngangOnderzoek: AbstractDatum? = null,
            val regel1: Boolean? = null,
            val regel2: Boolean? = null,
            val regel3: Boolean? = null,
            val land: Boolean? = null,
        )
    }
}