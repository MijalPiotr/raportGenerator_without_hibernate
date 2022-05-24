package pl.pm.raportgenerator.csvutils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CsvWriter {
    public static void write(final String raport, final String path) {
        try {

            System.out.println(path);
            FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(raport);
            pw.flush();
            pw.close();

        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String generate(int supply, int buy, int result){
        Date date = new Date();
        StringBuilder sb = new StringBuilder();
        sb.append("Report. Date:").append(new SimpleDateFormat("dd-MM-yyyy").format(date)).append("\n")
                .append("supply,").append(supply).append("\n")
                .append("buy,").append(buy).append("\n")
                .append("result,").append(result).append("\n");
        return sb.toString();
    }
}
