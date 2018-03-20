package NktRpg.Server

import NktRpg.Server.NktRpg.Event
import NktRpg.Server.NktRpg.Session
import NktRpg.Server.SqlStore.NktRpgSqlStore
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.nktRpgServerModule() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        get("/events"){
            var event = Event("Netherlands", "Description")
            call.respondText("${event.date} / ${event.location}", ContentType.Text.Html)
        }
        get("/sessions"){
            var session = Session("Montroc")
            call.respondText("${session.date} / ${session.title}", ContentType.Text.Html)
        }

        // TODO("post instead")
        get("/addSession"){
            var session = Session("Montroc")
            try {
                NktRpgSqlStore().add(session)
                call.respondText("success", ContentType.Text.Html)
            }
            catch (ex: Throwable){
                call.respond(HttpStatusCode.InternalServerError)
            }
        }
        get("/sessions"){
            try {
                var appendedSessions = StringBuilder()
                val listOfSessions = NktRpgSqlStore().getSessions()
                listOfSessions.forEach{ appendedSessions.append(it) } // TODO("does not append for whatever reason")
                call.respondText(appendedSessions.toString(), ContentType.Text.Html)
            }
            catch (ex: Throwable){
                call.respond(HttpStatusCode.InternalServerError)
            }
        }
    }
}