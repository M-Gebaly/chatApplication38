/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import model.User;

/**
 *
 * @author Asmaa
 */
public interface ClientInterface extends Remote{
    public void recieve(String message)throws RemoteException;
    public User getUser()throws RemoteException;

    
}
