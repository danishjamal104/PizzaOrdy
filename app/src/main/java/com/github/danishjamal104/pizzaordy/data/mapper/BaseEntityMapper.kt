package com.github.danishjamal104.pizzaordy.data.mapper

abstract class BaseEntityMapper<Entity, DomainModel>: EntityMapper<Entity, DomainModel> {

    override fun mapFromEntityList(entities: List<Entity>): List<DomainModel> {
        return entities.map { mapFromEntity(it) }
    }

    override fun mapToEntityList(domainModels: List<DomainModel>): List<Entity> {
        return domainModels.map { mapToEntity(it) }
    }

}