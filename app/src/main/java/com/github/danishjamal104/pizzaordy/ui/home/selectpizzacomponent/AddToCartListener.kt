package com.github.danishjamal104.pizzaordy.ui.home.selectpizzacomponent

import com.github.danishjamal104.pizzaordy.data.model.Crust
import com.github.danishjamal104.pizzaordy.data.model.CrustSize

interface AddToCartListener {
    fun addToCart(crust: Crust, crustSize: CrustSize)
}