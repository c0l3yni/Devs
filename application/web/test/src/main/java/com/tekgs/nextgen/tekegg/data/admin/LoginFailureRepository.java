package com.tekgs.nextgen.tekegg.data.admin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LoginFailureRepository {
    private final String failureLogPath;

    public LoginFailureRepository(String fileName) {
        fileName = fileName == null ? "loginFailures" : fileName;
        this.failureLogPath = String.format("../source/public/logs/%s.log", fileName);
    }

    public static LoginFailureRepository getInstance(String fileName) {
        return new LoginFailureRepository(fileName);
    }

    public static LoginFailureRepository getInstance() {
        return new LoginFailureRepository(null);
    }

    public List<String> query() {
        return getFailureLog();
    }

    private List<String> getFailureLog() {
        List<String> failureLogEntries = new ArrayList<>();
        File failureLogFile = new File(failureLogPath);
        try (BufferedReader fileReader = new BufferedReader(new FileReader(failureLogFile))) {
            String line = fileReader.readLine();
            while (line != null) {
                failureLogEntries.add(line);
                line = fileReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return failureLogEntries;
    }
}
