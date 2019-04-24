package com.asemiashkin.persistence

interface CRUDRepository<K, T> {

    fun create(toAdd: T) : K
    fun update(toUpdate: T) : Boolean
    fun remove(id: K) : Boolean
    fun fetch(id: K) : T
    fun fetchAll() : List<T>

}
