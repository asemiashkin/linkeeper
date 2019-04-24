package com.asemiashkin.persistence

import com.asemiashkin.Link

interface LinksRepository : CRUDRepository<Long, Link> {

    override fun create(toAdd: Link) : Long
    override fun update(toUpdate: Link) : Boolean
    override fun remove(id: Long) : Boolean
    override fun fetch(id: Long) : Link
    override fun fetchAll() : List<Link>
}