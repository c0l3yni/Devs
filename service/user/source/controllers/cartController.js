const carts = require("../data/cartRepository");
const cartFactory = require("../models/cartModel");

function cartController(repository) {
	if (!repository) {
		repository = carts();
	}
	return {
		getAll() {
			return repository.data;
		},
		get(id) {
			return repository.data.find((cart) => cart.id === id);
		},
		updateItemQuantity(cartId, cartPayload) {
			const cart = cartFactory(cartPayload);
			let allCarts = this.getAll();
			let cartToUpdateIndex = allCarts.findIndex((candidate) => candidate.id === cartId);
			if (cartToUpdateIndex < 0) return;
			allCarts[cartToUpdateIndex] = cart;
			allCarts[cartToUpdateIndex].updatedAt = Math.round(Date.now() / 1000);
			repository.update(allCarts);
			return this.get(cartId);
		},
		create(cartToCreate) {
			const cart = cartFactory(cartToCreate);
			let allCarts = this.getAll();
			cart.id = (allCarts.length + 1).toString();
			cart.updatedAt = Math.round(Date.now() / 1000);
			allCarts.push(cart)
			repository.update(allCarts);
			return this.get(cart.id);
		},
	};
}

module.exports = cartController;
