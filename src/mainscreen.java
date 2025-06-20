import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class mainscreen {
    @FXML private Button pieButton;
    @FXML private Button barButton;
    @FXML private Button lineButton;
    @FXML private Button areaButton;
    @FXML private Button scatterButton;
    @FXML private Button bubbleButton;
    @FXML private Button logoutButton;

    @FXML
    public void initialize() {
        pieButton.setOnAction(event -> openChart(new pie()));
        barButton.setOnAction(event -> openChart(new bar()));
        lineButton.setOnAction(event -> openChart(new line()));
        areaButton.setOnAction(event -> openChart(new AreaChartApp()));
        scatterButton.setOnAction(event -> openChart(new ScatterChartApp()));
        bubbleButton.setOnAction(event -> openChart(new BubbleChartApp()));
        logoutButton.setOnAction(event -> handleLogout());
    }

    private void openChart(Application chartApp) {
        try {
            chartApp.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleLogout() {
        try {
            Stage currentStage = (Stage) logoutButton.getScene().getWindow();
            
            Parent loginRoot = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Stage loginStage = new Stage();
            loginStage.setTitle("Interactive Data Visualization Tool - Login");
            loginStage.setScene(new Scene(loginRoot, 400, 350));
            loginStage.show();

            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
