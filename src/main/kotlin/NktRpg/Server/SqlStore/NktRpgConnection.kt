package NktRpg.Server.SqlStore

import org.jetbrains.exposed.sql.*

class NktRpgConnection(private val user: String, private val password: String){
    private val jdbc = "jdbc:mysql://localhost:3306/nktrpg"
    private val driver = "com.mysql.jdbc.Driver"

    fun connect(): Database = Database.connect(jdbc, driver, user, password)
}