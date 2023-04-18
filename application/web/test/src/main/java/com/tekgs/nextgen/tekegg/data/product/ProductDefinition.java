package com.tekgs.nextgen.tekegg.data.product;

public class ProductDefinition implements ProductCalibratable {
    public static final String DEFAULT_THUMBNAIL = "https://via.placeholder.com/1280x960.png?text=Image+Not+Found";
    private String description;
    private String title;
    private String id;
    private Integer stock;
    private Integer price;
    private String thumbnail;


    public static ProductDefinition getInstance() {
        return new ProductDefinition();
    }

    public ProductDefinition withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductDefinition withStock(int stock) {
        this.stock = stock;
        return this;
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

    public ProductDefinition withThumbnail(String thumbnail) {
        this.thumbnail = thumbnail == null ? DEFAULT_THUMBNAIL : thumbnail;
        return this;
    }

    public ProductDefinition withPrice(Integer price) {
        this.price = price;
        return this;
    }

    public ProductDefinition withId(String productId) {
        this.id = productId;
        return this;
    }
}
