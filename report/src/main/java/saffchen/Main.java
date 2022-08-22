package saffchen;

import org.springframework.boot.SpringApplication;
import saffchen.reports.GenerateReportCommand;

public class Main {
    public static void main(String[] args) throws Exception {
       new GenerateReportCommand().doCommand();
    }
}
