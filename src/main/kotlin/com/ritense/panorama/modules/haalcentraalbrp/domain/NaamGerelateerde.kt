package com.ritense.panorama.modules.haalcentraalbrp.domain

data class NaamGerelateerde(
    val voornamen: String? = null,
    val adellijkeTitelPredicaatType: AdellijkeTitelPredicaatType? = null,
    val voorvoegsel: String? = null,
    val geslachtsnaam: String? = null,
    val voorletters: String? = null,
    val volledigeNaam: String? = if(voorvoegsel == null) {"$voornamen $geslachtsnaam"} else {"$voornamen $voorvoegsel $geslachtsnaam"},
    val inOnderzoek: NaamPersoonInOnderzoek? = null,
)
