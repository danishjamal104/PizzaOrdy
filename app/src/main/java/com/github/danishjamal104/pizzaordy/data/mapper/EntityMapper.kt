package com.github.danishjamal104.pizzaordy.data.mapper

interface EntityMapper<Entity, DomainModel> {

    fun mapFromEntity(entity: Entity): DomainModel

    fun mapToEntity(domainModel: DomainModel): Entity

    fun mapFromEntityList(entities: List<Entity>): List<DomainModel>

    fun mapToEntityList(domainModels: List<DomainModel>): List<Entity>
}