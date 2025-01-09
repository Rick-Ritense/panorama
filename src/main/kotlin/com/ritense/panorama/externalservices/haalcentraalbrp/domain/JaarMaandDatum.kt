package com.ritense.panorama.externalservices.haalcentraalbrp.domain

data class JaarMaandDatum(
    override val langFormaat: String,
    val jaar: Int,
    val maand: Int,
) : AbstractDatum {
    override val type = "JaarMaandDatum"
}