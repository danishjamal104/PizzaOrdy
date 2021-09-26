package com.github.danishjamal104.pizzaordy.data.model

data class Pizza(
    val name: String,
    val isVeg: Boolean,
    val description: String,
    val defaultCrust: Crust,
    val crusts: List<Crust>
)
