package com.tekgs.nextgen.tekegg.data.cart.product;

public class ProductDefinition implements ProductCalibratable {
    private String description;
    private String title;
    private String id;
    private Integer stock;
    private Integer price;
    private String thumbnail;


    public static ProductDefinition getInstance() {
        return new ProductDefinition();
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
        return null;
    }

    @Override
    public String getThumbnail() {
        return this.thumbnail;
    }
}
