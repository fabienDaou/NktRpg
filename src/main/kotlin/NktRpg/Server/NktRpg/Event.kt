package NktRpg.Server.NktRpg

import java.time.LocalDateTime

class Event(val date: LocalDateTime = LocalDateTime.now(), val location: String, val description: String){
    var id: Int = 0
        private set

    var sessionId: Int = 0
        private set

    constructor(id: Int, sessionId: Int, date: LocalDateTime, location: String, description: String)
            : this(date, location, description){
        this.id = id
        this.sessionId = sessionId
    }
}