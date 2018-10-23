package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ood.hw3.HotelGuest;
import ood.hw3.HotelGuestMoneyManagement;

public class ChangeCodeWindow extends Stage {

    private VBox layout = new VBox();
    private PasswordField oldCode = new PasswordField(), newCode = new PasswordField();
    private Button changeCodeButton = new Button("Change code");

    public ChangeCodeWindow(HotelGuestMoneyManagement hotelGuestMoneyManagement){
        final int H = 200, W = 300;
        setup();

        changeCodeButton.setOnAction(event ->
                hotelGuestMoneyManagement.setSafeCode(oldCode.getText(), newCode.getText())
        );

        setScene(new Scene(layout,W,H));
    }


    private void setup(){
        setTitle("Change Safe Code");

        final int SPACE = 15;

        layout.getChildren().addAll(oldCode, newCode, changeCodeButton);
        layout.setSpacing(SPACE);
        layout.setPadding(new Insets(SPACE));

        layout.setAlignment(Pos.CENTER);

        oldCode.setPromptText("Old code: (Default - 1234)");

        newCode.setPromptText("New code:");

    }
}
