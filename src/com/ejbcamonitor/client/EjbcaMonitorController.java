/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejbcamonitor.client;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import restfx.web.GetQuery;
import restfx.web.Query;
import restfx.web.QueryListener;
//import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author sie1222
 */
public class EjbcaMonitorController implements Initializable {

    @FXML
    private TableView<Map<String, Object>> resultsTableView;
    
    @FXML
    private Button refreshButton;
    
    private GetQuery getQuery = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        refreshButton.setText("Refresh");

        loadTableContent();
        
    }
    
    /**
   * Search action handler.
   *
   * @param event
   */
  @FXML
  protected void handleRefreshAction(ActionEvent event) {
      loadTableContent();
  }

    private void loadTableContent() {
        refreshButton.setDisable(true);
        refreshButton.setText("Loading...");
        
        getQuery = new GetQuery("10.96.170.190", 7080, "/ejbca-monitor/check", false);
        System.out.println(getQuery.getLocation());

        getQuery.execute(new QueryListener<Object>() {

            @Override
            @SuppressWarnings("unchecked")
            public void queryExecuted(Query<Object> task) {
                if (task == getQuery) {
                    
                    if (task.isCancelled()) {
                        System.out.println("cancelled");
                    } else {
                        Throwable exception = task.getException();
                        if (exception == null) {
                            System.out.println(task.getValue());            
                            List<Object> results = (List<Object>) task.getValue();

                            // Update the table data
                            ObservableList<?> items = FXCollections.observableList(results);
                            resultsTableView.setItems((ObservableList<Map<String, Object>>) items);

                            if (results.size() > 0) {
                                //resultsTableView.getSelectionModel().select(0);
                                resultsTableView.requestFocus();
                            } else {
                            }
                        } else {
                            System.out.println("ERROR: " + exception);
                        }
                    }

                    getQuery = null;

                }
            }
        });
        
        refreshButton.setDisable(false);
        refreshButton.setText("Refresh");
    }
}
