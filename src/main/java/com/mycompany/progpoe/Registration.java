package com.mycompany.progpoe;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

class Registration {
    String registeredUser;
    String registeredPassword;
    String registeredPhone;
    String firstName;
    String lastName;
    
    public boolean checkUserName(String username) {
        if (username.contains("_") && username.length() <= 5) {
            JOptionPane.showMessageDialog(null, "Username successfully captured.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.");
            return false;
        }
    }
    
    public boolean checkPasswordComplexity(String password) {
        if (password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_+=.-])(?=\\S+$).{8,}$")) {
            JOptionPane.showMessageDialog(null, "Password successfully captured.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            return false;
        }
    }
    
    public boolean checkCellPhoneNumber(String cellphone) {
        if (cellphone.matches("0[0-9]{9}")) {
            JOptionPane.showMessageDialog(null, "Cellphone number successfully captured.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Cellphone number incorrectly formatted or does not contain international code, please correct the number and try again.");
            return false;
        }
    }
   
    public void registerUser() {
        String userName = JOptionPane.showInputDialog("Enter your username");
        String userPassword = JOptionPane.showInputDialog("Enter your password");
        String userCellphone = JOptionPane.showInputDialog("Enter your cellphone number (10 digits, starts with 0):");
        
        if (checkUserName(userName) && checkPasswordComplexity(userPassword) && checkCellPhoneNumber(userCellphone)) {
            registeredUser = userName;
            registeredPassword = userPassword;
            registeredPhone = userCellphone;
            JOptionPane.showMessageDialog(null, "Registered successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Registration failed");
        }
    }
    
    public void loginUser() {
        if (registeredUser == null || registeredPassword == null || registeredPhone == null) {
            JOptionPane.showMessageDialog(null, "Please register first");
            return;
        }
        
        firstName = JOptionPane.showInputDialog("Please enter your first name");
        lastName = JOptionPane.showInputDialog("Please enter your last name");
        String userName = JOptionPane.showInputDialog("Enter your username to login");
        String userPassword = JOptionPane.showInputDialog("Enter your password to login");
        
        if (userName != null && userPassword != null && 
            userName.equals(registeredUser) && userPassword.equals(registeredPassword)) {
            JOptionPane.showMessageDialog(null, "Welcome " + firstName + " " + lastName + " it is great to see you again.");
            showMessagingMenu();
        } else {
            JOptionPane.showMessageDialog(null, "Username or password incorrect please try again.");
        }
    }
    
    private void showMessagingMenu() {
        JOptionPane.showMessageDialog(null, "Welcome " + firstName + " to Quickchat!", "Welcome", JOptionPane.INFORMATION_MESSAGE);

        List<Message> messages = new ArrayList<>();

        while (true) {
            String menuInput = JOptionPane.showInputDialog(
                "Main Menu: \n\n" +
                "1) Send Messages \n" +
                "2) Show Recently Sent Messages\n" +
                "3) Quit\n\n" +
                "Please enter your choice (1-3):"
            );

            if (menuInput == null) {
                break;
            }
            
            int menuChoice;
            try {
                menuChoice = Integer.parseInt(menuInput);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number (1-3).");
                continue;
            }
            
            if (menuChoice == 1) {
                int maxMessages = 0;
                while (true) {
                    try {
                        String input = JOptionPane.showInputDialog("How many messages do you want to enter?");
                        if (input == null) break;
                        maxMessages = Integer.parseInt(input);
                        if (maxMessages > 0) break;
                        else JOptionPane.showMessageDialog(null, "Enter a number greater than 0.");
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                    }
                }
                
                int totalMessagesSent = 0;
                
                for (int i = 1; i <= maxMessages; i++) {
                    String recipient = JOptionPane.showInputDialog("Enter recipient's cellphone number (e.g. +27123456789):");
                    if (recipient == null) break;
                    
                    String messageContent = JOptionPane.showInputDialog("Enter your message:");
                    if (messageContent == null) break;
                    
                    Message msg = new Message(i, recipient, messageContent);
                    
                    if (!msg.checkRecipientCell()) {
                        JOptionPane.showMessageDialog(null, "Invalid recipient cellphone number format.", "Error", JOptionPane.ERROR_MESSAGE);
                        i--;
                        continue;
                    }
                    
                    String action = msg.sentMessageOption();
                    
                    switch (action) {
                        case "Send Message":
                            JOptionPane.showMessageDialog(null, "Message sent:\n" + msg.printMessage(), "Sent", JOptionPane.INFORMATION_MESSAGE);
                            totalMessagesSent++;
                            break;
                        case "Discard Message":
                            JOptionPane.showMessageDialog(null, "Message disregarded.", "Discarded", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case "Store Message":
                            messages.add(msg);
                            JOptionPane.showMessageDialog(null, "Message stored successfully.", "Stored", JOptionPane.INFORMATION_MESSAGE);
                            totalMessagesSent++;
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "No action selected.", "Info", JOptionPane.INFORMATION_MESSAGE);
                            break;
                    }
                }
                
                JOptionPane.showMessageDialog(null, "Total messages sent/stored: " + totalMessagesSent, "Summary", JOptionPane.INFORMATION_MESSAGE);
                
            } else if (menuChoice == 2) {
                if (messages.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No messages stored yet.", "Recently Sent Messages", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    StringBuilder messageList = new StringBuilder("Recently Stored Messages:\n\n");
                    for (Message msg : messages) {
                        messageList.append(msg.printMessage()).append("\n\n");
                    }
                    JOptionPane.showMessageDialog(null, messageList.toString(), "Recently Sent Messages", JOptionPane.INFORMATION_MESSAGE);
                }
                
            } else if (menuChoice == 3) {
                JOptionPane.showMessageDialog(null, "Thank you!", "Goodbye", JOptionPane.INFORMATION_MESSAGE);
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a valid number (1-3).");
            }
        }
    }
}