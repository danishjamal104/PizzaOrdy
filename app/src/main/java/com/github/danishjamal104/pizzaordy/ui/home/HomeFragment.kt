package com.github.danishjamal104.pizzaordy.ui.home

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.github.danishjamal104.pizzaordy.R
import com.github.danishjamal104.pizzaordy.data.model.Crust
import com.github.danishjamal104.pizzaordy.data.model.CrustSize
import com.github.danishjamal104.pizzaordy.data.model.Pizza
import com.github.danishjamal104.pizzaordy.databinding.FragmentHomeBinding
import com.github.danishjamal104.pizzaordy.ui.adapter.OnQuantityChangeListener
import com.github.danishjamal104.pizzaordy.ui.home.removepizzacomponent.RemovePizzaDialog
import com.github.danishjamal104.pizzaordy.ui.home.selectpizzacomponent.AddToCartListener
import com.github.danishjamal104.pizzaordy.ui.home.selectpizzacomponent.CustomizePizzaDialog
import com.github.danishjamal104.pizzaordy.utils.inRupeesFormat
import com.github.danishjamal104.pizzaordy.utils.longToast
import com.github.danishjamal104.pizzaordy.utils.shortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), AddToCartListener, OnQuantityChangeListener {

    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var dialog: CustomizePizzaDialog
    private lateinit var removePizzaDialog: RemovePizzaDialog

    private lateinit var pizza: Pizza

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        dialog = CustomizePizzaDialog(requireContext())
        dialog.addToCartListener = this

        removePizzaDialog = RemovePizzaDialog(requireContext())
        removePizzaDialog.addOnQuantityChangeListener(this)

        binding.addToCart.setOnClickListener {
            if (this::pizza.isInitialized) {
                dialog.show()
                dialog.setCrusts(pizza.crusts, pizza.defaultCrust)
            }
            shortToast(getString(R.string.fetching_pizza_info))
        }

        binding.removeFromCart.setOnClickListener {
            if (this::pizza.isInitialized) {
                removePizzaDialog.show()
            }
            shortToast(getString(R.string.fetching_pizza_info))
        }

        observeHomeState()
        setEvent(HomeEvent.GetPizza)
    }

    private fun observeHomeState() {
        viewModel.homeState.observe(viewLifecycleOwner) {
            when(it) {
                is HomeState.DataLoaded -> setPizzaUI(it.pizza)
                is HomeState.Error -> longToast(it.friendlyErrorMessage)
                HomeState.Loading -> shortToast("Getting pizza info")
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setPizzaUI(pizza: Pizza) {
        this.pizza = pizza
        binding.pizzaNameTv.text = pizza.name
        binding.pizzaDescriptionTv.text = pizza.description
        binding.totalAmountTv.text = 0.0.inRupeesFormat(requireContext())
        refreshVegNonVegIndicator(pizza.isVeg)
    }

    /**
     * sends the event to the viewmodel
     */
    private fun setEvent(event: HomeEvent) {
        viewModel.setEvent(event)
    }

    private fun refreshVegNonVegIndicator(isVeg: Boolean) {
        if(isVeg) {
            selectVeg()
        } else {
            selectNonVeg()
        }
    }

    private fun selectVeg() {
        binding.nonVegIndicator.alpha = 0.25f
        binding.vegIndicator.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        binding.vegIndicator.setTypeface(null, Typeface.BOLD)
    }

    private fun selectNonVeg() {
        binding.vegIndicator.alpha = 0.25f
        binding.nonVegIndicator.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
        binding.nonVegIndicator.setTypeface(null, Typeface.BOLD)
    }

    override fun addToCart(crust: Crust, crustSize: CrustSize) {
        removePizzaDialog.addToCart(crust, crustSize)
    }

    override fun onPriceChange(newTotalPrice: Double) {
        binding.totalAmountTv.text = newTotalPrice.inRupeesFormat(requireContext())
    }

    override fun onQuantityChange(newQuantity: Int) {
        binding.totalItem.text = "${requireContext().getString(R.string.quantity)} $newQuantity"
    }
}