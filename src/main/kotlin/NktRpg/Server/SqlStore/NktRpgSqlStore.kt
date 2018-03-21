package NktRpg.Server.SqlStore

import NktRpg.Server.NktRpg.Event
import NktRpg.Server.NktRpg.Session
import NktRpg.Server.Store.INktRpgStore
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

class NktRpgSqlStore: INktRpgStore {
    // TODO("Get rid off username/password. pass them as argument to the server.")
    private val user = ""
    private val password = ""

    suspend override fun add(event: Event) {
        NktRpgConnection(user, password).connect()
        transaction {
            Events.insert {
                it[sessionId] = event.sessionId
                it[date] = DateTime(event.date)
                it[location] = event.location
                it[description] = event.description
            }
        }
    }

    suspend override fun add(session: Session) {
        NktRpgConnection(user, password).connect()
        transaction {
            Sessions.insert {
                it[date] = DateTime(session.date)
                it[title] = session.title
            }
        }
    }

    suspend override fun getSessions(): Iterable<Session> {
        NktRpgConnection(user, password).connect()
        val listOfSessions = mutableListOf<Session>()
        transaction {
            Sessions.selectAll().forEach {
                var session = Session(it[Sessions.id], it[Sessions.title], it[Sessions.date].millis)
                listOfSessions.add(session)
            }
        }
        return listOfSessions
    }

    suspend override fun getEventsBySessionId(sessionId: Int): Iterable<Event> {
        NktRpgConnection(user, password).connect()
        val listOfEvents = mutableListOf<Event>()
        Events.select { Events.sessionId.eq(sessionId) }.forEach {
            var event = Event(it[Events.id], it[Events.sessionId], it[Events.location], it[Events.description], it[Events.date].millis)
            listOfEvents.add(event)
        }
        return listOfEvents
    }
}