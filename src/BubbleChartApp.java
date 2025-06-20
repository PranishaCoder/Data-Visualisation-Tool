import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.image.WritableImage;
import javafx.scene.SnapshotParameters;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class BubbleChartApp extends Application {

    private XYChart.Series<Number, Number> series = new XYChart.Series<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("BUBBLE CHART GENERATOR");

        Label label = new Label("Enter data for the bubble chart:");
        label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField xInput = new TextField();
        xInput.setPromptText("X-Value");
        TextField yInput = new TextField();
        yInput.setPromptText("Y-Value");
        TextField radiusInput = new TextField();
        radiusInput.setPromptText("Bubble Size (Radius)");


        Button addButton = new Button("Add Data");
        addButton.setStyle("-fx-font-size: 14px; -fx-background-color: #d35400; -fx-text-fill: white;");
        Button saveButton = new Button("Save as PNG");
        saveButton.setStyle("-fx-font-size: 14px; -fx-background-color: #3498db; -fx-text-fill: white;");

        BubbleChart<Number, Number> bubbleChart = new BubbleChart<>(new NumberAxis(), new NumberAxis());
        bubbleChart.getData().add(series);

        TableView<ItemData> tableView = new TableView<>();
        TableColumn<ItemData, Number> xValueCol = new TableColumn<>("X-Value");
        xValueCol.setCellValueFactory(cellData -> cellData.getValue().xValueProperty());
        TableColumn<ItemData, Number> yValueCol = new TableColumn<>("Y-Value");
        yValueCol.setCellValueFactory(cellData -> cellData.getValue().yValueProperty());
        TableColumn<ItemData, Number> radiusCol = new TableColumn<>("Radius");
        radiusCol.setCellValueFactory(cellData -> cellData.getValue().radiusProperty());
        tableView.getColumns().addAll(xValueCol, yValueCol, radiusCol);

        addButton.setOnAction(event -> {
            try {
                double x = Double.parseDouble(xInput.getText());
                double y = Double.parseDouble(yInput.getText());
                double radius = Double.parseDouble(radiusInput.getText());
                series.getData().add(new XYChart.Data<>(x, y, radius));
                tableView.getItems().add(new ItemData(x, y, radius));
                xInput.clear();
                yInput.clear();
                radiusInput.clear();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid numbers.");
            }
        });

        saveButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Chart as PNG");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Image", "*.png"));
            File file = fileChooser.showSaveDialog(primaryStage);
            if (file != null) {
                try {
                    WritableImage image = bubbleChart.snapshot(new SnapshotParameters(), null);
                    ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        HBox inputs = new HBox(10, new Label("X:"), xInput, new Label("Y:"), yInput, new Label("Size:"), radiusInput);
        HBox buttons = new HBox(10, addButton, saveButton);
        VBox root = new VBox(10, label, inputs, buttons, tableView, bubbleChart);
        root.setStyle("-fx-padding: 20px; -fx-background-color: #f4f4f4;");

        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static class ItemData {
        private final SimpleDoubleProperty xValue;
        private final SimpleDoubleProperty yValue;
        private final SimpleDoubleProperty radius;


        public ItemData(double xValue, double yValue, double radius) {
            this.xValue = new SimpleDoubleProperty(xValue);
            this.yValue = new SimpleDoubleProperty(yValue);
            this.radius = new SimpleDoubleProperty(radius);
        }

        public double getXValue() { return xValue.get(); }
        public SimpleDoubleProperty xValueProperty() { return xValue; }
        public double getYValue() { return yValue.get(); }
        public SimpleDoubleProperty yValueProperty() { return yValue; }
        public double getRadius() { return radius.get(); }
        public SimpleDoubleProperty radiusProperty() { return radius; }
    }
} 