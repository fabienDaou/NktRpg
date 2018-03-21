package NktRpg.Server.NktRpg

data class Event(val location: String, val description: String, val date: Long = System.currentTimeMillis()){
    var id: Int? = null
        private set

    var sessionId: Int? = null
        private set

    constructor(id: Int, sessionId: Int, location: String, description: String, date: Long)
            : this(location, description, date){
        this.id = id
        this.sessionId = sessionId
    }
}