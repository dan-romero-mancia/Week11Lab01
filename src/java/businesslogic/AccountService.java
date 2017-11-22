/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import dataaccess.NotesDBException;
import dataaccess.UserDB;
import domainmodel.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author awarsyle
 */
public class AccountService {
    
    public User checkLogin(String username, String password, String path) {
        User user;
        
        UserDB userDB = new UserDB();
        try {
            user = userDB.getUser(username);
            
            if (user.getPassword().equals(password)) {
                Logger.getLogger(AccountService.class.getName())
                        .log(Level.INFO, 
                        "A user logged in: {0}",
                        username);
                try {
                    //WebMailService.sendMail(user.getEmail(), "NotesKeepr Logged in", "<h2>Congrats!  You just loggedin successfully.</h2>" , true);
                    
                    HashMap<String, String> contents = new HashMap<>();
                    
                    contents.put("firstname", user.getFirstname());
                    contents.put("date", (new java.util.Date()).toString());
                    
                    String template = path + "/emailtemplates/login.html";
                    WebMailService.sendMail(user.getEmail(), "NotesKeepr Login", template, contents);
                    
                } catch (Exception ex) {
                    Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
                }
                return user;
            }
            
        } catch (NotesDBException ex) {
        }
        
        return null;
    }
    
    public boolean forgotPassword(HttpServletRequest request, String email) {
        UserService userService = new UserService();
        try {
            User user = userService.getByEmail(email);
            if (user != null) {
                HashMap<String, String> contents = new HashMap<>();
                contents.put("firstname", user.getFirstname());
                contents.put("lastname", user.getLastname());
                contents.put("username", user.getUsername());
                contents.put("password", user.getPassword());
            
                String template = request.getServletContext().getRealPath("/WEB-INF/emailtemplates/forgot.html");
                WebMailService.sendMail(user.getEmail(), "NotesKeepr Forgot Password", template, contents);
                return true;
            }

           return false; 
        } catch (Exception ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
