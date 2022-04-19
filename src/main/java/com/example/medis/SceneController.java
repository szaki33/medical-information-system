package com.example.medis;

import java.io.IOException;
import java.util.Objects;

import com.example.medis.Entities.*;
import com.example.medis.UserMode.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SceneController extends NewPatient {

    private Parent root;

    public void switchTo(String fxml, ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Medis");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToDashboard(User loggedInUser, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("user_mode/dashboard.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);

        Dashboard controller = loader.getController();
        controller.initData(loggedInUser);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void newWindowWithPatient(Patient patient) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("user_mode/patient_info.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        PatientInfo controller = loader.getController();
        controller.initData(patient);

        stage.toFront();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    // Patient info

    public void switchToPatientInfo(Patient patient, ActionEvent event) throws IOException {
        FXMLLoader loader  = new FXMLLoader(getClass().getResource("user_mode/patient_info.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Medis");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);

        PatientInfo controller = loader.getController();
        controller.initData(patient);

        stage.show();
    }

    public void switchToPatientInfoEdit(Patient patient, ActionEvent event) throws IOException {
        FXMLLoader loader  = new FXMLLoader(getClass().getResource("user_mode/patient_info_edit.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Medis");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);

        PatientInfoEdit controller = loader.getController();
        controller.initData(patient);

        stage.show();
    }

    public void switchToPatientCreation(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("user_mode/new_patient.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Medis");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Records

    public void switchToRecords(Patient patient, ActionEvent event) throws IOException {
        FXMLLoader loader  = new FXMLLoader(getClass().getResource("user_mode/records.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Medis");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);

        Records controller = loader.getController();
        controller.initData(patient);

        stage.show();
    }

    public void switchToRecordDetailed(Patient patient, Record record, ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("user_mode/record_detailed.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);

        RecordDetailed controller = loader.getController();
        controller.initData(patient, record);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void switchToRecordEdit(Patient patient, Record record, ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("user_mode/record_edit.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);

        RecordEdit controller = loader.getController();
        controller.initData(patient, record);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void switchToRecordCreation(Patient patient, MouseEvent event) throws IOException {
        FXMLLoader loader  = new FXMLLoader(getClass().getResource("user_mode/new_record.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Medis");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);

        NewRecord controller = loader.getController();
        controller.initData(patient);

        stage.show();
    }

    // Appointments

    public void switchToAppointments(Patient patient, ActionEvent event) throws IOException {
        FXMLLoader loader  = new FXMLLoader(getClass().getResource("user_mode/appointments.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Medis");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);

        Appointments controller = loader.getController();
        controller.initData(patient);

        stage.show();
    }

    public void switchToAppointmentEdit(Patient patient, Appointment appointment, ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("user_mode/appointment_edit.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);

        AppointmentEdit controller = loader.getController();
        controller.initData(patient, appointment);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void switchToAppointmentCreation(Patient patient, MouseEvent event) throws IOException {
        FXMLLoader loader  = new FXMLLoader(getClass().getResource("user_mode/new_appointment.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Medis");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);

        NewAppointment controller = loader.getController();
        controller.initData(patient);

        stage.show();
    }

    // Prescriptions

    public void switchToPrescriptions(Patient patient, ActionEvent event) throws IOException {
        FXMLLoader loader  = new FXMLLoader(getClass().getResource("user_mode/prescriptions.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Medis");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);

        Prescriptions controller = loader.getController();
        controller.initData(patient);

        stage.show();
    }

    public void switchToPrescriptionEdit(Patient patient, Prescription prescription, ActionEvent event) throws IOException {
        FXMLLoader loader  = new FXMLLoader(getClass().getResource("user_mode/prescription_edit.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Medis");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);

        PrescriptionEdit controller = loader.getController();
        controller.initData(patient, prescription);

        stage.show();
    }

    public void switchToPrescriptionCreation(Patient patient, MouseEvent event) throws IOException {
        FXMLLoader loader  = new FXMLLoader(getClass().getResource("user_mode/new_prescription.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Medis");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);

        NewPrescription controller = loader.getController();
        controller.initData(patient);

        stage.show();
    }
}