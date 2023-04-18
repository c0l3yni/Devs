package com.tekgs.nextgen.tekegg.data.product;

public class ProductExpected implements ProductCalibratable {
    private String description;
    private final String title;
    private final String id;
    private final Integer stock;
    private Integer price;
    private String thumbnail;

    public ProductExpected(Product product) {
        this.description = product.getDescription();
        this.title = product.getTitle();
        this.id = product.getId();
        this.stock = product.getStock();
        this.thumbnail = product.getThumbnail();
        this.price = product.getPrice();
    }

    public static ProductExpected getInstance(Product product) {
        return new ProductExpected(product);
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
        return false;
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
