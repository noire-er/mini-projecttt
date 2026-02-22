fun main() {
    val email = "tester@example.com"
    val name = "John"
    val surname = "Doe"
    val password = "Password1!"

    var isValid = true  // tracks overall validity

    // Email validations
    if (email.isEmpty()) {
        println("Email cannot be empty!")
        isValid = false
    }
    if (email.contains(" ")) {
        println("Email cannot contain blank spaces!")
        isValid = false
    }
    if (!email.contains("@")) {
        println("Invalid email address: missing '@'")
        isValid = false
    }
    if (!email.contains(".")) {
        println("Invalid email address: missing '.'")
        isValid = false
    }

    // Name validations
    if (name.length < 2) {
        println("Your name must be at least 2 letters!")
        isValid = false
    }
    if (name.any { it.isDigit() }) {
        println("Your name cannot contain numbers!")
        isValid = false
    }

    // Surname validations
    if (surname.length < 2) {
        println("Your surname must be at least 2 letters!")
        isValid = false
    }
    if (surname.any { it.isDigit() }) {
        println("Your surname cannot contain numbers!")
        isValid = false
    }

    // Password validations
    if (password.isEmpty()) {
        println("Password cannot be empty!")
        isValid = false
    }
    if (password.length < 8) {
        println("Password must be at least 8 characters long!")
        isValid = false
    }
    if (!password.any { it.isUpperCase() }) {
        println("Password must contain at least one uppercase letter!")
        isValid = false
    }
    if (!password.any { it.isLowerCase() }) {
        println("Password must contain at least one lowercase letter!")
        isValid = false
    }

    // Final result
    if (isValid) {
        println("Login successful! Welcome, $name $surname")
    } else {
        println("Login failed. Please fix the errors above.")
    }
}