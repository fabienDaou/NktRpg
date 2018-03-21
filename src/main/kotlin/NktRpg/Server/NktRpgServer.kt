package NktRpg.Server

import NktRpg.Server.SqlStore.PostgreSqlConnection
import NktRpg.Server.SqlStore.NktRpgSqlStore
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    if (args.size == 6) {
        val serverPort = args[0].toInt()
        val sqlConnection = PostgreSqlConnection(args[1], args[2], args[3], args[4].toInt(), args[5])
        val store = NktRpgSqlStore(sqlConnection)
        embeddedServer(Netty, serverPort) { nktRpgServerModule(store) }.start()
    } else {
        val host = System.getenv()["DATABASE_HOST"]
        val port = System.getenv()["DATABASE_PORT"]
        val name = System.getenv()["DATABASE_NAME"]
        val user = System.getenv()["DATABASE_USER"]
        val password = System.getenv()["DATABASE_PASSWORD"]
        val serverPort = System.getenv()["PORT"]

        val sqlConnection = PostgreSqlConnection(user!!, password!!, host!!, port!!.toInt(), name!!)
        val store = NktRpgSqlStore(sqlConnection)
        embeddedServer(Netty, serverPort!!.toInt()) { nktRpgServerModule(store) }.start()
    }
}