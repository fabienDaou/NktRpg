package NktRpg.Server.Routes

import NktRpg.Server.Store.INktRpgStore
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get

fun Route.getSessions(store: INktRpgStore){
    get("/sessions") {
        val listOfSessions = store.getSessions()
        call.respond(HttpStatusCode.OK, listOfSessions)
    }
}