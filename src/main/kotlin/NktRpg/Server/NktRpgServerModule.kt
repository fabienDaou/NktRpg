package NktRpg.Server

import NktRpg.Server.CommunicationInterfaces.*
import NktRpg.Server.NktRpg.*
import NktRpg.Server.Store.INktRpgStore
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.gson
import io.ktor.http.*
import io.ktor.request.receive
import io.ktor.response.*
import io.ktor.routing.*

fun Application.nktRpgServerModule(store: INktRpgStore) {
    install(DefaultHeaders)
    install(CORS){
        host("nkt.herokuapp.com")
        host("nktrpg.herokuapp.com")
    }
    install(ContentNegotiation) { gson { setPrettyPrinting() } }
    install(StatusPages) { exception<Throwable> { _ -> call.respond(HttpStatusCode.InternalServerError) } }
    install(Routing) {
        post("/session") {
            val newSession = call.receive<Session>()
            val newSessionId = store.add(newSession)
            call.respond(HttpStatusCode.Created, SessionCreatedResponse(newSessionId))
        }

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

        get("/sessions") {
            val listOfSessions = store.getSessions()
            call.respond(HttpStatusCode.OK, listOfSessions)
        }

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
}