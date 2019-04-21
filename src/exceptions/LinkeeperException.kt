package com.asemiashkin.exceptions

import io.ktor.http.HttpStatusCode

class LinkeeperException : Exception() {
    val statusCode = HttpStatusCode.BadRequest
}