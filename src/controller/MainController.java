package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.Demo;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.ConnectionUtil;

public class MainController {
	@FXML public TextArea ta,  priceArea;
	@FXML public HBox menuBox, condimentBox;
	private double overallPrice;
	private String totalOrder;

	public MainController() {
		Platform.runLater(() -> {
			try {
				ta.setFont(Font.font("Monospace"));
				priceArea.setFont(Font.font("Monospace", FontWeight.BOLD, 14));
				Connection conn = ConnectionUtil.getConnection();
				Statement statemnt = conn.createStatement();
				ResultSet rs = statemnt.executeQuery("select * from item");
				while (rs.next()) {
					String itemName = rs.getString("name");
					double price = rs.getDouble("price");
					Button button = new Button(itemName);
					button.setPrefSize(100, 50);
					button.setOnAction(event -> {
						addItem(itemName, price);
					});
					menuBox.getChildren().add(button);
				}
				rs = statemnt.executeQuery("select * from condiment");
				while (rs.next()) {
					String itemName = rs.getString("name");
					double price = rs.getDouble("price");
					Button button = new Button(itemName);
					button.setPrefSize(100, 50);
					button.setOnAction(event -> {
						addItem(itemName, price);
					});
					condimentBox.getChildren().add(button);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public void addItem(String itemName, double price) {
		priceArea.clear();
		String string = String.format("%-32s%1.2f\n", itemName, price);
		ta.appendText(string);
		double itemPrice = price * Demo.TAX + price;
		overallPrice += itemPrice;

		priceArea.setText("Total Price: \t\t\s\s" + Demo.DF.format(overallPrice));
		totalOrder += itemName + ", ";
	}

	public void clear(ActionEvent event) {
		ta.clear();
		priceArea.clear();
		overallPrice = 0;
	}

	public void submitOrder(ActionEvent event) {
		ta.clear();
		priceArea.clear();
		Alert alert = new Alert(AlertType.CONFIRMATION, "Order received!");
		alert.showAndWait();
		writeToFile();

		Connection conn = ConnectionUtil.getConnection();
		try {

			Statement statement = conn.createStatement();
			statement.setQueryTimeout(30); // not required, if DB doesnt respond in 30 seconds, it will quit. not
											// required by good to have
			statement.execute("INSERT INTO coffeeOrders(price, coffeeOrder)" + "VALUES('" + Demo.DF.format(overallPrice)
					+ "','" + totalOrder + "')");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConnection(conn);
		}
	}

	private void writeToFile() {
		File file = new File("Sales/sales.txt");
		try {
			String s = "PRICE: " + Demo.DF.format(overallPrice) + " ORDER: " + totalOrder;
			FileWriter fw = new FileWriter(file, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(s);

			pw.close();
		} catch (Exception e) {

		}

	}
}
