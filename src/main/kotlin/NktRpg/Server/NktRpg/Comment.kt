package NktRpg.Server.NktRpg

data class Comment(val id: Int?, val eventId: Int?,val parentId: Int?,  val username: String,val content: String, val date: Long = System.currentTimeMillis())