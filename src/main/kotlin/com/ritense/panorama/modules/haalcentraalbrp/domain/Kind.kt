package com.ritense.panorama.modules.haalcentraalbrp.domain

data class Kind(
    val burger: String? = null,
    val inOnderzoek: GeboorteInOnderzoek? = null,
    val naam: NaamPersoon? = null,
    val geboorte: Geboorte? = null,
)
