package app;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ConnectionUtil;

public class Demo extends Application {
	public static final double TAX = 0.08675;

	public static final DecimalFormat DF = new DecimalFormat("####.##");

	public static void main(String[] args) {
		Connection conn = null;
		conn = ConnectionUtil.getConnection();
		try {
			Statement statement = conn.createStatement();
			statement.setQueryTimeout(30); // not required, if DB doesnt respond in 30 seconds, it will quit. not
											// required by good to have
			ResultSet rs = statement.executeQuery("SELECT * FROM coffeeOrders");

//			Statement s = conn.createStatement();
//			boolean flag = s.execute("insert into item(name, price) values('Reuben Sandwhich',5.00)");

			while (rs.next()) {
				System.out
						.println("Price: " + rs.getDouble("price") + ". CoffeeOrders: " + rs.getString("coffeeOrder"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConnection(conn);
		}
		launch(args);

	}

	@Override
	public void start(Stage pStage) throws Exception {
		File file = new File("src/view/POS.fxml");
		Parent root = FXMLLoader.load(file.toURI().toURL());
		Scene scene = new Scene(root, 650, 600);
		pStage.setTitle("Corner Coffee Shop");
		pStage.setScene(scene);
		pStage.show();
	}

}
