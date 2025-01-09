package com.ritense.panorama.externalservices.haalcentraalbrp.domain

data class JaarDatum(
    override val langFormaat: String,
    val jaar: Int,
) : AbstractDatum {
    override val type = "JaarDatum"
}