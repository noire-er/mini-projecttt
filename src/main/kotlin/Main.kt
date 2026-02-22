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

        // Serve your static HTML files from resources/static
        staticResources("/", "static")

        // Handle signup form submission
        post("/signup") {

            val params = call.receiveParameters()

            val name = params["name"] ?: ""
            val surname = params["surname"] ?: ""
            val email = params["email"] ?: ""
            val address = params["address"] ?: ""

            // Basic validation
            if (!email.contains("@")) {
                call.respondText("Invalid email address: missing @")
                return@post
            }

            if (name.length < 2) {
                call.respondText("First name must be at least 2 characters")
                return@post
            }

            if (surname.length < 2) {
                call.respondText("Last name must be at least 2 characters")
                return@post
            }

            if (address.length < 5) {
                call.respondText("Please enter a valid address")
                return@post
            }

            // If everything is valid
            call.respondText("Signup successful!")
        }
    }
}
