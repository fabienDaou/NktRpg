package NktRpg.Server.Pages

import NktRpg.Server.NktRpgModel.Session
import kotlinx.html.*
import io.ktor.html.Template

class NktRpgIndex(private val sessions: Iterable<Session>) : Template<HTML> {
    override fun HTML.apply() {
        head {
            title { +"NktRpg" }
        }

        body {
            style = "background-color:black;"
            style += "color:white;"
            style += "font-family:Courier New;"
            style += "font-size:90%"

            div("column-session") {
                style = "margin:20px auto 0px auto;"
                style += "max-width:90%;"

                div {
                    style = "font-weight:bold;"

                    +"List of sessions"
                }

                div {
                    style="color:rgb(192, 192, 192);"

                    for (session in sessions) {
                        pre {
                            +session.title
                        }
                    }
                }
            }
            div("column-event") {
            }
        }
    }
}