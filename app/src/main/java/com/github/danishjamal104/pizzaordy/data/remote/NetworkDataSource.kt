package com.github.danishjamal104.pizzaordy.data.remote

import com.github.danishjamal104.pizzaordy.data.model.Pizza

/**
 * Single source of truth for remote data. This is the only way of getting data from remotes
 * @author Danish Jamal [https://github.com/danishjamal104]
 */
interface NetworkDataSource {

    /**
     * fetches pizza from remote
     */
    suspend fun getPizza(): Pizza
}