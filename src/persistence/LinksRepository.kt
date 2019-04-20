package com.asemiashkin.persistence

import com.asemiashkin.Link

interface LinksRepository {

    fun create(link: Link) : Long
    fun update(link: Link) : Boolean
    fun remove(link: Link) : Boolean
    fun remove(linkId: Long) : Boolean
    fun fetch(linkId: Long) : Link
    fun fetchAll() : List<Link>
}