package NktRpg.Server.Routes

import NktRpg.Server.Store.INktRpgStore
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get

fun Route.getEventsBySession(store: INktRpgStore) {
    get("/events/session/{sessionId}") {
        try {
            val sessionId = call.parameters["sessionId"]?.toInt()
            when (sessionId) {
                null -> {
                    call.respond(HttpStatusCode.BadRequest)
                }
                else -> {
                    val listOfEvents = store.getEventsBySessionId(sessionId)
                    call.respond(HttpStatusCode.OK, listOfEvents)
                }
            }
        } catch (ex: Throwable) {
            call.respondText(ex.message!!)
        }
    }
}