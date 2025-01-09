package com.ritense.panorama.externalservices.zakenapi.client.request

import com.ritense.panorama.externalservices.zakenapi.domain.Zaak
import java.util.UUID

interface Zaken {
    fun search(): SearchZaken

    fun get(id: UUID): GetZaak
}

interface SearchZaken : PagedRetrieve<SearchZaken, Zaak>, AuthenticationFilter<SearchZaken> {
    fun ofZaakType(zaakType: String): SearchZaken

//    fun ofZaakTypes(zaakType: List<UUID>): SearchZaken

    fun isOpen(open: Boolean): SearchZaken

    fun ofIdentificatie(identificatie: String): SearchZaken

    fun ofVestigingsNummer(vestigingNummer: String): SearchZaken
}

interface GetZaak : Retrieve<Zaak>
