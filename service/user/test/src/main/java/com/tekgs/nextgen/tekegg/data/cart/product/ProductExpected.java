package com.tekgs.nextgen.tekegg.data.cart.product;

public class ProductExpected implements ProductCalibratable {
    private String description;
    private final String title;
    private final String id;
    private final Integer stock;
    private Integer price;
    private String thumbnail;

    public ProductExpected(ProductDefinition productDefinition) {
        this.description = productDefinition.getDescription();
        this.title = productDefinition.getTitle();
        this.id = productDefinition.getId();
        this.stock = productDefinition.getStock();
        this.thumbnail = productDefinition.getThumbnail();
    }
    
    public static ProductExpected getInstance() {
        return new ProductExpected(null);
    }
    
    public static ProductExpected getInstance(ProductDefinition productDefinition) {
        return new ProductExpected(productDefinition);
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
