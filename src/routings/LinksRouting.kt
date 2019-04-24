package com.asemiashkin.routings

import com.asemiashkin.CreateLinkResponse
import com.asemiashkin.Link
import com.asemiashkin.UDLinkResponse
import com.asemiashkin.services.LinksService
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import java.lang.Exception


fun Routing.linksRest(linksService: LinksService) = route("/links") {

    get {
        call.respond(linksService.fetchAll())
    }

    get("/{id}") {
        val id: Long? = try {
            call.parameters["id"]?.toLong()
        } catch (e: Exception) {
            0
        }
        val link: Link? = linksService.fetch(id!!)
        if (link == null) call.respond(HttpStatusCode.NotFound)
        call.respond(link!!)
    }

    post {
        call.respond(CreateLinkResponse(linksService.create(call.receive())))
    }

    put("/{id}") {
        call.respond(UDLinkResponse(linksService.update(call.receive())))
    }

    delete("/{id}") {
        val id: Long? = try {
            call.parameters["id"]?.toLong()
        } catch (e: Exception) {
            0
        }
        call.respond(UDLinkResponse(linksService.remove(id!!)))
    }


}