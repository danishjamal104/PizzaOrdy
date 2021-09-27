package com.github.danishjamal104.pizzaordy.ui.adapter

import android.content.Context
import android.view.View
import com.github.danishjamal104.pizzaordy.R
import com.github.danishjamal104.pizzaordy.data.model.CartItem
import com.github.danishjamal104.pizzaordy.databinding.CartListItemBinding

class CartItemAdapter(val context: Context):
BaseAdapter<CartItem, CartListItemBinding, OnItemClickListener<CartItem>>(R.layout.cart_list_item){

    var onQuantityChangeListener: OnQuantityChangeListener? = null

    override fun getBinding(view: View): CartListItemBinding = CartListItemBinding.bind(view)

    override fun bindViewHolder(position: Int, item: CartItem, binding: CartListItemBinding) {
        binding.increase.setOnClickListener {
            item.increment()
            notifyItemChanged(position)
            updatePriceCallBack()
        }
        binding.decrease.setOnClickListener {
            item.decrement()
            if(item.quantity == 0) {
                remove(position)
            }
            notifyItemChanged(position)
            updatePriceCallBack()
        }
        binding.quantity.text = "${item.quantity}"
        binding.title.text = "${item.crust.name} | ${item.crustSize.name}"
        binding.price.text = "${context.getString(R.string.rupee_symbol)} ${item.price}"
    }

    fun addCustom(cartItem: CartItem) {
        var pos = -1;
        getDataList.forEach {
            pos++
            if(it.crust.id == cartItem.crust.id && it.crustSize.id == cartItem.crustSize.id) {
                it.quantity++
                notifyItemChanged(pos)
                updatePriceCallBack()
                return
            }
        }
        add(cartItem)
        updatePriceCallBack()
    }

    fun getTotalPrice(): Double {
        var total = 0.0
        getDataList.forEach {
            total += it.price
        }
        return total
    }

    private fun updatePriceCallBack() {
        onQuantityChangeListener?.let {
            it.onPriceChange(getTotalPrice())
        }
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