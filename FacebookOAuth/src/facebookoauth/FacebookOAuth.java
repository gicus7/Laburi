/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facebookoauth;

import static facebookoauth.Alignment.centerTheWindow;

/**
 * @author olegb
 */
public class FacebookOAuth {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AuthForm auth = new AuthForm();
        auth.setVisible(true);
        centerTheWindow(auth);
    }

}
