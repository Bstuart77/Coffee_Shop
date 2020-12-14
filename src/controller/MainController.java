package controller;

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

	public void writeHouseBlend(ActionEvent event) {
		ta.appendText("House Blend\t\t\t\t\s" + Demo.houseBlendPrice + "\n");
	}

	public void writeDarkRoast(ActionEvent event) {
		ta.appendText("Dark Roast\t\t\t\t\s" + Demo.darkRoastPrice + "\n");
	}

	public void writeDecaf(ActionEvent event) {
		ta.appendText("Decaf\t\t\t\t\t\s" + Demo.decafPrice + "\n");
	}

	public void writeSteamedMilk(ActionEvent event) {
		ta.appendText("\tSteamed Milk\t\t\t\s" + Demo.steamedMilkPrice + "\n");
	}

	public void writeSoyMilk(ActionEvent event) {
		ta.appendText("\tSoy Milk\t\t\t\t\s" + Demo.soyMilkPrice + "\n");
	}
}
