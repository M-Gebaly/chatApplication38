/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.rmi.RemoteException;

/**
 *
 * @author Asmaa
 */
public interface FriendRequestInterface {
    public boolean sendRequest(long id,String friendEmail) throws RemoteException;
    
}
