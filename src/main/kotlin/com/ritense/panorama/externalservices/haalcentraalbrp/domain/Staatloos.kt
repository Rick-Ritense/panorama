package com.ritense.panorama.externalservices.haalcentraalbrp.domain

data class Staatloos(
    override val redenOpname: Waardetabel? = null,
    override val datumIngangGeldigheid: AbstractDatum? = null,
    val inOnderzoek: StaatloosInOnderzoek? = null,
) : AbstractNationaliteit {
    override val type: String = "Staatloos"
}