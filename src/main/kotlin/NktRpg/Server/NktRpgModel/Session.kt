package NktRpg.Server.NktRpgModel

data class Session(val id: Int?, val title: String, val date: Long = System.currentTimeMillis())