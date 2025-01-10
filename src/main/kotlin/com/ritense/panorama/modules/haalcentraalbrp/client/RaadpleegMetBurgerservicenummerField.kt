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

package com.ritense.panorama.modules.haalcentraalbrp.client

import com.fasterxml.jackson.annotation.JsonValue

enum class RaadpleegMetBurgerservicenummerField(@JsonValue private val fieldKey: String) {
    A_NUMMER("aNummer"),
    ADRESSERING("adressering"),
    BURGERSERVICENUMMER("burgerservicenummer"),
    DATUM_EERSTE_INSCHRIJVING_GBA("datumEersteInschrijvingGBA"),
    DATUM_INSCHRIJVING_IN_GEMEENTE("datumInschrijvingInGemeente"),
    EUROPEES_KIESRECHT("europeesKiesrecht"),
    GEBOORTE("geboorte"),
    GEMEENTE_VAN_INSCHRIJVING("gemeenteVanInschrijving"),
    GESLACHT("geslacht"),
    GEZAG("gezag"),
    IMMIGRATIE("immigratie"),
    INDICATIE_CURATELE_REGISTER("indicatieCurateleRegister"),
    INDICATIE_GEZAG_MINDERJARIGE("indicatieGezagMinderjarige"),
    KINDEREN("kinderen"),
    LEEFTIJD("leeftijd"),
    NAAM("naam"),
    NATIONALITEITEN("nationaliteiten"),
    OUDERS("ouders"),
    OVERLIJDEN("overlijden"),
    PARTNERS("partners"),
    UITSLUITING_KIESRECHT("uitsluitingKiesrecht"),
    VERBLIJFPLAATS("verblijfplaats"),
    VERBLIJFSTITEL("verblijfstitel"),
    VERBLIJFPLAATS_BINNENLAND("verblijfplaatsBinnenland"),
    ADRESSERING_BINNENLAND("adresseringBinnenland"), ;

    override fun toString(): String {
        return this.fieldKey
    }


    companion object {
        fun asList(): List<String> {
            return entries.map { it.toString() }
        }

        fun fromFieldKey(fieldKey: String): RaadpleegMetBurgerservicenummerField? {
            return entries.firstOrNull { it.fieldKey == fieldKey }
        }
    }
}