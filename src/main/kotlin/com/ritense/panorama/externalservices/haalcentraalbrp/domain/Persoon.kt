package com.ritense.panorama.externalservices.haalcentraalbrp.domain

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Persoon(
    val burgerservicenummer: String? = null,
    val geslacht: Waardetabel? = null,
    val naam: NaamPersoon? = null,
    val nationaliteiten: List<AbstractNationaliteit>? = null,
    val geboorte: Geboorte? = null,
    val verblijfplaats: AbstractVerblijfplaats? = null,
    val gemeenteVanInschrijving: Waardetabel? = null,
)