/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import controller.*;
import interfaces.ServerInterface;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.Remote;

/**
 *
 * @author Asmaa
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    ServerInterface server;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            Registry registry = LocateRegistry.createRegistry(2000);
            registry.rebind("chat", new ServerImpl());
            //registry = LocateRegistry.getRegistry(2000);
            //server = (ServerInterface) registry.lookup("chat");
            label.setText("Server Started");
        }
        catch (RemoteException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
