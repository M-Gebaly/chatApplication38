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
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
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
    
    @FXML JFXListView<User> frindListView;
    
    @FXML
    private TabPane tabPane;
    
    @FXML
    private Circle circleImage;
    
    
    Map<String, Tab> tabsOpened = new HashMap<>();
    Map<String, FXMLChatContentController> tabsControllers = new HashMap<>();
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
                circleImage.setStroke(Color.SEAGREEN);
                Image profileImage = new Image("resources/logo.png");
                
                circleImage.setFill(new ImagePattern(profileImage));
                circleImage.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
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
        //--- Action on click on any frind---------//
        frindListView.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                String userName = frindListView.getSelectionModel().getSelectedItem().getName();
                System.out.println(userName);
                cellClickAction(userName);
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
    
    
    //------------------------------- create tabs ----------------------------//
     void cellClickAction(String friendName) {
        if (!tabsOpened.containsKey(friendName)) {
            try {

                
                Tab newTab = new Tab();

                newTab.setId(friendName);
                newTab.setText(friendName);

                newTab.setClosable(true);
                newTab.setOnCloseRequest(new EventHandler<Event>() {
                    @Override
                    public void handle(Event event) {
                        
                        tabsOpened.remove(newTab.getId());
                        tabsControllers.remove(newTab.getId());
                    }
                });

                tabPane.getTabs().add(newTab);
                tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXMLChatContent.fxml"));
                FXMLChatContentController chatContentController = new FXMLChatContentController(); //receiver
                loader.setController(chatContentController);

                tabsOpened.put(friendName, newTab);
                tabsControllers.put(friendName, chatContentController);

                newTab.setContent(loader.load());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            tabPane.getSelectionModel().select(tabsOpened.get(friendName));
        }
    }

    public void setUser(User user) {
        this.user = user;
    }    
    
}
