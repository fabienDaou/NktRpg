package NktRpg.Server.Routes

import NktRpg.Server.CommunicationInterfaces.EventCreatedResponse
import NktRpg.Server.NktRpgModel.Event
import NktRpg.Server.Store.INktRpgStore
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post

fun Route.createEvent(store: INktRpgStore) {
    post("/event") {
        val newEvent = call.receive<Event>()
        val newEventId = store.add(newEvent)
        when (newEventId) {
            null -> {
                call.respond(HttpStatusCode.BadRequest)
            }
            else -> {
                call.respond(HttpStatusCode.Created, EventCreatedResponse(newEventId))
            }
        }
    }
}