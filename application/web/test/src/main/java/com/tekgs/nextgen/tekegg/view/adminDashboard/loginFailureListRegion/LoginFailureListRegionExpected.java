package com.tekgs.nextgen.tekegg.view.adminDashboard.loginFailureListRegion;

import com.tekgs.nextgen.tekegg.data.admin.LoginFailure;
import com.tekgs.nextgen.tekegg.view.adminDashboard.loginFailureListRegion.loginFailureRegion.LoginFailureRegionCalibratable;
import com.tekgs.nextgen.tekegg.view.adminDashboard.loginFailureListRegion.loginFailureRegion.LoginFailureRegionExpected;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class LoginFailureListRegionExpected implements LoginFailureListRegionCalibratable {
    private final List<LoginFailure> loginFailures = new ArrayList<>();
    private final LinkedHashMap<String, Integer> loginFailureMap = new LinkedHashMap<>();
    private final List<String> logFileLines = new ArrayList<>();

    public LoginFailureListRegionExpected(List<String> logs) {
        this.logFileLines.addAll(logs);
        populateMapWithLast28Days();
        populateLoginFailureMap();
        createFailureList();
    }

    public static LoginFailureListRegionExpected getInstance(List<String> logFileLines) {
        return new LoginFailureListRegionExpected(logFileLines);
    }

    @SuppressWarnings("Convert2MethodRef")
    private void populateLoginFailureMap() {
        logFileLines.forEach(entry -> aggregateFailuresByDate(entry));
    }

    private LocalDate getLatestDate() {
        if (!logFileLines.isEmpty()) {
            return LocalDate.parse(this.logFileLines.get(this.logFileLines.size() - 1));
        }
        return LocalDate.now();
    }

    private void populateMapWithLast28Days() {
        LocalDate startDate = getLatestDate().minusDays(27);
        List<LocalDate> past28Days = Stream.iterate(startDate, date -> date.plusDays(1)).limit(28).toList();
        past28Days.forEach(date -> loginFailureMap.put(date.toString(), 0));
    }

    public List<LoginFailureRegionCalibratable> getLoginFailureList() {
        List<LoginFailureRegionCalibratable> loginFailureRegions = new ArrayList<>();
        this.loginFailures.forEach(loginFailure -> loginFailureRegions.add(LoginFailureRegionExpected.getInstance(loginFailure)));
        return loginFailureRegions;
    }

    private void createFailureList() {
        for (Map.Entry<String, Integer> entry : loginFailureMap.entrySet()) {
            String date = entry.getKey();
            Integer failures = entry.getValue();
            loginFailures.add(LoginFailure.getInstance(date, failures));
        }
    }

    private void aggregateFailuresByDate(String date) {
        if (loginFailureMap.containsKey(date)) {
            Integer currentFailures = loginFailureMap.get(date);
            loginFailureMap.put(date, currentFailures + 1);
        }
    }


}
