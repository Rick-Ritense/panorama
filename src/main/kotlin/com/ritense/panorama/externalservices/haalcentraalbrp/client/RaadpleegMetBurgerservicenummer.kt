package com.ritense.panorama.externalservices.haalcentraalbrp.client

import com.ritense.panorama.externalservices.haalcentraalbrp.domain.Persoon

class RaadpleegMetBurgerservicenummer(
    override val type: String = "RaadpleegMetBurgerservicenummer",
    val personen: List<Persoon>,
) : ZoekPersonenResponse