package aps;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;

import db.BinHandler;
import forms.Person;
import forms.User;
import helper.Javax;
import helper.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

public class UserListController implements Initializable {

  @FXML
  private TableView<User> table;

  BinHandler handler = new BinHandler();
  Map<String, User> map = BinHandler.loadUser();
  ArrayList<User> list = handler.valuesToList(map);

  @Override
  public void initialize(URL location, ResourceBundle resource) {
    Person u = new Person();
    String[] headers = Arrays.copyOf(u.getHeaders(), u.getHeaders().length + 1);
    headers[u.getHeaders().length - 1] = "userId";

    Javax.initializeTableColumn(headers, table);
    Javax.initializeTableRow(list, table);

  }

  @FXML
  private void goBack() throws IOException {
    boolean privilage = Session.getInstance().getPerson().getIsAdmin();

    if (privilage) {
      App.setRoot("adminDash");
    } else {
      App.setRoot("scDash");
    }
  }
}
