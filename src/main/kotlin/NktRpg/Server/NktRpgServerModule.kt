package NktRpg.Server

import NktRpg.Server.CommunicationInterfaces.*
import NktRpg.Server.NktRpg.*
import NktRpg.Server.SqlStore.NktRpgSqlStore
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.gson
import io.ktor.http.*
import io.ktor.request.receive
import io.ktor.response.*
import io.ktor.routing.*

fun Application.nktRpgServerModule() {
    install(DefaultHeaders)
    install(ContentNegotiation) { gson { setPrettyPrinting() } }
    install(StatusPages) { exception<Throwable> { cause -> call.respond(HttpStatusCode.InternalServerError) } }
    install(Routing) {
        post("/session") {
            val newSession = call.receive<Session>()
            val newSessionId = NktRpgSqlStore().add(newSession)
            when (newSessionId) {
                null -> {
                    call.respond(HttpStatusCode.BadRequest)
                }
                else -> {
                    call.respond(HttpStatusCode.Created, SessionCreatedResponse(newSessionId))
                }
            }
        }

        post("/event") {
            val newEvent = call.receive<Event>()
            val newEventId = NktRpgSqlStore().add(newEvent)
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
            val listOfSessions = NktRpgSqlStore().getSessions()
            call.respond(HttpStatusCode.OK, listOfSessions)
        }

        get("/events/session/{sessionId}") {
            try{
                val sessionId = call.parameters["sessionId"]?.toInt()
                when (sessionId) {
                    null -> {
                        call.respond(HttpStatusCode.BadRequest)
                    }
                    else -> {
                        val listOfEvents = NktRpgSqlStore().getEventsBySessionId(sessionId)
                        call.respond(HttpStatusCode.OK, listOfEvents)
                    }
                }
            }
            catch (ex: Throwable){
                call.respondText(ex.message!!)
            }
        }
    }
}