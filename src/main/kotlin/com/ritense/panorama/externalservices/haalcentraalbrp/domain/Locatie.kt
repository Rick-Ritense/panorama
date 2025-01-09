package com.ritense.panorama.externalservices.haalcentraalbrp.domain

data class Locatie(
    override val type: String = "Locatie",
    val datumVan: AbstractDatum? = null,
    val functieAdres: Waardetabel? = null,
    val verblijfadres: VerblijfadresLocatie? = null,
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

    data class VerblijfadresLocatie(
        val locatiebeschrijving: String? = null,
        val inOnderzoek: VerblijfadresLocatieInOnderzoek? = null,
    ) {
        data class VerblijfadresLocatieInOnderzoek(
            val datumIngangOnderzoek: AbstractDatum? = null,
            val locatiebeschrijving: Boolean? = null,
        )
    }
}