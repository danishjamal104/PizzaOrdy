package com.github.danishjamal104.pizzaordy.data.model

class CartItem(
    var quantity: Int,
    val crust: Crust,
    val crustSize: CrustSize
) {

    var price = quantity*crustSize.price

    fun increment() {
        quantity++
        refreshPrice()
    }

    fun decrement() {
        quantity--
        refreshPrice()
    }

    private fun refreshPrice() {
        price = quantity*crustSize.price
    }
}