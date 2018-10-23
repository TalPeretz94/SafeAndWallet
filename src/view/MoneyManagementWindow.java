package view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ood.hw3.*;
import ood.hw3.Observer;
import ood.hw3.WrongCodeEvent;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.Set;

public class MoneyManagementWindow extends Application implements Observer {

    private static final double SPACE = 15;
    

    private HotelGuestMoneyManagement hotelGuest = HotelGuest.getInstance(); //Insert here your implementation

    private int billSerializer = 100;

    private ChangeCodeWindow changeCodeWindow = new ChangeCodeWindow(hotelGuest);

    private MoveBillsToSafeWindow moveBillsToSafeWindow = new MoveBillsToSafeWindow(hotelGuest);

    private WalletView walletView = new WalletView();

    private SafeView safeView = new SafeView();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        final double W = 800, H = 600;

        BorderPane borderPane = new BorderPane();

        HBox buttons = bottomButtonsSetup();


        HBox addBillPane = addBillToWalletBoxSetup();

        VBox centerBox = new VBox();

        Scene scene = new Scene(borderPane, W, H);

        walletView.showWallet(hotelGuest.getWalletStatus());
        safeView.showSafe(hotelGuest.getSafeStatus());

        borderPane.setLeft(walletView);
        borderPane.setRight(safeView);

        centerBox.getChildren().addAll(addBillPane);

        borderPane.setCenter(centerBox);

        borderPane.setBottom(buttons);

        hotelGuest.getSafeStatus().addObserver(this);

        primaryStage.setScene(scene);
        primaryStage.show();

    }


    private HBox bottomButtonsSetup(){
        HBox buttonBox = new HBox();
        Button saveButton = new Button("Save"), undoButton = new Button("Undo"),
                changeCodeButton = new Button("Change Safe Code"), moveToSafeButton = new Button("Move bills to safe");

        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(SPACE);
        buttonBox.setPadding(new Insets(SPACE));

        buttonBox.getChildren().addAll(moveToSafeButton, changeCodeButton, saveButton, undoButton);

        changeCodeButton.setOnAction(event -> {
            if(!changeCodeWindow.isShowing())
                changeCodeWindow.show();
        });

        moveToSafeButton.setOnAction(event -> {
            if(!moveBillsToSafeWindow.isShowing())
                moveBillsToSafeWindow.show();
        });

        moveBillsToSafeWindow.setOnMoveBillsToSafe(event -> {
            walletView.showWallet(hotelGuest.getWalletStatus());
            safeView.showSafe(hotelGuest.getSafeStatus());
        });

        saveButton.setOnAction(event -> {
            hotelGuest.save();

            System.out.println("saved state: " + hotelGuest.getWalletStatus());

        });

        undoButton.setOnAction(event -> onUndoPressed());


        return buttonBox;
    }

    private HBox addBillToWalletBoxSetup(){
        final int SPACE = 15;

        HBox addBillBox = new HBox();

        ComboBox<Double> valuesComboBox = new ComboBox<>(FXCollections.observableList(Arrays.asList(0.1, 0.5, 1.0, 2.0, 5.0, 10.0, 20.0, 50.0, 100.0, 200.0)));
        Button submitButton = new Button("Add to wallet");
        ComboBox<Currency> currencyComboBox = new ComboBox<>(FXCollections.observableList(Arrays.asList(Currency.values())));


        valuesComboBox.setPromptText("Amount");

        currencyComboBox.setPromptText("Currency");

        addBillBox.getChildren().addAll(currencyComboBox, valuesComboBox, submitButton);


        submitButton.setOnAction(e -> onAddBillPressed(valuesComboBox, currencyComboBox));


        addBillBox.setStyle("-fx-border-color: black; -fx-border-width: 1");

        addBillBox.setSpacing(SPACE);
        addBillBox.setAlignment(Pos.CENTER);
        addBillBox.setPadding(new Insets(SPACE));

        return addBillBox;
    }


    private void onAddBillPressed(ComboBox<Double> valuesComboBox, ComboBox<Currency> currencyComboBox){

        hotelGuest.addBillToWallet(new Bill(
                "B"+(billSerializer++),
                valuesComboBox.getSelectionModel().getSelectedItem(),
                currencyComboBox.getSelectionModel().getSelectedItem()
        ));

        walletView.showWallet(hotelGuest.getWalletStatus());
    }

    private void onUndoPressed(){
        try {
            hotelGuest.undo();
        }catch (EmptyStackException e){
            new Alert(Alert.AlertType.ERROR, "No more saved states!").showAndWait();
        }

        System.out.println("undo: " + hotelGuest.getWalletStatus());
        walletView.showWallet(hotelGuest.getWalletStatus());
        safeView.showSafe(hotelGuest.getSafeStatus());
    }

    @Override
    public void onPublish(WrongCodeEvent message) {
    	
        new Alert(Alert.AlertType.INFORMATION, message.getErrorType().name() + ": " + message.getDescription()).showAndWait();
    }
}
