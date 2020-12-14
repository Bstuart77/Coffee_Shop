package app;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ConnectionUtil;

public class Demo extends Application {
	
	final public static double houseBlendPrice = 2.00;
	final public static double darkRoastPrice = 2.25;
	final public static double decafPrice = 2.50;
	final public static double steamedMilkPrice = 0.50;
	final public static double soyMilkPrice = 0.75;

	
	public static void main(String[] args) {
		Connection conn = null;
		conn = ConnectionUtil.getConnection();
		try {
			Statement statement = conn.createStatement();
			statement.setQueryTimeout(30);	//not required, if DB doesnt respond in 30 seconds, it will quit. not required by good to have
//			statement.executeUpdate("INSERT INTO coffeeOrders(price, coffeeOrder) "
//						+ "VALUES('9.66','blah')");
			ResultSet rs = statement.executeQuery("SELECT * FROM coffeeOrders");
			while(rs.next()) {
				System.out.println("Price: " + rs.getDouble("price") + ". CoffeeOrders: " + rs.getString("coffeeOrder"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionUtil.closeConnection(conn);
		}
		launch(args);
	}

	@Override
	public void start(Stage pStage) throws Exception {
		File file = new File("src/view/POS.fxml");
		Parent root = FXMLLoader.load(file.toURI().toURL());
		Scene scene = new Scene(root,600,454);
		pStage.setTitle("Corner Coffee Shop");
		pStage.setScene(scene);
		pStage.show();
	}

}
