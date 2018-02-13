/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import interfaces.ServerInterface;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asmaa
 */
public class RmiFactory {
    private static RmiFactory rmiFactory;
    private Object server;
    private Registry registry;
    
    private RmiFactory() {    
    }
    public Object connect(){
        try {
            if (server==null) {
                registry = LocateRegistry.getRegistry(2000);
                server =  registry.lookup("chat");
            }
            
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(RmiFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return server;
    }
    public static RmiFactory getRmiInstance(){
        if(rmiFactory==null)
            rmiFactory=new RmiFactory();
        return rmiFactory;
            
    }
    
    
    
    
}
