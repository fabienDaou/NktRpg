package NktRpg.Server.SqlStore

import org.jetbrains.exposed.sql.*

class PostgreSqlConnection(private val user: String, private val password: String, host: String, port: Int, database: String) : ISqlConnection {
    private val jdbc = "jdbc:postgresql://$host:$port/$database?sslmode=require"
    private val driver = "org.postgresql.Driver"

    override fun connect(): Database = Database.connect(jdbc, driver, user, password)
}