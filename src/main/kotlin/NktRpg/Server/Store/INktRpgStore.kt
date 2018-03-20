package NktRpg.Server.Store

import NktRpg.Server.NktRpg.Event
import NktRpg.Server.NktRpg.Session

interface INktRpgStore{
    suspend fun Add(event: Event): Boolean
    suspend fun Add(session: Session): Boolean
    suspend fun GetSessions(): Iterable<Session>
    suspend fun GetEventsBySessionId(sessionId: Int): Iterable<Event>
}