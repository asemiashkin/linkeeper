package com.asemiashkin.persistence.dao

import com.asemiashkin.Link
import com.asemiashkin.Links
import com.asemiashkin.persistence.LinksRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.net.URI

class LinksDaoRepository : LinksRepository {

    override fun create(toAdd: Link): Long = transaction {
        (Links.insert {
            it[title] = toAdd.title
            it[link] = toAdd.link.toString()
            it[userId] = toAdd.userId
        } get Links.id)!!
    }

    override fun update(toUpdate: Link): Boolean = transaction {
        Links.update({ Links.id eq toUpdate.id }) {
            it[title] = toUpdate.title
            it[link] = toUpdate.link.toString()
            it[userId] = toUpdate.userId
        } > 0
    }

    override fun remove(id: Long): Boolean = transaction {
        Links.deleteWhere { Links.id eq id } > 0
    }

    override fun fetch(id: Long): Link = transaction {
        Links.select { Links.id eq id }.first().toLink()
    }

    override fun fetchAll(): List<Link> = transaction {
        Links.selectAll().toList().map { it.toLink() }
    }

    private fun ResultRow.toLink(): Link = Link(
        id = this[Links.id],
        title = this[Links.title],
        link = URI.create(this[Links.link]),
        userId = this[Links.userId]
    )
    

}