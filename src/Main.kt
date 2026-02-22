import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.http.content.*

fun main() {
    embeddedServer(Netty, port = 8080) {
        module()
    }.start(wait = true)
}

fun Application.module() {

    routing {

        // Serve static HTML files
        staticResources("/", "static")

        // Handle signup form
        post("/signup") {

            val params = call.receiveParameters()

            val name = params["name"] ?: ""
            val surname = params["surname"] ?: ""
            val email = params["email"] ?: ""

            // Validation
            if (!email.contains("@")) {
                call.respondText("Invalid email address: missing @")
                return@post
            }

            if (name.length < 2) {
                call.respondText("Name must be at least 2 letters")
                return@post
            }

            if (surname.length < 2) {
                call.respondText("Surname must be at least 2 letters")
                return@post
            }

            call.respondText("Signup successful!")
        }
    }
}

