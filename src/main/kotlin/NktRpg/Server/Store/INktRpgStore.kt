package NktRpg.Server.Store

import NktRpg.Server.NktRpgModel.Event
import NktRpg.Server.NktRpgModel.Session

interface INktRpgStore{
    suspend fun add(event: Event): Int?
    suspend fun add(session: Session): Int
    suspend fun getSessions(): Iterable<Session>
    suspend fun getEventsBySessionId(sessionId: Int): Iterable<Event>
}