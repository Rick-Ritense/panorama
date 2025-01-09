package com.ritense.panorama.externalservices.haalcentraalbrp.domain

import java.time.LocalDate

data class Datum(
    override val langFormaat: String,
    val datum: LocalDate,
) : AbstractDatum {
    override val type = "Datum"
}