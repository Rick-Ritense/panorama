package com.ritense.panorama.externalservices.haalcentraalbrp.domain

data class BehandeldAlsNederlander(
    override val redenOpname: Waardetabel? = null,
    override val datumIngangGeldigheid: AbstractDatum? = null,
    val inOnderzoek: BijzonderNederlanderschapInOnderzoek? = null,
) : AbstractNationaliteit {
    override val type: String = "BehandeldAlsNederlander"
}