package com.ritense.panorama.externalservices.haalcentraalbrp.domain

data class Nationaliteit(
    override val redenOpname: Waardetabel? = null,
    override val datumIngangGeldigheid: AbstractDatum? = null,
    val inOnderzoek: NationaliteitOnbekendInOnderzoek? = null,
    val nationaliteit: Waardetabel? = null,
) : AbstractNationaliteit {
    override val type: String = "Nationaliteit"
}