package com.asemiashkin.services

import com.asemiashkin.Link
import com.asemiashkin.persistence.LinksRepository

class LinksService(private val linksRepository: LinksRepository) {

    fun create(link: Link): Long {
        return linksRepository.create(link)
    }

    fun update(link: Link) : Boolean {
        return linksRepository.update(link)
    }

    fun remove(linkId: Long) : Boolean {
        return linksRepository.remove(linkId)
    }

    fun fetch(linkId: Long) : Link {
        return linksRepository.fetch(linkId)
    }

    fun fetchAll() : List<Link> {
        return linksRepository.fetchAll()
    }

}