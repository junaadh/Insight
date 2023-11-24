package aps;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import db.BinHandler;
import forms.Survey;
import forms.SurveyCreator;
import helper.Javax;
import helper.Session;
import helper.Misc.prefix;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

public class MySurveyController implements Initializable {

  @FXML
  private TableView<Survey> table;

  @Override
  public void initialize(URL location, ResourceBundle resource) {
    BinHandler handler = new BinHandler();

    Map<String, Survey> map = BinHandler.loadSurvey();
    ArrayList<Survey> list = handler.valuesToList(map);
    Survey s = new Survey();
    SurveyCreator sc = handler.searchSurveyCreator(prefix.NID, Session.getInstance().getPerson().getNid());

    ArrayList<Survey> filtered = new ArrayList<>();
    for (Survey sy : list) {
      if (sy.getScId().equals(sc.getScId())) {
        filtered.add(sy);
      }
    }

    Javax.initializeTableColumn(s.getHeaders(), table);
    Javax.initializeTableRow(filtered, table);
  }

  @FXML
  private void goBack() throws IOException {
    App.setRoot("scDash");
  }
}
