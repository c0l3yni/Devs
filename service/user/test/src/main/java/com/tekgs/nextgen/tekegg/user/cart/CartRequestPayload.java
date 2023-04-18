package com.tekgs.nextgen.tekegg.user.cart;

public class CartRequestPayload {
    private final String cartId;
    private final String productId;
    private final Integer quantity;

    public CartRequestPayload(String cartId, String productId, Integer quantity) {
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public static CartRequestPayload getInstance(String cartId, String productId, Integer quantity) {
        return new CartRequestPayload(cartId, productId, quantity);
    }

    public String getCartId() {
        return cartId;
    }

    public String getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
