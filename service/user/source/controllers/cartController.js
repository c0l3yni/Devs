const carts = require("../data/cartRepository");

function cartController(repository) {
    if(!repository){
        repository = carts();
    }
    return {
        getAll() {
            return repository.data;
        },
        get(id) {
            return repository.data.find((cart) => cart.id === id);
        },
        updateItemQuantity(cartId, productId, newQuantity) {
            const allCarts = this.getAll();
            const cartToUpdate = allCarts.find((cart) => cart.id === cartId);
            if (!cartToUpdate) return;
            const item = cartToUpdate.items.find((item) => item.product.id === productId);
            item.quantity = newQuantity;
            repository.update(allCarts);
            if (item) return this.get(cartId);
        },
    }
};

module.exports = cartController;
