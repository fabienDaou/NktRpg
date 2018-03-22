package NktRpg.Server.Routes

import NktRpg.Server.CommunicationInterfaces.SessionCreatedResponse
import NktRpg.Server.NktRpgModel.Session
import NktRpg.Server.Store.INktRpgStore
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post

fun Route.createSession(store: INktRpgStore) {
    post("/session") {
        val newSession = call.receive<Session>()
        val newSessionId = store.add(newSession)
        call.respond(HttpStatusCode.Created, SessionCreatedResponse(newSessionId))
    }
}