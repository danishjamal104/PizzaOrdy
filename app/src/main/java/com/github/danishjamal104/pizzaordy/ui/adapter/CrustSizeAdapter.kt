package com.github.danishjamal104.pizzaordy.ui.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.View
import androidx.core.content.ContextCompat
import com.github.danishjamal104.pizzaordy.R
import com.github.danishjamal104.pizzaordy.data.model.Crust
import com.github.danishjamal104.pizzaordy.data.model.CrustSize
import com.github.danishjamal104.pizzaordy.databinding.TextListItemBinding

class CrustSizeAdapter(val context: Context): BaseAdapter<CrustSize, TextListItemBinding, OnItemClickListener<CrustSize>>(
    R.layout.text_list_item) {

    private lateinit var _binding: TextListItemBinding

    private var selected: Pair<Int, TextListItemBinding>? = null

    override fun getBinding(view: View): TextListItemBinding = TextListItemBinding.bind(view)

    override fun bindViewHolder(position: Int, item: CrustSize, binding: TextListItemBinding) {
        binding.optionText.text = item.name
    }

    override fun onItemClick(
        position: Int,
        item: CrustSize,
        binding: TextListItemBinding,
        listener: OnItemClickListener<CrustSize>?
    ) {
        listener?.onItemClick(item, position)
        updateSelection(position, binding)
    }

    fun getPosition(crustId: Int): Int {
        var pos = -1;
        getDataList.forEach {
            pos++
            if(crustId == it.id){
                return pos
            }
        }
        return pos
    }

    fun getCurrentSelected(): CrustSize {
        return getDataList[selected!!.first]
    }

    /**
     * updates the selection of item based on current selected
     */
    private fun updateSelection(position: Int, binding: TextListItemBinding) {
        selected?.let {
            unselectUIUpdate(it.second)
            selected = null
        }
        selectUIUpdate(binding)
        selected = position to binding
    }

    /**
     * update the UI of view when selected
     */
    private fun selectUIUpdate(binding: TextListItemBinding) {
        binding.root.setCardBackgroundColor(ContextCompat.getColor(context, R.color.secondary))
        //binding.optionText.setTextColor(ContextCompat.getColor(context, R.color.secondary))
    }

    /**
     * update the ui of view which is to be unselected
     */
    private fun unselectUIUpdate(binding: TextListItemBinding) {
        binding.root.setCardBackgroundColor(ContextCompat.getColor(context, R.color.primaryDark))
        //binding.optionText.setTextColor(ContextCompat.getColor(context, R.color.black))
    }
}