package NktRpg.Server.Routes

import NktRpg.Server.Pages.NktRpgIndex
import NktRpg.Server.Store.INktRpgStore
import io.ktor.routing.*
import io.ktor.application.call
import io.ktor.html.respondHtmlTemplate

fun Route.getBase(store: INktRpgStore) {
    get("/") {
        val sessions = store.getSessions()
        call.respondHtmlTemplate(NktRpgIndex(sessions)){}
    }
}