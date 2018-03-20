package NktRpg.Server

import io.ktor.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    embeddedServer(Netty, 8080, module = Application::nktRpgServerModule).start()
}