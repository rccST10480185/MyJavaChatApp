package com.mycompany.progpoe;

public class Login {
    public String name;
    public String surname;
    public String createUsername;
    public String createPassword;
    public String loginUsername;
    public String loginPassword;
    public String cellphoneNumber;
    
    public boolean checkUsername() {
        return createUsername.contains("_") && createUsername.length() <= 5;
    }
       
    public boolean checkPasswordComplexity() {
        if (createPassword.length() < 8)
            return false;
        
        boolean hasCapitalLetter = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;
        
        for (int i = 0; i < createPassword.length(); i++) {
            char ch = createPassword.charAt(i);
            if (Character.isUpperCase(ch))
                hasCapitalLetter = true;
            else if (Character.isDigit(ch))
                hasDigit = true;
            if (!Character.isLetterOrDigit(ch))
                hasSpecialChar = true;
        }
        
        return hasCapitalLetter && hasDigit && hasSpecialChar;
    }
    
    public boolean checkCellPhoneNumber() {
        return cellphoneNumber.matches("^0[0-9]{9}$");
    }
    
    public String registerUser() {
        if (!checkUsername()) {
            return "The username is incorrectly formatted.";
        }
        if (!checkPasswordComplexity()) {
            return "The password does not meet the complexity requirements";
        }
        if (!checkCellPhoneNumber()) {
            return "The cellphone number is incorrectly formatted";
        }
        
        return "The conditions have been met, and the user has been registered successfully.";
    }
    
    public boolean loginUser(String loginUsername, String loginPassword) {
        return this.createUsername.equals(loginUsername) && this.createPassword.equals(loginPassword);
    }
    
    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + name + " " + surname + ", Welcome back.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}