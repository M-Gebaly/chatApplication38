/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import controller.RmiFactory;
import interfaces.ServerInterface;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;

/**
 * FXML Controller class
 *
 * @author M.Gebaly
 */
public class FXMLRegistrationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    ServerInterface server;
    @FXML
    private AnchorPane signupPane;
    
    private JFXTextField rname;

    @FXML
    private JFXPasswordField rpassword;

    @FXML
    private JFXTextField remail;
    
    @FXML
    private JFXPasswordField opassword;

    @FXML
    private JFXRadioButton male;

    @FXML
    private JFXRadioButton female;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        server = (ServerInterface) RmiFactory.getRmiInstance().connect();
    }


 @FXML
    private void backLogin(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXMLLogin.fxml"));
            Parent root = loader.load();
            FXMLLoginController controller = loader.getController();
            //controller.setText(nameTxtField.getText());

            Scene scene = new Scene(root);
            
            Stage stage = (Stage) signupPane.getScene().getWindow();
            stage.setTitle("Login Page");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
//    @FXML
//    private  void passwordReleased(ActionEvent event){
//        String password_1 = rpassword.getText();
//        String password_2 = opassword.getText();
//        
//        if(!password_2.equals(password_1)){
//            opassword.setStyle("-jfx-focus-color: #ee2e58;-fx-prompt-text-fill: #ee2e58;\n" +
//"    -fx-text-fill: #fff;");
//            opassword.setPromptText("Password not matched");
//        }
//    }
    
    @FXML
    private void signupAction(ActionEvent event){
        String name = rname.getText();
        String Password = rpassword.getText();
        String password_2 = opassword.getText();
        String email = remail.getText();

        boolean isMale = male.isSelected();
        boolean isFemale = female.isSelected();
        
        
        if (name.equals("") || Password.equals("") || email.equals("") || password_2.equals("")) {

            if (name.equals("")) {
                rname.setStyle("-jfx-focus-color: #ee2e58;-fx-prompt-text-fill: #ee2e58;\n" +
"    -fx-text-fill: #fff;");
            }
            if (Password.equals("")) {
                rpassword.setStyle("-jfx-focus-color: #ee2e58;-fx-prompt-text-fill: #ee2e58;\n" +
"    -fx-text-fill: #fff;");
                rpassword.setPromptText("********");
            }
            if (email.equals("")) {
                remail.setStyle("-jfx-focus-color: #ee2e58;-fx-prompt-text-fill: #ee2e58;\n" +
"    -fx-text-fill: #fff;");
            }
            remail.setPromptText("abc@abc.com");

        } else if (!password_2.equals(Password)) {
            opassword.setStyle("-jfx-focus-color: #ee2e58;-fx-prompt-text-fill: #ee2e58;\n" +
"    -fx-text-fill: #fff;");
            opassword.setPromptText("Password not matched");
        } else {
            User user = new User();
            user.setName(name);
            user.setPassword(Password);
            user.setEmail(email);
            if (isMale) {
                user.setGender("male");
            }
            if (isFemale) {
                user.setGender("female");
            }
            boolean result;
            try {
                result = server.getAccessOperationsInstance().signUp(user);
                if (!result) {
                    
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLLogin.fxml"));
                        Parent root = loader.load();
                        FXMLLoginController controller = loader.getController();
                        //controller.setEmail(remail.getText());
                        

                        Scene scene = new Scene(root);

                        Stage stage = (Stage) signupPane.getScene().getWindow();
                        stage.setTitle("Login Page");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(FXMLRegistrationController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
}
