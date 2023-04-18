function cartFactory(props) {
	const itemList = [];
	props.items.forEach((item) => itemList.push(itemFactory(item)));
	return {
		id: props.id,
		updatedAt: props.updatedAt,
		items: itemList,
	};
}

function itemFactory(props) {
	return {
		quantity: props.quantity,
		product: {
			id: props.product.id,
		},
	};
}

module.exports = cartFactory;
