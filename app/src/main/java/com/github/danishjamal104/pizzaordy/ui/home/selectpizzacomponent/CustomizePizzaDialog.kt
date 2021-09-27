package com.github.danishjamal104.pizzaordy.ui.home.selectpizzacomponent

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
import com.github.danishjamal104.pizzaordy.data.model.Crust
import com.github.danishjamal104.pizzaordy.data.model.CrustSize
import com.github.danishjamal104.pizzaordy.databinding.CustomizePizzaLayoutBinding
import com.github.danishjamal104.pizzaordy.ui.adapter.CrustAdapter
import com.github.danishjamal104.pizzaordy.ui.adapter.CrustSizeAdapter
import com.github.danishjamal104.pizzaordy.ui.adapter.OnItemClickListener
import com.github.danishjamal104.pizzaordy.utils.setStatusColor
import kotlin.math.hypot

class CustomizePizzaDialog(val context: Context) {

    private var dialog: Dialog = Dialog(context)

    @SuppressLint("InflateParams")
    private var _binding: CustomizePizzaLayoutBinding = CustomizePizzaLayoutBinding.bind(
        LayoutInflater.from(context).inflate(R.layout.customize_pizza_layout, null, true)
    )
    private val binding get() = _binding

    private lateinit var crusts: List<Crust>

    private lateinit var crustAdapter: CrustAdapter
    private lateinit var sizeAdapter: CrustSizeAdapter

    private var currentPrice = 0.0

    var addToCartListener: AddToCartListener? = null

    init {
        dialogSetup()
        setUpCrustAdapterSetup()
        setUpSizeAdapterSetup()
        updatePriceUI()
    }

    private fun setUpCrustAdapterSetup() {
        crustAdapter = CrustAdapter(context)
        binding.crustList.layoutManager = LinearLayoutManager(context,
        LinearLayoutManager.HORIZONTAL, false)
        binding.crustList.setHasFixedSize(false)
        binding.crustList.adapter = crustAdapter

        crustAdapter.listener = object : OnItemClickListener<Crust> {
            override fun onItemClick(item: Crust, position: Int) {
                setSize(item.sizes, item.defaultSize)
            }
        }
    }

    private fun setUpSizeAdapterSetup() {
        sizeAdapter = CrustSizeAdapter(context)
        binding.sizeList.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false)
        binding.sizeList.setHasFixedSize(false)
        binding.sizeList.adapter = sizeAdapter

    }

    private fun dialogSetup() {
        dialog.setContentView(binding.root)

        dialog.window?.setStatusColor(
            ContextCompat.getColor(
                context,
                R.color.secondary
            )
        )
        dialog.window?.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
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

        binding.addToCart.setOnClickListener {
            addToCartListener?.addToCart(
                crustAdapter.getCurrentSelected(), sizeAdapter.getCurrentSelected())
            revealShow(b = false, exit = true)
        }
    }

    private fun revealShow(b: Boolean, exit: Boolean = false, exitFunction: () -> Unit = {}) {
        val view = binding.root
        val w = view.width
        val h = view.height
        val endRadius = hypot(w.toDouble(), h.toDouble()).toInt()
        val cx = w/2
        val cy = h/2
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

    fun setCrusts(crusts: List<Crust>, defaultCrustId: Int) {
        this.crusts = crusts
        crustAdapter.addAll(crusts, true)
        val positionInAdapter = crustAdapter.getPosition(defaultCrustId)
        if(positionInAdapter != -1) {
            binding.crustList.postDelayed({
                binding.crustList.findViewHolderForAdapterPosition(positionInAdapter)!!
                    .itemView.performClick()
            }, 50)

        }
    }

    fun setSize(sizes: List<CrustSize>, defaultSizeId: Int) {
        sizeAdapter.listener = null
        sizeAdapter.addAll(sizes, true)
        val positionInAdapter = sizeAdapter.getPosition(defaultSizeId)
        if(positionInAdapter != -1) {
            binding.crustList.postDelayed({
                sizeAdapter.listener = object : OnItemClickListener<CrustSize> {
                    override fun onItemClick(item: CrustSize, position: Int) {
                        updatePrice(item.price)
                    }
                }
                binding.sizeList.findViewHolderForAdapterPosition(positionInAdapter)!!
                    .itemView.performClick()
            }, 50)
        }
    }

    private fun updatePrice(price: Double) {
        currentPrice = price
        updatePriceUI()
    }

    @SuppressLint("SetTextI18n")
    private fun updatePriceUI() {
        binding.price.text = "${context.getString(R.string.rupee_symbol)} $currentPrice"
    }

    fun show() {
        dialog.show()
    }

}