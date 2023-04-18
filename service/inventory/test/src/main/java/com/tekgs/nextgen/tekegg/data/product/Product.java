package com.tekgs.nextgen.tekegg.data.product;

public class Product implements ProductCalibratable {
    private final String title;
    private final String id;
    private final Integer stock;
    private final Integer price;
    private String description;
    private final String thumbnail;
    
    public Product() {
        this.thumbnail = null;
        this.description = null;
        this.title = null;
        this.id = null;
        this.stock = null;
        this.price = null;
    }
    
    @Override
    public String getDescription() {
        String hiddenBackspaceCharacter = "(U+2408)";
        if (description == null || description.contains(hiddenBackspaceCharacter)) {
            this.description = "";
        }
        return this.description;
    }
    
    @Override
    public boolean equivalent(ProductCalibratable comparator) {
        boolean isDescriptionMatch = comparator.getDescription().equals("") || comparator.getDescription().equals(this.getDescription());
        boolean isTitleMatch = comparator.getTitle() == null || comparator.getTitle().equals(this.getTitle());
        boolean isStockMatch = comparator.getStock() == null || comparator.getStock().equals(this.getStock());
        boolean isPriceMatch = comparator.getPrice() == null || comparator.getPrice().equals(this.getPrice());
        boolean isThumbnailMatch = comparator.getThumbnail() == null || comparator.getThumbnail().equals(this.getThumbnail());
        return isDescriptionMatch && isTitleMatch && isStockMatch && isPriceMatch && isThumbnailMatch;
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
}
