package com.ritense.panorama.modules.haalcentraalbrp.domain

data class Ouder(
    val burgerservicenummer: String? = null,
    val geslacht: Waardetabel? = null,
    val ouderAanduiding: String? = null,
    val datumIngangFamilirechtelijkeBetrekking: AbstractDatum? = null,
    val naam: NaamGerelateerde? = null,
    val geboorte: Geboorte? = null,
    val inOnderzoek: NaamPersoonInOnderzoek? = null,
)
