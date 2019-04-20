package com.asemiashkin

import com.asemiashkin.persistence.inmemory.LinksInMemoryRepository
import com.asemiashkin.routings.linksRest
import com.asemiashkin.services.LinksService
import io.ktor.application.*
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.routing.Routing
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.text.DateFormat
import java.time.Duration

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")
    install(DefaultHeaders)
    install(CORS) {
        maxAge = Duration.ofDays(1)
    }
    install(ContentNegotiation) {
        gson {
            setDateFormat(DateFormat.LONG)
            setPrettyPrinting()
        }
    }

    install(Routing) {
        linksRest(LinksService(LinksInMemoryRepository()))
    }

    transaction {
        SchemaUtils.create (Links, Users)
    }

}



