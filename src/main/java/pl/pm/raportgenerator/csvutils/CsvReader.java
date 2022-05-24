package pl.pm.raportgenerator.csvutils;

import pl.pm.raportgenerator.model.Store;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CsvReader {
    public static List<Store> readFromCsvFile(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath))
                .map(l -> {
                    String[] a = l.split(",");
                    return new Store(a[0], Integer.valueOf(a[1]));
                })
                .collect(Collectors.toList());
    }
}
