package com.ritense.panorama.externalservices.haalcentraalbrp.web.rest

import com.ritense.panorama.externalservices.haalcentraalbrp.client.RaadpleegMetBurgerservicenummerField
import com.ritense.panorama.externalservices.haalcentraalbrp.domain.Persoon
import com.ritense.panorama.externalservices.haalcentraalbrp.service.HaalCentraalBrpService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api/v1/haalcentraalbrp"])
class HaalCentraalBrpResource(private val haalCentraalBrpService: HaalCentraalBrpService) {

    @GetMapping("/persoon/{bsn}")
    suspend fun getPersoon(
        @PathVariable("bsn") bsn: String,
    ): ResponseEntity<Persoon?> {
        val result = haalCentraalBrpService.findPersoonByBurgerservicenummer(bsn)

        return ResponseEntity.ok().body(result)
    }

    @GetMapping("/personen")
    suspend fun getPersonen(
        @RequestParam("burgerservicenummers") burgerservicenummers: List<String>,
        @RequestParam("fields") fields: List<String>,
    ): ResponseEntity<List<Persoon>?> {
        val requestedFields =
                runCatching {
                    fields.map {
                        RaadpleegMetBurgerservicenummerField.fromFieldKey(it)
                    }
                }
                .getOrElse {
                    return ResponseEntity.badRequest().build()
                }
                .filterNotNull()

        val result = haalCentraalBrpService.findPersonenByBurgerservicenummer(burgerservicenummers, requestedFields)

        return ResponseEntity.ok().body(result)
    }
}