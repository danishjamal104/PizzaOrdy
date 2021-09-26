package com.github.danishjamal104.pizzaordy.data.mapper.pizzamapper

import com.github.danishjamal104.pizzaordy.data.mapper.BaseEntityMapper
import com.github.danishjamal104.pizzaordy.data.model.Pizza
import javax.inject.Inject

class PizzaMapper
@Inject
constructor(): BaseEntityMapper<Pizza, Pizza>() {

    override fun mapFromEntity(entity: Pizza): Pizza {
        return Pizza(
            entity.name,
            entity.isVeg,
            entity.description,
            entity.defaultCrust,
            entity.crusts
        )
    }

    override fun mapToEntity(domainModel: Pizza): Pizza {
        return Pizza(
            domainModel.name,
            domainModel.isVeg,
            domainModel.description,
            domainModel.defaultCrust,
            domainModel.crusts
        )
    }
}