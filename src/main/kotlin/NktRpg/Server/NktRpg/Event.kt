package NktRpg.Server.NktRpg

data class Event(val id: Int?, val sessionId: Int?, val location: String, val description: String, val date: Long = System.currentTimeMillis())