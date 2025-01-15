package com.ritense.panorama.modules.haalcentraalbrp.domain

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Partner(
    val burgerservicenummer: String? = null,
    val geslacht: Waardetabel? = null,
    val soortVerbintenis: Waardetabel? = null,
    val naam: NaamGerelateerde? = null,
    val geboorte: Geboorte? = null,
    val inOnderzoek: NaamPersoonInOnderzoek? = null,
    val aangaanHuwelijkPartnerschap: AangaanHuwelijkPartnerschap? = null,
)
