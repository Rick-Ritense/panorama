package com.ritense.panorama.externalservices.haalcentraalbrp.domain

data class VerblijfplaatsOnbekend(
    override val type: String = "VerblijfplaatsOnbekend",
    val datumVan: AbstractDatum? = null,
    val datumIngangGeldigheid: AbstractDatum? = null,
    val inOnderzoek: VerblijfplaatsOnbekendInOnderzoek? = null,
) : AbstractVerblijfplaats {
    data class VerblijfplaatsOnbekendInOnderzoek(
        val datumIngangOnderzoek: AbstractDatum? = null,
        val type: Boolean? = null,
        val datumVan: Boolean? = null,
        val datumIngangGeldigheid: Boolean? = null,
    )
}