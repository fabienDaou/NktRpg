package NktRpg.Server.NktRpg

import java.time.LocalDateTime

class Session(val title: String, val date: LocalDateTime = LocalDateTime.now()){
    var id: Int = 0
        private set

    constructor(id: Int, title: String, date: LocalDateTime)
            : this( title, date){
        this.id = id
    }
}