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

data class NaamPersoonInOnderzoek(
    val datumIngangOnderzoek: AbstractDatum? = null,
    val voornamen: Boolean? = null,
    val adellijkeTitelPredicaat: Boolean? = null,
    val voorvoegsel: Boolean? = null,
    val geslachtsnaam: Boolean? = null,
    val voorletters: Boolean? = null,
    val aanduidingNaamgebruik: Boolean? = null,
    val volledigeNaam: Boolean? = null,
)