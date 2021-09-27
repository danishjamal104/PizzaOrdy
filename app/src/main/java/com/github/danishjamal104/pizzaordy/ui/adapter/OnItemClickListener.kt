package com.github.danishjamal104.pizzaordy.ui.adapter

interface OnItemClickListener<T> {
    fun onItemClick(item: T, position: Int)
}