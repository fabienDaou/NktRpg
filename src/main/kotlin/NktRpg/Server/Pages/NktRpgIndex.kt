package NktRpg.Server.Pages

import NktRpg.Server.NktRpgModel.Session
import kotlinx.html.*
import io.ktor.html.Template

class NktRpgIndex(private val sessions: Iterable<Session>) : Template<HTML> {
    override fun HTML.apply() {
        head {
            title { +"NktRpg" }
            style("text/css") {
                +"a.session-ref {text-decoration: none;}"
                +"a.session-ref:hover {text-decoration: underline;}"
            }
            script(type = ScriptType.textJavaScript) {
                unsafe { // héhé
                    raw("""
                            function GetEventsBySessionId(id)
                            {
                                var xmlHttp = new XMLHttpRequest();
                                xmlHttp.open( "GET", "https://nktrpg.herokuapp.com/events/session/"+id, false );
                                xmlHttp.send( null );
                                console.log(xmlHttp.responseText);
                            }"""
                    )
                }
            }
        }

        body {
            style = "background-color:black;"
            style += "color:white;"
            style += "font-family:Courier New;"
            style += "font-size:90%"

            div("wrapper") {
                style = "max-width:90%;"
                style += "margin:auto;"

                div("column-session") {
                    style = "margin:20px auto 0px auto;"
                    style = "width:300px;"
                    style += "float:left;"

                    div {
                        style = "font-weight:bold;"

                        +"List of sessions"
                    }

                    div {
                        style = "color:rgb(192, 192, 192);"

                        for (session in sessions) {
                            pre {
                                style = "word-break:break-all;"
                                style += "white-space: pre-wrap;"
                                style += "text-decoration:none;"

                                a(classes = "session-ref") {
                                    onClick = "GetEventsBySessionId("+session.id+")"

                                    +session.title
                                }
                            }
                        }
                    }
                }
                div("column-event") {
                    style = "overflow:hidden;"

                    div {
                        style = "font-weight:bold;"

                        +"List of events"
                    }
                }
            }
        }
    }
}