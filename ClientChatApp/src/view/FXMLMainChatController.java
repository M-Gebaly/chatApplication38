/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.sun.deploy.security.ruleset.RunRule;
import controller.RmiFactory;
import interfaces.ServerInterface;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import model.*;

/**
 * FXML Controller class
 *
 * @author M.Gebaly
 */
public class FXMLMainChatController implements Initializable {

    User user = null;
    ServerInterface server;
    
    ArrayList<User> frindList = new ArrayList<>();
    ObservableList<User> oFrindList;
    
    @FXML
    private Label userNameLable;
    
    @FXML JFXListView frindListView;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         server = (ServerInterface) RmiFactory.getRmiInstance().connect();
         
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                userNameLable.setText(user.getName());
                try {
                    frindList = server.getUserInfoInterface().getFriendList(user.getId());
                } catch (RemoteException ex) {
                    Logger.getLogger(FXMLMainChatController.class.getName()).log(Level.SEVERE, null, ex);
                }
                oFrindList = FXCollections.observableArrayList(frindList);
                frindListView.setItems( oFrindList);
                loadFrindList();
            }
        });
    }
    
    
    void loadFrindList() {
        frindListView.setCellFactory(listView -> new JFXListCell<User>() {

            @Override
            public void updateItem(User friend, boolean empty) {
                super.updateItem(friend, empty);
                if (empty || friend == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setGraphic(loadCell(friend));
                }
            }

        });
    }
    
    Node loadCell(User friend) {
        ImageView imageView = new ImageView();
        //ImageView imageViewStatus = new ImageView();

        FlowPane flow = new FlowPane();
        flow.setHgap(4);
        flow.setPrefWidth(1);
        
        Label friendName = new Label();
        friendName.setText(friend.getName());
        
        Image image = new Image("resources/logo.png", true);
        imageView.setImage(image);
        imageView.setFitWidth(35);
        imageView.setFitHeight(35);
        
        flow.getChildren().addAll(imageView,friendName);
        return flow;
    }

    public void setUser(User user) {
        this.user = user;
    }    
    
}
