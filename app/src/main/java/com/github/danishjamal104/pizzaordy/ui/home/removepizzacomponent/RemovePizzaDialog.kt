package com.github.danishjamal104.pizzaordy.ui.home.removepizzacomponent

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.danishjamal104.pizzaordy.R
import com.github.danishjamal104.pizzaordy.data.model.CartItem
import com.github.danishjamal104.pizzaordy.data.model.Crust
import com.github.danishjamal104.pizzaordy.data.model.CrustSize
import com.github.danishjamal104.pizzaordy.databinding.CustomizePizzaLayoutBinding
import com.github.danishjamal104.pizzaordy.databinding.DeletePizzaLayoutBinding
import com.github.danishjamal104.pizzaordy.ui.adapter.*
import com.github.danishjamal104.pizzaordy.ui.home.selectpizzacomponent.AddToCartListener
import com.github.danishjamal104.pizzaordy.utils.setStatusColor
import kotlin.math.hypot

class RemovePizzaDialog(val context: Context) {

    private var dialog: Dialog = Dialog(context, R.style.Theme_PizzaOrdy)

    @SuppressLint("InflateParams")
    private var _binding: DeletePizzaLayoutBinding = DeletePizzaLayoutBinding.bind(
        LayoutInflater.from(context).inflate(R.layout.delete_pizza_layout, null, true)
    )
    private val binding get() = _binding

    private lateinit var cartItemAdapter: CartItemAdapter

    init {
        dialogSetup()
        setUpCartAdapter()
    }

    private fun setUpCartAdapter() {
        cartItemAdapter = CartItemAdapter(context)
        binding.cartList.layoutManager = LinearLayoutManager(context)
        binding.cartList.setHasFixedSize(false)
        binding.cartList.adapter = cartItemAdapter
        cartItemAdapter.bindEmptyView(binding.emptyCartView)
    }


    private fun dialogSetup() {
        dialog.setContentView(binding.root)

        dialog.window?.setStatusColor(
            ContextCompat.getColor(
                context,
                R.color.secondary
            )
        )
        dialog.window?.setStatusColor(ContextCompat.getColor(context, R.color.primaryDark))
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.setOnShowListener { revealShow(true) }

        dialog.setOnKeyListener(
            DialogInterface.OnKeyListener { _, i, _ ->
                if (i == KeyEvent.KEYCODE_BACK) {
                    revealShow(false, exit = true)
                    return@OnKeyListener true
                }
                false
            }
        )

        binding.doneButton.setOnClickListener {
            revealShow(b = false, exit = true)
        }
    }

    private fun revealShow(b: Boolean, exit: Boolean = false, exitFunction: () -> Unit = {}) {
        val view = binding.root
        val w = view.width
        val h = view.height
        val endRadius = hypot(w.toDouble(), h.toDouble()).toInt()
        val cx = w/2
        val cy = h
        val duration = 600L
        if (b) {
            val revealAnimator =
                ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, endRadius.toFloat())
            view.visibility = View.VISIBLE
            revealAnimator.duration = duration
            revealAnimator.start()
        } else {
            val anim =
                ViewAnimationUtils.createCircularReveal(view, cx, cy, endRadius.toFloat(), 0f)
            anim.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    if (exit) {
                        dialog.dismiss()
                        view.visibility = View.INVISIBLE
                        exitFunction()
                    }
                }
            })
            anim.duration = duration
            anim.start()
        }
    }

    fun addToCart(crust: Crust, crustSize: CrustSize) {
        cartItemAdapter.addCustom(CartItem(1, crust, crustSize))
    }

    fun show() {
        dialog.show()
    }

    fun addOnQuantityChangeListener(onQuantityChangeListener: OnQuantityChangeListener) {
        cartItemAdapter.onQuantityChangeListener = onQuantityChangeListener
    }

    fun getItemCount(): Int {
        return cartItemAdapter.getDataList.size
    }

}