package com.github.danishjamal104.pizzaordy.ui.adapter

interface OnQuantityChangeListener {
    fun onPriceChange(newTotalPrice: Double)
    fun onQuantityChange(newQuantity: Int)
}