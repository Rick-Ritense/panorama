package com.ritense.panorama.externalservices.haalcentraalbrp.domain

data class VerblijfplaatsBuitenland(
    override val type: String = "VerblijfplaatsBuitenland",
    val verblijfadres: Any? = null,
    val datumVan: AbstractDatum? = null,
    val datumIngangGeldigheid: Any? = null,
    val inOnderzoek: Any? = null,
) : AbstractVerblijfplaats {
    data class VerblijfadresBuitenland(
        val regel1: String? = null,
        val regel2: String? = null,
        val regel3: String? = null,
        val land: Waardetabel? = null,
        val inOnderzoek: VerblijfadresBuitenlandInOnderzoek? = null,
    ) {
        data class VerblijfadresBuitenlandInOnderzoek(
            val datumIngangOnderzoek: AbstractDatum? = null,
            val regel1: Boolean? = null,
            val regel2: Boolean? = null,
            val regel3: Boolean? = null,
            val land: Boolean? = null,
        )
    }
}