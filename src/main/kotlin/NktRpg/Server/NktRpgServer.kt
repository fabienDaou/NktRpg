package NktRpg.Server

import NktRpg.Server.SqlStore.MySqlConnection
import NktRpg.Server.SqlStore.NktRpgSqlStore
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    if(args.size == 6){
        val serverPort = args[0].toInt()
        val sqlConnection = MySqlConnection(args[1], args[2], args[3], args[4].toInt(), args[5])
        val store = NktRpgSqlStore(sqlConnection)
        embeddedServer(Netty, serverPort) { nktRpgServerModule(store) }.start()
    }
}