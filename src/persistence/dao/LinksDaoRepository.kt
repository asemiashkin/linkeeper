package com.asemiashkin.persistence.dao

import com.asemiashkin.Link
import com.asemiashkin.Links
import com.asemiashkin.persistence.LinksRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.net.URI

class LinksDaoRepository: LinksRepository {

    override fun create(newLink: Link): Long = transaction {
        (Links.insert {
            it[title] = newLink.title
            it[link] = newLink.link.toString()
            it[userId] = newLink.userId
        } get Links.id)!!
    }

    override fun update(updLink: Link): Boolean = transaction {
        Links.update({Links.id eq updLink.id}) {
            it[title] = updLink.title
            it[link] = updLink.link.toString()
            it[userId] = updLink.userId
        } > 0
    }

    override fun remove(link: Link): Boolean {
        return remove(link.id)
    }

    override fun remove(linkId: Long): Boolean = transaction {
         Links.deleteWhere { Links.id eq linkId } > 0
    }

    override fun fetch(linkId: Long): Link = transaction {
        Links.select { Links.id eq linkId }.first().toLink()
    }

    override fun fetchAll(): List<Link> = transaction {
        Links.selectAll().toList().map {it.toLink()}
    }

    private fun ResultRow.toLink() : Link {
        return Link(id = this[Links.id],
            title = this[Links.title],
            link = URI.create(this[Links.link]),
            userId = this[Links.userId])
    }


}