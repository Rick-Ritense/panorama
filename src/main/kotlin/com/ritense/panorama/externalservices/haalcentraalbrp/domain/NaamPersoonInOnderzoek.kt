package com.ritense.panorama.externalservices.haalcentraalbrp.domain

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