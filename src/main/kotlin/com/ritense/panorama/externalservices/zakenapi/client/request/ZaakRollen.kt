package com.ritense.panorama.externalservices.zakenapi.client.request

import com.ritense.panorama.externalservices.zakenapi.domain.ZaakRol
import java.util.UUID

interface ZaakRollen {
    fun search(): SearchZaakRollen

    fun get(id: UUID): GetZaakRol
}

interface SearchZaakRollen : PagedRetrieve<SearchZaakRollen, ZaakRol>, AuthenticationFilter<SearchZaakRollen> {
    fun forZaak(zaakUrl: String): SearchZaakRollen

    fun forZaak(zaakId: UUID): SearchZaakRollen

    fun ofVestigingsNummer(vestigingsNummer: String): SearchZaakRollen
}

interface GetZaakRol : Retrieve<ZaakRol>
