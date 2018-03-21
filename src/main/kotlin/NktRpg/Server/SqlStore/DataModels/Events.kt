package NktRpg.Server.SqlStore

import org.jetbrains.exposed.sql.*

object Events: Table(){
    val id = integer("id").autoIncrement().primaryKey()
    val sessionId = (integer("session_id") references Sessions.id)
    val date = datetime("date")
    val location = varchar("location", 20)
    val description = varchar("description", 500)
}