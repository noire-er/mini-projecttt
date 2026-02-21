public class Main {
    public static void main(String[] args) {
        String email = "tester@example.com";
        String name = "John";
        String surname = "Doe";
        String password = "Password1!";

        boolean isValid = true;

        // Email validations
        if (email.isEmpty()) {
            System.out.println("Email cannot be empty!");
            isValid = false;
        }
        if (email.contains(" ")) {
            System.out.println("Email cannot contain blank spaces!");
            isValid = false;
        }
        if (!email.contains("@")) {
            System.out.println("Invalid email address: missing '@'");
            isValid = false;
        }
        if (!email.contains(".")) {
            System.out.println("Invalid email address: missing '.'");
            isValid = false;
        }

        // Name validations
        if (name.length() < 2) {
            System.out.println("Your name must be at least 2 letters!");
            isValid = false;
        }
        for (char c : name.toCharArray()) {
            if (Character.isDigit(c)) {
                System.out.println("Your name cannot contain numbers!");
                isValid = false;
                break;
            }
        }

        // Surname validations
        if (surname.length() < 2) {
            System.out.println("Your surname must be at least 2 letters!");
            isValid = false;
        }
        for (char c : surname.toCharArray()) {
            if (Character.isDigit(c)) {
                System.out.println("Your surname cannot contain numbers!");
                isValid = false;
                break;
            }
        }

        // Password validations
        if (password.isEmpty()) {
            System.out.println("Password cannot be empty!");
            isValid = false;
        }
        if (password.length() < 8) {
            System.out.println("Password must be at least 8 characters long!");
            isValid = false;
        }
        boolean hasUpper = false, hasLower = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isLowerCase(c)) hasLower = true;
        }
        if (!hasUpper) {
            System.out.println("Password must contain at least one uppercase letter!");
            isValid = false;
        }
        if (!hasLower) {
            System.out.println("Password must contain at least one lowercase letter!");
            isValid = false;
        }

        // Final result
        if (isValid) {
            System.out.println("Login successful! Welcome, " + name + " " + surname);
        } else {
            System.out.println("Login failed. Please fix the errors above.");
        }
    }
}
