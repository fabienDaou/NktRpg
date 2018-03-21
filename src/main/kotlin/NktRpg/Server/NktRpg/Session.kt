package NktRpg.Server.NktRpg

data class Session(val id: Int?, val title: String, val date: Long = System.currentTimeMillis())