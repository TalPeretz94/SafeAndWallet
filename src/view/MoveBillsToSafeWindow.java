package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ood.hw3.Bill;

import ood.hw3.HotelGuestMoneyManagement;


import java.util.ArrayList;
public class MoveBillsToSafeWindow extends Stage {
    private ComboBox<Bill> billsComboBox = new ComboBox<>();
    private PasswordField codeField = new PasswordField();
    private Button submitButton = new Button("Move to safe");

    private EventHandler<ActionEvent> onMoveBillsToSafe = event -> {};

    private HotelGuestMoneyManagement hotelGuest;

    public MoveBillsToSafeWindow(HotelGuestMoneyManagement hotelGuest){
        final int W = 200, H = 200, SPACE = 20;

        this.hotelGuest = hotelGuest;

        VBox layout = new VBox();
        Scene scene = new Scene(layout, W, H, Color.CORNFLOWERBLUE);

        layout.getChildren().addAll(billsComboBox, codeField, submitButton);

        layout.setSpacing(SPACE);
        layout.setPadding(new Insets(SPACE));
        layout.setAlignment(Pos.CENTER);

        billsComboBox.setPromptText("Choose Bill Here");
        codeField.setPromptText("Enter code here");

        submitButton.setOnAction(event -> {
            Bill bill = billsComboBox.getSelectionModel().getSelectedItem();

            String code = codeField.getText();

            if(code != null && bill != null) {
                hotelGuest.moveMoneyToSafe(code, bill.getCurrency(), bill.getAmount());

                onMoveBillsToSafe.handle(event);

                close();
            }
        });

        setOnShowing(event -> onShowing());

        setScene(scene);
    }

    private void onShowing() {
        ObservableList<Bill> bills = FXCollections.observableList(new ArrayList<>());
        hotelGuest.getWalletStatus().forEach(bills::add);
        billsComboBox.setItems(bills);
    }

    public void setOnMoveBillsToSafe(EventHandler<ActionEvent> onMoveBillsToSafe) {
        this.onMoveBillsToSafe = onMoveBillsToSafe;
    }
}
