// want to add basic validation errors for website

fun main() {
    val email = "tester@example.com"
    if (!email.contains()"@)) {
        println("This is an Invalid email address: missing: @)
    } else {
        println("Email is valid")
    }
// this is for missing '@' in an email address
// Length validation
    if (name.length < 2) {
        println("Your name must be at least 2 letters!")
    } else {
        println("Name is valid")
    }

    if (surname.length < 2) {
        println("Your surname must be at least 2 letters!")
    } else {
        println("Surname is valid")
    }

// Numbers in name validation
    if (name.any { it.isDigit() }) {
        println("Your name cannot contain numbers!")
    } else {
        println("Name is valid")
    }

// Numbers in surname validation
    if (surname.any { it.isDigit() }) {
        println("Your surname cannot contain numbers!")
    } else {
        println("Surname is valid")
    }

// Validate password not being empty
    if (password.isEmpty()) {
        println("Password cannot be empty!")
    } else {
        println("Password is valid")
    }

// Validate password length
    if password.length < 8) {
        println("Password must be at least 8 characters long!")
    } else {
        println("Password is valid")
    }

// Validate email containing "."
    if (!email.contains(".")) {
        println("This is an invalid email address: missing '.'")
    } else {
        println("Email is valid")
    }

// Validate email not being empty
    if (email.isEmpty()) {
        println("Email cannot be empty!")
    } else {
        println("Email is valid")
    }

//  Validate password containing an uppercase letter
    if (!password.any { it.isUpperCase() }) {
        println("Password must contain at least one uppercase letter!")
    } else {
        println("Password is valid")
    }

// Validate password containing a lowercase letter
    if (!password.any { it.isLowerCase() }) {
        println("Password must contain at least one lowercase letter!")
    } else {
        println("Password is valid")
    }

// Validate email not having a blank space
    if (email.contains(" ")) {
        println("Email cannot contain blank spaces!")
    } else {
        println("Email is valid")
    }
    }
    }
    }
    }
    }
    }
    }
    }

}

