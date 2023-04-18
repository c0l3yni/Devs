package com.tekgs.nextgen.tekegg.data.financial.revenue;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RevenueRepository {
    public static RevenueRepository getInstance() {
        return new RevenueRepository();
    }

    public List<Revenue> query() {
        return getRevenueFromJSON();
    }

    private List<Revenue> getRevenueFromJSON() {
        List<Revenue> revenueList = new ArrayList<>();
        Path path = Paths.get("../source/src/revenue.json");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            revenueList = new Gson().fromJson(reader, new TypeToken<List<Revenue>>() {
            }.getType());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return revenueList;
    }
}
