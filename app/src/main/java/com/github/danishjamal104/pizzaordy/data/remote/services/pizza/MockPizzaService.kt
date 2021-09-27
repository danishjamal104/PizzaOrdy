package com.github.danishjamal104.pizzaordy.data.remote.services.pizza

import android.content.Context
import com.github.danishjamal104.pizzaordy.data.model.Crust
import com.github.danishjamal104.pizzaordy.data.model.CrustSize
import com.github.danishjamal104.pizzaordy.data.model.Pizza
import com.github.danishjamal104.pizzaordy.utils.AppConstants
import com.github.danishjamal104.pizzaordy.utils.getJsonFromAssets
import com.google.gson.Gson
import kotlinx.coroutines.delay

/**
 * Mock implementation of the [PizzaService], to reduce api calls and testing
 * @author Danish Jamal [https://github.com/danishjamal104]
 * @param [gson] used for converting fake response json string to concrete class
 */
class MockPizzaService(
    private val context: Context,
    private val gson: Gson
): PizzaService {

    override suspend fun getPizza(): Pizza {
        val cheeseBurstCrust = Crust(
            2,
            "Cheese Burst",
            defaultSize = 1,
            sizes = mutableListOf(
                CrustSize(1, "Medium", 295.0),
                CrustSize(2, "Large", 325.0)
            )
        )
        val handTossedCrust = Crust(
            1,
            "Hand-tossed",
            defaultSize = 2,
            sizes = mutableListOf(
                CrustSize(1, "Regular", 235.0),
                CrustSize(2, "Medium", 265.0),
                CrustSize(3, "Large", 295.0)
            )
        )
        val pizza = Pizza(
            "Non-Veg Pizza",
        false,
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        1,
            mutableListOf(handTossedCrust, cheeseBurstCrust)
        )
        delay(1000)
        return pizza
    }
}