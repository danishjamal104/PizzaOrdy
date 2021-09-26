package com.github.danishjamal104.pizzaordy.data.model

data class Crust(
    val id: Int,
    val name: String,
    val defaultSize: Int,
    val sizes: List<CrustSize>
)
