/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.FriendTableOperation;
import interfaces.FriendRequestInterface;

/**
 *
 * @author Asmaa
 */
public class FriendRequestImpl implements FriendRequestInterface{
    @Override
    public boolean sendRequest(long id,String friendEmail){
        return new FriendTableOperation().addFriendHandler(id, friendEmail);
    }
    
}
