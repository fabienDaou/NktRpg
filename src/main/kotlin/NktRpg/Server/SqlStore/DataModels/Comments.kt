package NktRpg.Server.SqlStore

import org.jetbrains.exposed.sql.*

object Comments: Table(){
    val id = integer("id").autoIncrement().primaryKey()
    val eventId = (integer("event_id") references Events.id)
    val parentId = (integer("parent_id") references Comments.id)
    val date = datetime("date")
    val content = varchar("content", 500)
    val username = varchar("username", 20)
}