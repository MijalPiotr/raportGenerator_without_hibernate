package pl.pm.raportgenerator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import pl.pm.raportgenerator.csvutils.CsvReader;
import pl.pm.raportgenerator.csvutils.CsvWriter;
import pl.pm.raportgenerator.db.PropertiesTable;
import pl.pm.raportgenerator.model.Store;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class HelloController implements Initializable {
    private static final PropertiesTable propertiesTable = new PropertiesTable();

    private List<File> files = new ArrayList<>();
    private static final FileChooser fileChooser = new FileChooser();
    @FXML
    private Label filesList;
    @FXML
    private Label raport;
    @FXML
    private AnchorPane ap;

    public void initialize() throws SQLException {
        propertiesTable.startConnection();
        propertiesTable.createDB();
    }

    public void shutdown() throws SQLException {
        propertiesTable.closeConnection();
    }

    @FXML
    protected void addFile() {
        files.add(fileChooser.showOpenDialog(ap.getScene().getWindow()));
        setFilesLabel();
    }
    private void setFilesLabel() {
        StringBuilder label = new StringBuilder();
        files.forEach( file -> label.append(file.getName()).append("\n"));
        filesList.setText(label.toString());
    }
    @FXML
    protected void generateReport() throws SQLException, NullPointerException {
        List<Store> storeList = files.stream().map(x -> {
            try {
                return CsvReader.readFromCsvFile(x.getPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).flatMap(Collection::stream).collect(Collectors.toList());
        propertiesTable.insertStoreToDB(storeList);
        int buySum = propertiesTable.selectSumOfOperationBuy();
        int supplySum = propertiesTable.selectSumOfOperationSupply();
        raport.setText(CsvWriter.generate(supplySum, buySum, supplySum-buySum));
        files.clear();
        filesList.setText("");
    }
    @FXML
    protected void saveToFile() throws IOException {
        File file = fileChooser.showSaveDialog(ap.getScene().getWindow());
        if (file != null) {
            CsvWriter.write(raport.getText(), file.getAbsolutePath());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            propertiesTable.startConnection();
            propertiesTable.createDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}