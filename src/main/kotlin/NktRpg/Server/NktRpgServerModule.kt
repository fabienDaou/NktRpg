package NktRpg.Server

import NktRpg.Server.Routes.*
import NktRpg.Server.Store.INktRpgStore
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.gson
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.nktRpgServerModule(store: INktRpgStore) {
    install(DefaultHeaders)
    install(CORS) {
        host("nkt.herokuapp.com", schemes = listOf("https"), subDomains = listOf("www"))
    }
    install(ContentNegotiation) { gson { setPrettyPrinting() } }
    install(StatusPages) { exception<Throwable> { _ -> call.respond(HttpStatusCode.InternalServerError) } }
    install(Routing) {
        getBase(store)
        createSession(store)
        createEvent(store)
        getSessions(store)
        getEventsBySession(store)
    }
}