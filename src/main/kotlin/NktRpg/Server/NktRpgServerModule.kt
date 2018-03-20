package NktRpg.Server

import NktRpg.Server.NktRpg.Event
import NktRpg.Server.NktRpg.Session
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import java.time.LocalDateTime

fun Application.nktRpgServerModule() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        get("/events"){
            var event = Event(LocalDateTime.now(), "Netherlands", "Description")
            call.respondText("${event.date} / ${event.location}", ContentType.Text.Html)
        }
        get("/sessions"){
            var session = Session("Montroc")
            call.respondText("${session.date} / ${session.title}", ContentType.Text.Html)
        }
    }
}

