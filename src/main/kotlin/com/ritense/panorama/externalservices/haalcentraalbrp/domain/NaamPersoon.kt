package com.ritense.panorama.externalservices.haalcentraalbrp.domain

data class NaamPersoon(
    val voornamen: String? = null,
    val adellijkeTitelPredicaat: AdellijkeTitelPredicaatType? = null,
    val voorvoegsel: String? = null,
    val geslachtsnaam: String? = null,
    val voorletters: String? = null,
    val volledigeNaam: String? = null,
    val aanduidingNaamgebruik: Waardetabel? = null,
    val inOnderzoek: NaamPersoonInOnderzoek? = null,
)