/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Admin;

import Model.Account;
import Model.User;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HP-DALAL
 */
public class UpdateAccountPageController implements Initializable {

    private Account oldAccount;

    @FXML
    private Button updateUserBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField userIdTF;
    @FXML
    private TextField creationDateTF;
    @FXML
    private TextField balanceTF;
    @FXML
    private TextField currencyTF;
    @FXML
    private TextField usernameTF;
    @FXML
    private TextField accountNumberTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.oldAccount = Controller.Admin.AccountsManagmentController.selectedAccountToUpdate;
        userIdTF.setText(Integer.toString(oldAccount.getUser_id()));
        accountNumberTF.setText(Integer.toString(oldAccount.getAccount_number()));
        currencyTF.setText(oldAccount.getCurrency());
        usernameTF.setText(oldAccount.getUsername());
        balanceTF.setText(Double.toString(oldAccount.getBalance()));
    }

    @FXML
    private void updateUser(ActionEvent event) throws ClassNotFoundException, SQLException {
        //get the new data from text field's and store it in new user object
        String username = usernameTF.getText();
        String currency = currencyTF.getText();
        int userId = Integer.parseInt(userIdTF.getText());
        int accountNumber = Integer.parseInt(accountNumberTF.getText());
        double balance = Double.parseDouble(balanceTF.getText());

        //make an new user object having this data
        Account newAccount = new Account(userId,accountNumber , username,currency, balance);

        //set the new user id the same as the old user
        newAccount.setId(oldAccount.getId());

        // update the user in database by update method
        newAccount.update();

        //close the update stage using our global stage var in UsersManagmentController and show an alert
        Controller.Admin.AccountsManagmentController.updateStage.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account updated");
        alert.setContentText("Account updated");
        alert.showAndWait();
    }

    @FXML
    private void cancelCreation(ActionEvent event) {
    }

}
