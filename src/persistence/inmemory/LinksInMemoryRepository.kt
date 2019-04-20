package com.asemiashkin.persistence.inmemory

import com.asemiashkin.Link
import com.asemiashkin.persistence.LinksRepository
import kotlin.random.Random

class LinksInMemoryRepository: LinksRepository {

    private val linksList: MutableList<Link> = ArrayList()

    private fun <R> linksSynchronized(block: () -> R): R = synchronized(linksList) {
        return block()
    }

    override fun create(newLink: Link): Long = linksSynchronized {
        newLink.id = provideId()
        linksList.add(newLink)
        newLink.id
    }

    override fun update(updLink: Link): Boolean = linksSynchronized {
        val existing: Link? = linksList.first { it.id == updLink.id }
        if (existing == null) {
            false
        } else {
            existing.link = updLink.link
            existing.title = updLink.title
            existing.userId = updLink.userId
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