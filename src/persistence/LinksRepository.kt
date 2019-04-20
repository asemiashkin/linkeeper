package com.asemiashkin.persistence

import com.asemiashkin.Link

interface LinksRepository {

    fun create(newLink: Link) : Long
    fun update(updLink: Link) : Boolean
    fun remove(link: Link) : Boolean
    fun remove(linkId: Long) : Boolean
    fun fetch(linkId: Long) : Link
    fun fetchAll() : List<Link>
}