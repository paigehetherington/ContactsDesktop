package theironyard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Scanner;

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
        try {
            ArrayList<HashMap<String, String>> temp = loadJson();
            for (HashMap <String, String> contact: temp) {
                contacts.add(new Contact (contact.get("name"), contact.get("phone"), contact.get("email")));

            }
        } catch (FileNotFoundException e) {

        }
        list.setItems(contacts);
    }

    public void addContact() throws IOException {
        if (!nameField.getText().equals("") && !phoneField.getText().equals("") && !emailField.getText().equals("")) {
            contacts.add(new Contact(nameField.getText(), phoneField.getText(), emailField.getText()));
            saveJson(contacts);
        }
            nameField.setText("");
            phoneField.setText("");
            emailField.setText("");


    }
    public void removeContact() throws IOException {
        Contact item = (Contact) list.getSelectionModel().getSelectedItem();
        contacts.remove(item);
        saveJson(contacts);
    }

    public static void saveJson(ObservableList<Contact> contacts) throws IOException { //create save method. start with empty and see what need to pass in
        JsonSerializer s = new JsonSerializer(); //new object
        String json = s.include("*").serialize(contacts); //serialize into string -- (include("*") includes array list)

        File f = new File("ContactDesktop.json");
        FileWriter fw = new FileWriter(f);
        fw.write(json);
        fw.close();
    }

    public static ArrayList loadJson() throws FileNotFoundException {
        File f = new File("ContactDesktop.json");
        Scanner s = new Scanner(f);
        s.useDelimiter("\\Z");
        String contents = s.next();

        JsonParser p = new JsonParser();
        return p.parse(contents, ArrayList.class); //method returns  object


    }





}
