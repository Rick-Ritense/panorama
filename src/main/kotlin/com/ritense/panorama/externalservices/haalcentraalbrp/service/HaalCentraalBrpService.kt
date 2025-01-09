package com.ritense.panorama.externalservices.haalcentraalbrp.service

import com.ritense.panorama.externalservices.haalcentraalbrp.client.HaalCentraalBrpClient
import com.ritense.panorama.externalservices.haalcentraalbrp.client.RaadpleegMetBurgerservicenummer
import com.ritense.panorama.externalservices.haalcentraalbrp.client.RaadpleegMetBurgerservicenummerField
import com.ritense.panorama.externalservices.haalcentraalbrp.client.RaadpleegMetBurgerservicenummerRequest
import com.ritense.panorama.externalservices.haalcentraalbrp.domain.Persoon

class HaalCentraalBrpService(
    private val haalcentraalBrpClient: HaalCentraalBrpClient,
) {
    suspend fun findPersoonByBurgerservicenummer(
        burgerservicenummer: String,
    ): Persoon? {
        val request =
            RaadpleegMetBurgerservicenummerRequest(
                burgerservicenummer = listOf(burgerservicenummer),
            )
        val response =
            haalcentraalBrpClient
                .zoekPersonen(request)

        return when (response) {
            is RaadpleegMetBurgerservicenummer -> {
                response
                    .personen
                    .singleOrNull {
                        it.burgerservicenummer == burgerservicenummer
                    }
            }

            else -> null
        }
    }

    suspend fun findPersonenByBurgerservicenummer(
        burgerservicenummers: List<String>,
        fields: List<RaadpleegMetBurgerservicenummerField>,
    ): List<Persoon>? {
        val request =
            RaadpleegMetBurgerservicenummerRequest(
                burgerservicenummer = burgerservicenummers,
                fields = fields.map { it.toString() },
            )
        val response =
            haalcentraalBrpClient
                .zoekPersonen(request)

        return when (response) {
            is RaadpleegMetBurgerservicenummer -> {
                response
                    .personen
            }

            else -> null
        }
    }
}