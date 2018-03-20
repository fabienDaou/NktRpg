package NktRpg.Server.NktRpg

class Event(val location: String, val description: String, val date: Long = System.currentTimeMillis()){
    var id: Int = 0
        private set

    var sessionId: Int = 0
        private set

    constructor(id: Int, sessionId: Int, location: String, description: String, date: Long)
            : this(location, description, date){
        this.id = id
        this.sessionId = sessionId
    }
}