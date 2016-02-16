package theironyard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    ObservableList<Contact> contacts = FXCollections.observableArrayList();

    @FXML
    ListView list;

    @FXML
    TextField nameField;

    @FXML
    TextField phoneField;

    @FXML
    TextField emailField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    list.setItems(contacts);
    }

    public void addContact() {
        if (!nameField.getText().equals("") && !phoneField.getText().equals("") && !emailField.getText().equals("")) {
            contacts.add(new Contact(nameField.getText(), phoneField.getText(), emailField.getText()));
        }
            nameField.setText("");
            phoneField.setText("");
            emailField.setText("");

    }
    public void removeContact() {
        Contact item = (Contact) list.getSelectionModel().getSelectedItem();
        contacts.remove(item);

    }




}
