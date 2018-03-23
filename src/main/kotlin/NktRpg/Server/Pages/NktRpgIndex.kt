package NktRpg.Server.Pages

import NktRpg.Server.NktRpgModel.Session
import kotlinx.html.*
import io.ktor.html.Template

class NktRpgIndex(private val sessions: Iterable<Session>) : Template<HTML> {
    override fun HTML.apply() {
        head {
            title { +"NktRpg" }
            style(type="text/css") {
                +"a.session-ref {text-decoration: none;}"
                +"a.session-ref:hover {text-decoration: underline;}"
            }
            script(type = ScriptType.textJavaScript) {
                unsafe { // héhé
                    raw("""
                            function FillEventList(sessionId) {
                                var events = GetEventsBySession(sessionId);
                                var eventListTag = document.getElementById("event-list-div");
                                ClearChildrenTags(eventListTag);

                                for (var i = 0; i < events.length; i++) {
                                    AppendEventTo(events[i], eventListTag);
                                }

                            }

                            function GetEventsBySession(sessionId) {
                                var xmlHttp = new XMLHttpRequest();
                                xmlHttp.open("GET", "https://nktrpg.herokuapp.com/events/session/"+sessionId, false);
                                xmlHttp.send(null);
                                return JSON.parse(xmlHttp.responseText);
                            }

                            function ClearChildrenTags(parentTag) {
                                parentTag.innerHTML = '';
                            }

                            function AppendEventTo(event, parentTag) {
                                var pre = document.createElement("pre");
                                var content = document.createTextNode(event.description);
                                pre.appendChild(content);
                                parentTag.appendChild(pre);
                            }
                            """
                    )
                }
            }
        }

        body {
            style = "background-color:black;"
            style += "color:white;"
            style += "font-family:Courier New;"
            style += "font-size:90%"

            div(classes="wrapper") {
                style = "max-width:90%;"
                style += "margin:auto;"

                div(classes="column-session") {
                    style = "margin:20px auto 0px auto;"
                    style = "width:300px;"
                    style += "float:left;"

                    div {
                        style = "font-weight:bold;"

                        +"List of sessions"
                    }

                    div(classes = "session-list") {
                        style = "color:rgb(192, 192, 192);"

                        for (session in sessions) {
                            pre {
                                style = "word-break:break-all;"
                                style += "white-space: pre-wrap;"
                                style += "text-decoration:none;"

                                a(classes = "session-ref") {
                                    onClick = "FillEventList("+session.id+")"

                                    +session.title
                                }
                            }
                        }
                    }
                }
                div(classes="column-event") {
                    style = "overflow:hidden;"

                    div {
                        style = "font-weight:bold;"

                        +"List of events"
                    }

                    div{
                        id="event-list-div"
                        style = "color:rgb(192, 192, 192);"
                    }
                }
            }
        }
    }
}