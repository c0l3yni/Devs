package com.tekgs.nextgen.tekegg.data.product;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Product implements ProductCalibratable {
    public static final String DEFAULT_DESCRIPTION = "";
    public static final String DEFAULT_THUMBNAIL = "https://via.placeholder.com/1280x960.png?text=Image+Not+Found";
    public static final String HIDDEN_BACKSPACE_CHARACTER = "(U+2408)";
    private final String description;
    private final String title;
    private final String id;
    private final Integer stock;
    private final Integer price;
    private final String thumbnail;

    public Product() {
        this.description = null;
        this.title = null;
        this.id = null;
        this.stock = null;
        this.price = null;
        this.thumbnail = null;
    }

    private static boolean areEquivalent(Object comparatorValue, Object thisValue) {
        return comparatorValue == null || (thisValue != null && thisValue.equals(comparatorValue));
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean equivalent(ProductCalibratable comparator) {
        boolean isEquivalent=true;
        if (comparator == null) {
            return false;
        }
        if (comparator.getId() != null ) {
            isEquivalent = this.getId().equals(comparator.getId());
        }
        String thisDescription = this.getDescription() == null || HIDDEN_BACKSPACE_CHARACTER.equals(this.getDescription()) ? DEFAULT_DESCRIPTION : this.getDescription();
        String comparatorDescription = comparator.getDescription() == null || HIDDEN_BACKSPACE_CHARACTER.equals(comparator.getDescription()) ? DEFAULT_DESCRIPTION : comparator.getDescription();
        String defaultThumbnail = this.getThumbnail() == null ? DEFAULT_THUMBNAIL : this.getThumbnail();
        isEquivalent &= "".equals(comparatorDescription) || areEquivalent( comparatorDescription, thisDescription);
        isEquivalent &= areEquivalent(comparator.getTitle(), this.getTitle());
        isEquivalent &= areEquivalent(comparator.getStock(), this.getStock());
        isEquivalent &= areEquivalent(comparator.getPrice(), this.getPrice());
        isEquivalent &= areEquivalent(comparator.getThumbnail(), defaultThumbnail);
        return isEquivalent;
    }

    @Override
    public Integer getStock() {
        return this.stock;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Integer getPrice() {
        return this.price;
    }

    @Override
    public String getThumbnail() {
        return this.thumbnail;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(this);
    }
}
