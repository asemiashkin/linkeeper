package com.asemiashkin.persistence.inmemory

import com.asemiashkin.Link
import com.asemiashkin.persistence.LinksRepository
import kotlin.random.Random

class LinksInMemoryRepository: LinksRepository {

    private val linksList: MutableList<Link> = ArrayList()

    private fun <R> linksSynchronized(block: () -> R): R = synchronized(linksList) {
        return block()
    }

    override fun create(link: Link): Long = linksSynchronized {
        link.id = provideId()
        linksList.add(link)
        link.id
    }

    override fun update(link: Link): Boolean = linksSynchronized {
        val existing: Link? = linksList.first { it.id == link.id }
        if (existing == null) {
            false
        } else {
            existing.link = link.link
            existing.title = link.title
            existing.userId = link.userId
            true
        }
    }

    override fun remove(link: Link): Boolean = linksSynchronized {
        remove(link.id)
    }

    override fun remove(linkId: Long): Boolean = linksSynchronized {
        linksList.removeIf { it.id == linkId }
    }

    override fun fetch(linkId: Long): Link = linksSynchronized {
        linksList.first { it.id == linkId }
    }

    override fun fetchAll(): List<Link> = linksSynchronized {
        ArrayList(linksList)
    }


    private fun provideId(): Long {
        return Random.nextLong()
    }

}