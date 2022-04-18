package com.example.medis.UserMode;

import com.example.medis.Entities.Appointment;
import com.example.medis.Entities.Patient;
import com.example.medis.JavaPostgreSql;
import com.example.medis.SceneController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PatientAppointmentEdit implements Initializable {

    Patient selectedPatient;
    Appointment selectedAppointment;

    @FXML private TextField title_data;
    @FXML private DatePicker start_ymd_data;
    @FXML private ComboBox<String> start_h_data;
    @FXML private ComboBox<String> start_min_data;
    @FXML private DatePicker end_ymd_data;
    @FXML private ComboBox<String> end_h_data;
    @FXML private ComboBox<String> end_min_data;
    @FXML private TextField description_data;
    @FXML private ComboBox <String> doctor_data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        doctor_data.setItems(FXCollections.observableArrayList(JavaPostgreSql.getUsersByPosition("doctor")));
        start_h_data.setItems(FXCollections.observableArrayList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17","18","19","20","21","22","23"));
        start_min_data.setItems(FXCollections.observableArrayList("00", "15", "30", "45"));
        end_h_data.setItems(FXCollections.observableArrayList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17","18","19","20","21","22","23"));
        end_min_data.setItems(FXCollections.observableArrayList("00", "15", "30", "45"));
    }

    @FXML
    public void goBackToAppointments(ActionEvent event) throws IOException {
        SceneController s = new SceneController();
        s.switchToAppointments(selectedPatient, event);
    }

    public void initData(Patient patient, Appointment appointment) {
        selectedPatient = patient;
        selectedAppointment = appointment;
        title_data.setText(selectedAppointment.getTitle());
        description_data.setText(selectedAppointment.getDescription());
        doctor_data.getSelectionModel().select(JavaPostgreSql.getUser(selectedAppointment.getDoctor_id()).getFullname());
        start_ymd_data.setValue(selectedAppointment.getStart_time().toLocalDate());
        end_ymd_data.setValue(selectedAppointment.getEnd_time().toLocalDate());
        start_h_data.getSelectionModel().select(String.valueOf(selectedAppointment.getStart_hour()));
        end_h_data.getSelectionModel().select(String.valueOf(selectedAppointment.getEnd_hour()));
        start_min_data.getSelectionModel().select(String.valueOf(selectedAppointment.getStart_min()));
        end_min_data.getSelectionModel().select(String.valueOf(selectedAppointment.getEnd_min()));

    }

//    public static String updateAppointment(long id, String title, String description, String start_time,
//                                           String end_time, long patient_id, long doctor_id){
    public void updateAppointment(ActionEvent event) throws IOException {
        JavaPostgreSql.updateAppointment(
                selectedAppointment.getId(),
                title_data.getText(),
                description_data.getText(),
                start_ymd_data.getValue().toString()+" " + start_h_data.getValue() + ":" + start_min_data.getValue(),
                end_ymd_data.getValue().toString() + " "  + end_h_data.getValue()+":" + end_min_data.getValue(),
                selectedAppointment.getPatient_id(),
                JavaPostgreSql.getUserByFullname(doctor_data.getValue()).getId());

        SceneController s = new SceneController();
        s.switchToAppointments(selectedPatient, event);
    }
}
