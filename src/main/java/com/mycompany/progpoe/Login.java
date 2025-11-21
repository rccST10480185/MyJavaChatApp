/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progpoe;

/**
 *
 * @author RC_Student_Lab
 */
public class Login {
    
    //Declare variables and their data types
    public String name;
    public String surname;
    public String createUsername;
    public String createPassword;
    public String loginUsername;
    public String loginPassword;
    public String cellphoneNumber;
    
    public boolean checkUsername() { // Fixed method name (was checkUsernameName)
        return createUsername != null && createUsername.contains("_") && createUsername.length() <= 5;
    }
       
    public boolean checkPasswordComplexity() {
        // Check if password is null or too short
        if (createPassword == null || createPassword.length() < 8)
            return false;
        
        boolean hasCapitalLetter = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;
        
        for (int i = 0; i < createPassword.length(); i++) {
            char ch = createPassword.charAt(i); // Fixed variable name (was Ch/ch inconsistency)
            if (Character.isUpperCase(ch))
                hasCapitalLetter = true;
            else if (Character.isDigit(ch)) // Fixed logic - check for digit specifically
                hasDigit = true;
            
            // Check for special character (not letter or digit)
            if (!Character.isLetterOrDigit(ch))
                hasSpecialChar = true;
        }
        
        return hasCapitalLetter && hasDigit && hasSpecialChar;
    }
    
    public boolean checkCellPhoneNumber() { // Fixed method name (was CheckCellPhoneNumber)
        // Check if cellphoneNumber is null
        if (cellphoneNumber == null) return false;
        
        // Regular expression for South African cellphone numbers
        // Format: +27 followed by 9 digits, or 0 followed by 9 digits
        String regex = "^(\\+27|0)[0-9]{9}$";
        return cellphoneNumber.matches(regex);
    }
    
    public String registerUser() {
        // Check if required fields are null
        if (createUsername == null || createPassword == null) {
            return "Username or password not provided.";
        }
        
        // if statements used to register username and password created
        if (!checkUsername()) {
            return "The username is incorrectly formatted.";
        }
        if (!checkPasswordComplexity()) {
            return "The password does not meet the complexity requirements";
        }
        
        return "The two above conditions have been met, "
                + "and the user has been registered successfully.";
    }
    
    public boolean loginUser(String loginUsername, String loginPassword) { // Fixed parameter name (was LoginPassword)
        // Check for null values before comparison
        if (this.createUsername == null || this.createPassword == null || 
            loginUsername == null || loginPassword == null) {
            return false;
        }
        return this.createUsername.equals(loginUsername) && this.createPassword.equals(loginPassword);
    }
    
    public String returnLoginStatus(boolean loginSuccess) {
        // Check if name and surname are available
        String fullName = (name != null ? name : "") + " " + (surname != null ? surname : "");
        fullName = fullName.trim();
        
        //if statement to login user when details match registration details
        if (loginSuccess) {
            return "Welcome" + (fullName.isEmpty() ? "" : " " + fullName) + ", Welcome back.";
        //if details don't match, tell user to input the correct details
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}