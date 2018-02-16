/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import sun.security.jca.GetInstance;


/**
 *
 * @author Asmaa
 */
public interface ServerInterface extends Remote{
    
    public void register(ClientInterface client)throws RemoteException;
    public void unregister(ClientInterface client)throws RemoteException;
    
    public AccountAccessOperationsInterface getAccessOperationsInstance()throws RemoteException;
    public UserInfoInterface getUserInfoInterface()throws RemoteException;
    public FriendRequestInterface getFriendRequestInstance() throws RemoteException;
}
