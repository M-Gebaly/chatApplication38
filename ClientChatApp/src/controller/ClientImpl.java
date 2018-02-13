/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import interfaces.ClientInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import model.User;

/**
 *
 * @author Asmaa
 */
public class ClientImpl extends UnicastRemoteObject implements ClientInterface{
    User user;

    public ClientImpl() throws RemoteException{
    }
    
    public ClientImpl(User user) throws RemoteException {
        this.user = user;
    }

    @Override
    public User getUser() throws RemoteException{
        return user;
    }

    public void setUser(User user) throws RemoteException{
        this.user = user;
    }
    
    @Override
    public void recieve(String message) throws RemoteException {
        System.out.println("from recieve");
        //controller.render(userData);
        System.out.println(message);
    }    
}

