package com.ritense.panorama.externalservices.haalcentraalbrp.client

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class RaadpleegMetBurgerservicenummerRequest(
    override val type: String = "RaadpleegMetBurgerservicenummer",
    val burgerservicenummer: List<String> = emptyList(),
    val fields: List<String> = RaadpleegMetBurgerservicenummerField.asList(),
    val gemeenteVanInschrijving: String? = null,
) : PersoonRequest