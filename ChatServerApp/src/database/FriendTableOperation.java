/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author Asmaa
 */
public class FriendTableOperation {
    public ArrayList<User> friendListHandler(long id){
        ArrayList<User> usersList = new ArrayList<>();
        String sql = "SELECT * from "+
                DatabaseTables.UserTable.tableName
                +" where "+DatabaseTables.UserTable.idColumn
                +" in (select "+DatabaseTables.FriendsTable.friendIDColumn+" from "+DatabaseTables.FriendsTable.tableName
                +" where addfriend.USERID = "+id+" and addfriend.REQUESTFLAG != 0)";
        ResultSet resultSet= DatabaseHandler.getInstance().select(sql);
        try {
            while (resultSet.next()) {
                usersList.add(
                        new User(resultSet.getLong(DatabaseTables.UserTable.idColumn),
                                resultSet.getString(DatabaseTables.UserTable.nameColumn),
                                resultSet.getString(DatabaseTables.UserTable.emailColumn),
                                resultSet.getString(DatabaseTables.UserTable.passwordColumn),
                                resultSet.getString(DatabaseTables.UserTable.genderColumn),
                                resultSet.getString(DatabaseTables.UserTable.statusColumn),
                                resultSet.getString(DatabaseTables.UserTable.statusFlagColumn)
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(FriendTableOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usersList;
    }
 
    public boolean addFriendHandler(long id,String friendEmail){
        boolean result=false;
        try {
            String checkUserQuery="SELECT id from users where lower(email) = lower('"+friendEmail+"')";
            ResultSet resultSet=DatabaseHandler.getInstance().select(checkUserQuery);
            if(resultSet.next()){
                long friendID=resultSet.getLong(DatabaseTables.UserTable.idColumn);
                String checkFriendQuery = "select "+DatabaseTables.FriendsTable.friendIDColumn
                         +" from "+DatabaseTables.FriendsTable.tableName
                         +" where (addfriend.USERID = "+id+" and addfriend.REQUESTFLAG != 0)";
                resultSet=DatabaseHandler.getInstance().select(checkFriendQuery);
                if(resultSet.next()){
                    result=false;
                }
                else{
                    String query="INSERT INTO "+DatabaseTables.FriendsTable.tableName+" values ("+id+","+friendID+","+"0)";
                    DatabaseHandler.getInstance().insert(query);
                    result=true;
                }
     
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(FriendTableOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
        
    }
    
}
