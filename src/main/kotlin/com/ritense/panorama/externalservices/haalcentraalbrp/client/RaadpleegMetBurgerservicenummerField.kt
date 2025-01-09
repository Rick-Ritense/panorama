package com.ritense.panorama.externalservices.haalcentraalbrp.client

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