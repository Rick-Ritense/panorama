package com.ritense.panorama.externalservices.haalcentraalbrp.domain

data class Adres(
    override val type: String = "Adres",
    val verblijfadres: VerblijfadresBinnenland? = null,
    val functieAdres: Waardetabel? = null,
    val adresseerbaarObjectIdentificatie: String? = null,
    val nummeraanduidingIdentificatie: String? = null,
    val datumVan: AbstractDatum? = null,
    val datumIngangGeldigheid: AbstractDatum? = null,
    val indicatieVastgesteldVerblijftNietOpAdres: Boolean? = null,
    val inOnderzoek: AdresInOnderzoek? = null,
) : AbstractVerblijfplaats {
    data class AdresInOnderzoek(
        val datumIngangOnderzoek: AbstractDatum? = null,
        val functieAdres: Boolean? = null,
        val adresseerbaarObjectIdentificatie: Boolean? = null,
        val nummeraanduidingIdentificatie: Boolean? = null,
        val datumVan: Boolean? = null,
        val datumIngangGeldigheid: Boolean? = null,
    )

    data class VerblijfadresBinnenland(
        val officieleStraatnaam: String? = null,
        val korteStraatnaam: String? = null,
        val huisnummer: Int? = null,
        val huisletter: String? = null,
        val huisnummertoevoeging: String? = null,
        val aanduidingBijHuisnummer: Waardetabel? = null,
        val postcode: String? = null,
        val woonplaats: String? = null,
        val inOnderzoek: VerblijfadresBinnenlandInOnderzoek? = null,
    ) {
        data class VerblijfadresBinnenlandInOnderzoek(
            val datumIngangOnderzoek: AbstractDatum? = null,
            val officieleStraatnaam: Boolean? = null,
            val korteStraatnaam: Boolean? = null,
            val huisnummer: Boolean? = null,
            val huisletter: Boolean? = null,
            val huisnummertoevoeging: Boolean? = null,
            val aanduidingBijHuisnummer: Boolean? = null,
            val postcode: Boolean? = null,
            val woonplaats: Boolean? = null,
        )
    }
}