package com.example.medis.UserMode;

import com.example.medis.Entities.Patient;
import com.example.medis.JavaPostgreSql;
import com.example.medis.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {

    public TableView<Patient> getPatientsTable() {
        return patientsTable;
    }

    @FXML private TableView<Patient> patientsTable;
    @FXML private TableColumn<Patient, String> name;
    @FXML private TableColumn<Patient, String> surname;
    @FXML private TableColumn<Patient, Long> birth_id;
    @FXML private TableColumn<Patient, LocalDateTime> last_visit;
    @FXML private TableColumn<Patient, LocalDateTime> next_visit;

    private Patient selectedPatient;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        name.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        birth_id.setCellValueFactory(new PropertyValueFactory<>("birth_id"));
        last_visit.setCellValueFactory(new PropertyValueFactory<>("last_visit"));
        next_visit.setCellValueFactory(new PropertyValueFactory<>("next_visit"));
        addButtonToTable();
        patientsTable.setItems(JavaPostgreSql.getAllPatients());

    }

    private void addButtonToTable() {
        TableColumn<Patient, Void> details  = new TableColumn<>();
        details.setText("Details");

        Callback<TableColumn<Patient, Void>, TableCell<Patient, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Patient, Void> call(final TableColumn<Patient, Void> param) {
                return new TableCell<>() {

                    private final Button openButton = new Button("Open");
                    {
                        openButton.setOnAction((ActionEvent event) -> {

                            selectedPatient = JavaPostgreSql.getPatient(getPatientsTable().getItems().get(getIndex()).getId());

                            try {
                                showPatientInfo();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(openButton);
                        }
                    }
                };
            }
        };

        details.setCellFactory(cellFactory);

        patientsTable.getColumns().add(details);

    }

    @FXML
    private void userLogOut(ActionEvent event) throws IOException {
        SceneController s = new SceneController();
        s.switchTo("login.fxml",event);

    }

    @FXML
    private void showPatients(ActionEvent event) throws IOException {
        SceneController s = new SceneController();
        s.switchTo("user_mode/dashboard.fxml",event);
    }

    @FXML
    private void addPatient()  {
        SceneController s = new SceneController();
        try {
            s.newWindow("user_mode/new_patient.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showPatientInfo() throws IOException {
        SceneController s = new SceneController();
        s.newWindowWithPatient(selectedPatient, "user_mode/patient_info.fxml");

    }





}