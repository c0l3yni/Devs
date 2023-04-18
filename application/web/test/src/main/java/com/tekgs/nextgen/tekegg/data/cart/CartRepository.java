package com.tekgs.nextgen.tekegg.data.cart;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CartRepository {
    public static CartRepository getInstance() {
        return new CartRepository();
    }

    public Cart query(CartCalibratable cartDefinition) {
        for (Cart candidate : query()) {
            if (candidate.equivalent(cartDefinition)) {
                return candidate;
            }
        }
        return null;
    }

    public List<Cart> query() {
        List<Cart> carts = new ArrayList<>();
        Path path = Paths.get("../../../service/user/source/data/cart.json");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            carts = new Gson().fromJson(reader, new TypeToken<List<Cart>>() {
            }.getType());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return carts;
    }
}
