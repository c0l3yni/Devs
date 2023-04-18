import React, { useEffect, useState } from "react";
import ItemList from "./components/ItemList/ItemList.jsx";
import calculateSubTotal from "../../totals/calculateSubTotal.js";
import { getCartById, updateItemQuantity } from "../../tekEggService/cart";
import CartTotal from "./components/CartTotal/CartTotal.jsx";
import getUrlParams from "../../urlParams/getUrlParams";
import styles from "./styles";

function Cart() {
	const [cart, setCart] = useState({});
	const [total, setTotal] = useState(0);

	useEffect(() => {
		const cartId = getUrlParams("cart_id");
		if (cartId) getCartById(cartId).then((cart) => setCart(cart));
	}, []);

	useEffect(() => {
		if (cart.items) {
			setTotal(calculateSubTotal(cart.items));
		}
	}, [cart.items]);

	const updateQuantity = (cartId, newQuantity, productId) => {
		const updatedCart = cart;
		const item = updatedCart.items.find((item) => item.product.id == productId);
		item.quantity = newQuantity;
		updateItemQuantity(updatedCart).then((cartRes) => setCart(cartRes.data[0]));
	};

	return (
		<div id="cart" className={styles.cartContainer}>
			<h1 className={styles.header}>Cart Page</h1>
			<div className={styles.gridContainer}>
				<div className={styles.grid}>
					<ItemList items={cart.items} updateQuantity={updateQuantity} />
					<CartTotal cart={cart} total={total} />
				</div>
			</div>
		</div>
	);
}

export default Cart;
