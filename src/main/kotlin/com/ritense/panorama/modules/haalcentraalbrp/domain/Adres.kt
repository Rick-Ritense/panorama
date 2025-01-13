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

data class Adres(
    override val type: String = "Adres",
    val verblijfadres: VerblijfadresBinnenland? = null,
    val functieAdres: Waardetabel? = null,
    val adresseerbaarObjectIdentificatie: String? = null,
    val nummeraanduidingIdentificatie: String? = null,
    val datumVan: AbstractDatum? = null,
    val datumIngangGeldigheid: AbstractDatum? = null,
    val indicatieVastgesteldVerblijftNietOpAdres: Boolean? = null,
    val inOnderzoek: AdresInOnderzoek? = null,
) : AbstractVerblijfplaats {
    data class AdresInOnderzoek(
        val datumIngangOnderzoek: AbstractDatum? = null,
        val functieAdres: Boolean? = null,
        val adresseerbaarObjectIdentificatie: Boolean? = null,
        val nummeraanduidingIdentificatie: Boolean? = null,
        val datumVan: Boolean? = null,
        val datumIngangGeldigheid: Boolean? = null,
    )

    data class VerblijfadresBinnenland(
        val officieleStraatnaam: String? = null,
        val korteStraatnaam: String? = null,
        val huisnummer: Int? = null,
        val huisletter: String? = null,
        val huisnummertoevoeging: String? = null,
        val aanduidingBijHuisnummer: Waardetabel? = null,
        val postcode: String? = null,
        val woonplaats: String? = null,
        val inOnderzoek: VerblijfadresBinnenlandInOnderzoek? = null,
    ) {
        data class VerblijfadresBinnenlandInOnderzoek(
            val datumIngangOnderzoek: AbstractDatum? = null,
            val officieleStraatnaam: Boolean? = null,
            val korteStraatnaam: Boolean? = null,
            val huisnummer: Boolean? = null,
            val huisletter: Boolean? = null,
            val huisnummertoevoeging: Boolean? = null,
            val aanduidingBijHuisnummer: Boolean? = null,
            val postcode: Boolean? = null,
            val woonplaats: Boolean? = null,
        )
    }
}