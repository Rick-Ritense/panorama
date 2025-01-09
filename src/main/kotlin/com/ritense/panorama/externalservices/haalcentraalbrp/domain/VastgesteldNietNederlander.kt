package com.ritense.panorama.externalservices.haalcentraalbrp.domain

data class VastgesteldNietNederlander(
    override val redenOpname: Waardetabel? = null,
    override val datumIngangGeldigheid: AbstractDatum? = null,
    val inOnderzoek: BijzonderNederlanderschapInOnderzoek? = null,
) : AbstractNationaliteit {
    override val type: String = "VastgesteldNietNederlander"
}