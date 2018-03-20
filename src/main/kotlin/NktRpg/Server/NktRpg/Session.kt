package NktRpg.Server.NktRpg

class Session(val title: String, val date: Long = System.currentTimeMillis()){
    var id: Int = 0
        private set

    constructor(id: Int, title: String, date: Long)
            : this( title, date){
        this.id = id
    }
}