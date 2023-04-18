package com.tekgs.nextgen.tekegg.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tekgs.nextgen.tekegg.data.product.Product;
import com.tekgs.nextgen.tekegg.data.product.ProductCalibratable;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InventoryRepository {

    public static InventoryRepository getInstance() {
        return new InventoryRepository();
    }

    public List<Product> queryAll() {
        return getProductsFromJson();
    }

    private List<Product> getProductsFromJson() {
        List<Product> products = new ArrayList<>();
        Path path = Paths.get("../source/data/inventory.json");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            products = new Gson().fromJson(reader, new TypeToken<List<Product>>() {
            }.getType());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public Product query(ProductCalibratable productDefinition) {
        return getProductsFromJson().stream()
                .filter(candidate -> candidate.equivalent(productDefinition))
                .findFirst()
                .orElse(null);
    }
}
