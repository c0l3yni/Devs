export const isProductInCart = (product, items) => {
	let isInCart = false;
	items.forEach((item) => {
		if (item.product.id === product.id) {
			isInCart = true;
		}
	});

	return isInCart;
};
