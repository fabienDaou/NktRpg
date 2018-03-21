package NktRpg.Server.SqlStore

import org.jetbrains.exposed.sql.*

object Sessions: Table(){
    val id = integer("id").autoIncrement().primaryKey()
    val date = datetime("date")
    val title = varchar("title", 50)
}