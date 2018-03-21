package NktRpg.Server.SqlStore

import org.jetbrains.exposed.sql.Database

interface ISqlConnection{
    fun connect(): Database
}