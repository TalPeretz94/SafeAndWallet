package view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import ood.hw3.Bill;
import ood.hw3.Currency;
import ood.hw3.HotelGuest;

import java.util.Arrays;

public class AddBillToWalletBox extends HBox {
	private ComboBox<Double> valuesComboBox = new ComboBox<>(
			FXCollections.observableList(Arrays.asList(0.1, 0.5, 1.0, 2.0, 5.0, 10.0, 20.0, 50.0, 100.0, 200.0)));
	private Button submitButton = new Button("Add to wallet");
	private ComboBox<Currency> currencyComboBox = new ComboBox<>(
			FXCollections.observableList(Arrays.asList(Currency.values())));

	private int serializer = 100;

	public AddBillToWalletBox() {
		final int SPACE = 15;

		getChildren().addAll(currencyComboBox, valuesComboBox, submitButton);

		submitButton.setOnAction(e -> {
			
			

			HotelGuest.getInstance().addBillToWallet(
					new Bill("B" + (serializer++), valuesComboBox.getSelectionModel().getSelectedItem(),
							currencyComboBox.getSelectionModel().getSelectedItem()));

		});

		setSpacing(SPACE);
		setAlignment(Pos.CENTER);
		setPadding(new Insets(SPACE));
	}

}
