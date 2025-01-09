package com.ritense.panorama.externalservices.haalcentraalbrp.domain

data class NationaliteitOnbekend(
    override val redenOpname: Waardetabel,
    override val datumIngangGeldigheid: AbstractDatum,
    val inOnderzoek: NationaliteitOnbekendInOnderzoek,
) : AbstractNationaliteit {
    override val type: String = "NationaliteitOnbekend"
}