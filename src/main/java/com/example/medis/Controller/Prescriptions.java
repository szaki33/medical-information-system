package com.example.medis.Controller;

import com.example.medis.Entity.Patient;
import com.example.medis.Entity.Prescription;
import com.example.medis.Entity.User;
import com.example.medis.Model.JavaPostgreSql;
import com.example.medis.ControllerBuffer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class Prescriptions implements Initializable {


    private Patient selectedPatient;
    private Prescription selectedPrescription;
    private User loggedInUser;
    private final JavaPostgreSql javaPostgreSql = new JavaPostgreSql();

    @FXML private TableView<Prescription> prescriptionsTable;
    @FXML private Label patientNamePrescriptions;
    @FXML private TableColumn <Prescription, String> title;
    @FXML private TableColumn <Prescription, String>drug;
    @FXML private TableColumn <Prescription, Date> expirationDate;
    @FXML private TableColumn <Prescription, String> prescribedBy;

    @FXML
    private void switchToPatientInfo(ActionEvent event) throws IOException {
        ControllerBuffer s = new ControllerBuffer();
        s.switchToPatientInfo(loggedInUser, selectedPatient, event);
    }

    @FXML
    private void switchToRecords(ActionEvent event) throws IOException {
        ControllerBuffer s = new ControllerBuffer();
        s.switchToRecords(loggedInUser, selectedPatient, event);
    }

    @FXML
    private void switchToAppointments(ActionEvent event) throws IOException {
        ControllerBuffer s = new ControllerBuffer();
        s.switchToAppointments(loggedInUser, selectedPatient, event);
    }

    @FXML
    private void switchToPrescriptions(ActionEvent event) throws IOException {
        ControllerBuffer s = new ControllerBuffer();
        s.switchToPrescriptions(loggedInUser, selectedPatient, event);
    }

    @FXML
    private void switchToPrescriptionEdit(ActionEvent event) throws IOException {
        ControllerBuffer s = new ControllerBuffer();
        s.switchToPrescriptionEdit(loggedInUser, selectedPatient, selectedPrescription, event);
    }

    @FXML
    private void switchToPrescriptionCreation(MouseEvent event) throws IOException {
        ControllerBuffer s = new ControllerBuffer();
        s.switchToPrescriptionCreation(loggedInUser, selectedPatient, event);
    }

    @FXML
    private void switchToPatients(ActionEvent event) throws IOException {
        ControllerBuffer s = new ControllerBuffer();
        s.switchToPatients(loggedInUser, event);
    }


    private void addButtonToTable() {
        TableColumn<Prescription, Void> details  = new TableColumn<>();

        Callback<TableColumn<Prescription, Void>, TableCell<Prescription, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Prescription, Void> call(final TableColumn<Prescription, Void> param) {
                return new TableCell<>() {

                    private final Button openButton = new Button("View");

                    {
                        openButton.setOnAction((ActionEvent event) -> {
                            selectedPrescription  = javaPostgreSql.getPrescriptionById(prescriptionsTable.getItems().get(getIndex()).getId());
                            try {
                                switchToPrescriptionEdit(event);
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

        prescriptionsTable.getColumns().add(details);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        drug.setCellValueFactory(new PropertyValueFactory<>("drug"));
        expirationDate.setCellValueFactory(new PropertyValueFactory<>("expirationDate"));
        prescribedBy.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        addButtonToTable();
    }

    public void initData(Patient patient, User user) {
        selectedPatient = patient;
        loggedInUser = user;
        patientNamePrescriptions.setText(selectedPatient.getFirstName() + " " + selectedPatient.getLastName() + "'s prescriptions");
        prescriptionsTable.setItems(javaPostgreSql.getAllNotDeletedPrescriptionsByEntityID("patient", selectedPatient.getId()));
    }
}