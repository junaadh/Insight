package aps;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;

import db.BinHandler;
import forms.Person;
import forms.SurveyCreator;
import helper.Javax;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

public class ScListController implements Initializable {

  @FXML
  private TableView<SurveyCreator> table;

  BinHandler handler = new BinHandler();
  Map<String, SurveyCreator> map = BinHandler.loadSurveyCreator();
  ArrayList<SurveyCreator> list = handler.valuesToList(map);

  @Override
  public void initialize(URL location, ResourceBundle resource) {
    Person p = new Person();
    String[] headers = Arrays.copyOf(p.getHeaders(), p.getHeaders().length + 2);
    headers[p.getHeaders().length - 2] = "scId";
    headers[p.getHeaders().length - 1] = "scDept";

    Javax.initializeTableColumn(headers, table);
    Javax.initializeTableRow(list, table);
  }

  @FXML
  private void goBack() throws IOException {
    App.setRoot("adminDash");
  }

}
