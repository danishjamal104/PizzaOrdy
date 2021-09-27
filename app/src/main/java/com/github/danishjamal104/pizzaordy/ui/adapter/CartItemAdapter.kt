package com.github.danishjamal104.pizzaordy.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import com.github.danishjamal104.pizzaordy.R
import com.github.danishjamal104.pizzaordy.data.model.CartItem
import com.github.danishjamal104.pizzaordy.databinding.CartListItemBinding
import com.github.danishjamal104.pizzaordy.utils.inRupeesFormat

class CartItemAdapter(val context: Context):
BaseAdapter<CartItem, CartListItemBinding, OnItemClickListener<CartItem>>(R.layout.cart_list_item){

    var onQuantityChangeListener: OnQuantityChangeListener? = null

    override fun getBinding(view: View): CartListItemBinding = CartListItemBinding.bind(view)

    @SuppressLint("SetTextI18n")
    override fun bindViewHolder(position: Int, item: CartItem, binding: CartListItemBinding) {
        binding.increase.setOnClickListener {
            item.increment()
            notifyItemChanged(position)
            updateData()
        }
        binding.decrease.setOnClickListener {
            item.decrement()
            if(item.quantity == 0) {
                remove(position)
            }
            notifyItemChanged(position)
            updateData()
        }
        binding.quantity.text = "${item.quantity}"
        binding.title.text = "${item.crust.name} | ${item.crustSize.name}"
        binding.price.text = item.crustSize.price.inRupeesFormat(context)
    }

    fun addCustom(cartItem: CartItem) {
        var pos = -1
        getDataList.forEach {
            pos++
            if(it.crust.id == cartItem.crust.id && it.crustSize.id == cartItem.crustSize.id) {
                it.increment()
                notifyItemChanged(pos)
                updateData()
                return
            }
        }
        add(cartItem)
        updateData()
    }

    private fun updateData() {
        var totalPrice = 0.0
        var quatity = 0
        getDataList.forEach {
            totalPrice += it.price
            quatity += it.quantity
        }

        onQuantityChangeListener?.onPriceChange(totalPrice)
        onQuantityChangeListener?.onQuantityChange(quatity)
    }

    override fun onItemClick(
        position: Int,
        item: CartItem,
        binding: CartListItemBinding,
        listener: OnItemClickListener<CartItem>?
    ) {
        println()
    }
}