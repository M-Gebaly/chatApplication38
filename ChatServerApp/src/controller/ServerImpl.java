/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import interfaces.AccountAccessOperationsInterface;
import interfaces.ClientInterface;
import interfaces.UserInfoInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author Asmaa
 */
public class ServerImpl extends UnicastRemoteObject implements interfaces.ServerInterface{
    
    public static ArrayList<ClientInterface> clients = new ArrayList<>();

    public ServerImpl() throws RemoteException{
    }
    
    @Override
    public AccountAccessOperationsInterface getAccessOperationsInstance() throws RemoteException{
        return new FactoryImpl().getAAOInstance();
    }
     @Override
    public UserInfoInterface getUserInfoInterface() throws RemoteException{
        return new FactoryImpl().getUserInfoInstance();
    }
    
    
    @Override
    public void register(ClientInterface client) throws RemoteException {
        System.out.println("from register");
        clients.add(client);
    }

    @Override
    public void unregister(ClientInterface client) throws RemoteException {
        System.out.println("from unregister");
        clients.remove(client);
    }

  
    
}
