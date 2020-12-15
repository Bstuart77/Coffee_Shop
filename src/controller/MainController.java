package controller;

import java.text.DecimalFormat;

import app.Demo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class MainController {
	@FXML private Button houseBlendButton;
	@FXML private Button darkRoastButton;
	@FXML private Button decafButton;
	@FXML private Button steamedMilkButton;
	@FXML private Button soyMilkButton;
	@FXML public TextArea ta;
	@FXML public TextArea priceArea;
	private double overallPrice;

	DecimalFormat df = new DecimalFormat("####.##");
	public void writeHouseBlend(ActionEvent event) {
		priceArea.clear();
		ta.appendText("House Blend\t\t\t\t\s" + Demo.houseBlendPrice + "\n");
		Demo.addTaxAndPrice = Demo.houseBlendPrice * Demo.tax;
		Demo.price = Demo.addTaxAndPrice + Demo.houseBlendPrice;
		overallPrice += Demo.price;
		priceArea.setText("Testing " + df.format(overallPrice));
	}
 
	public void writeDarkRoast(ActionEvent event) {
		priceArea.clear();
		ta.appendText("Dark Roast\t\t\t\t\s" + Demo.darkRoastPrice + "\n");
		Demo.addTaxAndPrice = Demo.darkRoastPrice * Demo.tax;
		Demo.price = Demo.addTaxAndPrice + Demo.darkRoastPrice;
		overallPrice += Demo.price;
		priceArea.setText("Testing " + df.format(overallPrice));
	}

	public void writeDecaf(ActionEvent event) {
		priceArea.clear();
		ta.appendText("Decaf\t\t\t\t\t\s" + Demo.decafPrice + "\n");
		Demo.addTaxAndPrice = Demo.decafPrice * Demo.tax;
		Demo.price = Demo.addTaxAndPrice + Demo.decafPrice;
		overallPrice += Demo.price;
		priceArea.setText("Testing " + df.format(overallPrice));
	}

	public void writeSteamedMilk(ActionEvent event) {
		ta.appendText("\tSteamed Milk\t\t\t\s" + Demo.steamedMilkPrice + "\n");
		Demo.addTaxAndPrice = Demo.steamedMilkPrice * Demo.tax;
		Demo.price = Demo.addTaxAndPrice + Demo.steamedMilkPrice;
		overallPrice += Demo.price;
		priceArea.setText("Testing " + df.format(overallPrice));
	}

	public void writeSoyMilk(ActionEvent event) {
		ta.appendText("\tSoy Milk\t\t\t\t\s" + Demo.soyMilkPrice + "\n");
		Demo.addTaxAndPrice = Demo.soyMilkPrice * Demo.tax;
		Demo.price = Demo.addTaxAndPrice + Demo.soyMilkPrice;
		overallPrice += Demo.price;
		priceArea.setText("Testing " + df.format(overallPrice));
	}
}
