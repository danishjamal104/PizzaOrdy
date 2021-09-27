package com.github.danishjamal104.pizzaordy.data.mapper


/**
 * Used for converting any entity model to the domain model.
 * This defines the possible generic conversions
 * @author Danish Jamal [https://github.com/danishjamal104]
 * @param [Entity] type of class coming from third party source not to be used directly in UI
 * @param [DomainModel] type of class recognised by UI and client side
 */
interface EntityMapper<Entity, DomainModel> {

    fun mapFromEntity(entity: Entity): DomainModel

    fun mapToEntity(domainModel: DomainModel): Entity

    fun mapFromEntityList(entities: List<Entity>): List<DomainModel>

    fun mapToEntityList(domainModels: List<DomainModel>): List<Entity>
}