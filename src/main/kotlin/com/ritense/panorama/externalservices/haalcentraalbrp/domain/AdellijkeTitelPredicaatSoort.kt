package com.ritense.panorama.externalservices.haalcentraalbrp.domain

enum class AdellijkeTitelPredicaatSoort(private val value: String) {
    TITLE("titel"),
    PREDICATE("predicaat"), ;

    override fun toString(): String {
        return this.value
    }
}