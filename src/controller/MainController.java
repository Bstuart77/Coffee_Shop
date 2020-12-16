package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import app.Demo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.ConnectionUtil;
import javafx.scene.control.TextArea;

public class MainController {
	@FXML public TextArea ta;
	@FXML public TextArea priceArea;
	private double overallPrice;
	private String totalOrder = "";


	DecimalFormat df = new DecimalFormat("####.##");
	
	public void writeHouseBlend(ActionEvent event) {
		priceArea.clear();
		ta.appendText("House Blend\t\t\t\t\s" + Demo.houseBlendPrice + "\n");
		Demo.addTaxAndPrice = Demo.houseBlendPrice * Demo.tax;
		Demo.price = Demo.addTaxAndPrice + Demo.houseBlendPrice;
		overallPrice += Demo.price;
		priceArea.setText("Total Price: " + df.format(overallPrice));
		totalOrder += "House Blend, ";
		System.out.println(totalOrder);
	}
 
	public void writeDarkRoast(ActionEvent event) {
		priceArea.clear();
		ta.appendText("Dark Roast\t\t\t\t\s" + Demo.darkRoastPrice + "\n");
		Demo.addTaxAndPrice = Demo.darkRoastPrice * Demo.tax;
		Demo.price = Demo.addTaxAndPrice + Demo.darkRoastPrice;
		overallPrice += Demo.price;
		priceArea.setText("Total Price: " + df.format(overallPrice));
		totalOrder += "Dark Roast, ";
		System.out.println(totalOrder);
	}

	public void writeDecaf(ActionEvent event) {
		priceArea.clear();
		ta.appendText("Decaf\t\t\t\t\t\s" + Demo.decafPrice + "\n");
		Demo.addTaxAndPrice = Demo.decafPrice * Demo.tax;
		Demo.price = Demo.addTaxAndPrice + Demo.decafPrice;
		overallPrice += Demo.price;
		priceArea.setText("Total Price: " + df.format(overallPrice));
		totalOrder += "Decaf, ";
		System.out.println(totalOrder);
	}

	public void writeSteamedMilk(ActionEvent event) {
		ta.appendText("\tSteamed Milk\t\t\t\s" + Demo.steamedMilkPrice + "\n");
		Demo.addTaxAndPrice = Demo.steamedMilkPrice * Demo.tax;
		Demo.price = Demo.addTaxAndPrice + Demo.steamedMilkPrice;
		overallPrice += Demo.price;
		priceArea.setText("Total Price: " + df.format(overallPrice));
		totalOrder += "Steamed Milk, ";
		System.out.println(totalOrder);
	}

	public void writeSoyMilk(ActionEvent event) {
		ta.appendText("\tSoy Milk\t\t\t\t\s" + Demo.soyMilkPrice + "\n");
		Demo.addTaxAndPrice = Demo.soyMilkPrice * Demo.tax;
		Demo.price = Demo.addTaxAndPrice + Demo.soyMilkPrice;
		overallPrice += Demo.price;
		priceArea.setText("Total Price: " + df.format(overallPrice));
		totalOrder += "Soy Milk, ";
	}
	
	public void submitOrder(ActionEvent event) {
		ta.clear();
		priceArea.clear();
		Alert alert = new Alert(AlertType.CONFIRMATION, "Order received!");
		alert.showAndWait();
		Connection conn = null;
		conn = ConnectionUtil.getConnection();
		try {
			Statement statement = conn.createStatement();
			statement.setQueryTimeout(30);	//not required, if DB doesnt respond in 30 seconds, it will quit. not required by good to have
			statement.executeUpdate("INSERT INTO coffeeOrders(price, coffeeOrder) "
						+ "VALUES('" +df.format(overallPrice) + "','" + totalOrder + "')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionUtil.closeConnection(conn);
		}
	}
}	
