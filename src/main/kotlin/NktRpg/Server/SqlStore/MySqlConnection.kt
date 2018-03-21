package NktRpg.Server.SqlStore

import org.jetbrains.exposed.sql.*

class MySqlConnection(private val user: String, private val password: String, host: String, port: Int, database: String): ISqlConnection{
    private val jdbc = "jdbc:mysql://$host:$port/$database"
    private val driver = "com.mysql.jdbc.Driver"

    override fun connect(): Database = Database.connect(jdbc, driver, user, password)
}