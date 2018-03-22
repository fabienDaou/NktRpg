package NktRpg.Server.SqlStore

import NktRpg.Server.NktRpgModel.Event
import NktRpg.Server.NktRpgModel.Session
import NktRpg.Server.Store.INktRpgStore
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

class NktRpgSqlStore(private val sqlConnection: ISqlConnection) : INktRpgStore {
    suspend override fun add(event: Event): Int? {
        if (event.sessionId != null) {
            var newEventId: Int? = null
            val nonNullSessionId = event.sessionId!!

            sqlConnection.connect()
            transaction {
                newEventId = Events.insert {
                    it[sessionId] = nonNullSessionId
                    it[date] = DateTime(System.currentTimeMillis())
                    it[location] = event.location
                    it[description] = event.description
                } get Events.id
            }
            return newEventId
        }
        return null
    }

    suspend override fun add(session: Session): Int {
        var sessionId: Int? = null

        sqlConnection.connect()
        transaction {
            sessionId = Sessions.insert {
                it[date] = DateTime(System.currentTimeMillis())
                it[title] = session.title
            } get Sessions.id
        }
        return sessionId!!
    }

    suspend override fun getSessions(): Iterable<Session> {
        val listOfSessions = mutableListOf<Session>()

        sqlConnection.connect()
        transaction {
            Sessions.selectAll().forEach {
                var session = Session(it[Sessions.id], it[Sessions.title], it[Sessions.date].millis)
                listOfSessions.add(session)
            }
        }
        return listOfSessions
    }

    suspend override fun getEventsBySessionId(sessionId: Int): Iterable<Event> {
        val listOfEvents = mutableListOf<Event>()
        sqlConnection.connect()
        transaction {
            Events.select { Events.sessionId.eq(sessionId) }.forEach {
                var event = Event(it[Events.id], it[Events.sessionId], it[Events.location], it[Events.description], it[Events.date].millis)
                listOfEvents.add(event)
            }
        }
        return listOfEvents
    }
}