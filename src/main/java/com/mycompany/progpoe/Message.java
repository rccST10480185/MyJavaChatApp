package com.mycompany.progpoe;

import javax.swing.JOptionPane;
import java.util.Random;

public class Message {
    private String messageid;
    private int messageNumber;
    private String recipient;
    private String messageContent;
    private String messageHash;
    
    public Message(int messageNumber, String recipient, String messageContent) {
        this.messageid = generateMessageID();
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageContent = messageContent;
        this.messageHash = createMessageHash();
    }

    private String generateMessageID() {
        Random rand = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append(rand.nextInt(10));
        }
        return id.toString();
    }
    
    public boolean checkMessageID() {
        return messageid.length() == 10;
    }
    
    public boolean checkRecipientCell() {
        if (this.recipient == null) return false;
        this.recipient = this.recipient.trim();
        return this.recipient.matches("^\\+27\\d{9}$");
    }
    
    public String createMessageHash() {
        if (messageContent == null || messageContent.trim().isEmpty()) {
            return (messageid.substring(0,2) + ":" + messageNumber + "::").toUpperCase();
        }
        
        String[] words = messageContent.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        return (messageid.substring(0,2) + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }
    
    public String sentMessageOption() {
        String[] options = {"Send Message", "Discard Message", "Store Message"};
        int choice = JOptionPane.showOptionDialog(
            null, 
            "Choose an option for the message:", 
            "Message Action", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.INFORMATION_MESSAGE, 
            null, 
            options, 
            options[0]
        );
        
        if (choice >= 0 && choice < options.length) {
            return options[choice];
        } else {
            return "Discard Message";
        }
    }
   
    public String printMessage() {
        return "Message ID: " + messageid + "\n" + 
               "Message Hash: " + messageHash + "\n" + 
               "Recipient: " + recipient + "\n" + 
               "Message: " + messageContent;
    }
    
    public String getMessageid() {
        return messageid;
    }
    
    public String getMessageHash() {
        return messageHash;
    }
    
    public String getRecipient() {
        return recipient;
    }
    
    public String getMessageContent() {
        return messageContent;
    }
    
    public int getMessageNumber() {
        return messageNumber;
    }
}