/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streamclient;

/**
 *
 * @author olegb
 */
public class StreamClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClientForm clientForm = new ClientForm();
        Alignment.centerTheWindow(clientForm);
        clientForm.setVisible(true);
    }
    
}
