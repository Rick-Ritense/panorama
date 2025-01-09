package com.ritense.panorama.externalservices.haalcentraalbrp.domain

data class DatumOnbekend(
    override val langFormaat: String,
    val onbekend: Boolean,
) : AbstractDatum {
    override val type = "DatumOnbekend"
}