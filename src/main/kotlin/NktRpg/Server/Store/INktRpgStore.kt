package NktRpg.Server.Store

import NktRpg.Server.NktRpg.Event
import NktRpg.Server.NktRpg.Session

interface INktRpgStore{
    suspend fun add(event: Event)
    suspend fun add(session: Session)
    suspend fun getSessions(): Iterable<Session>
    suspend fun getEventsBySessionId(sessionId: Int): Iterable<Event>
}