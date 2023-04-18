import React from "react";
import Product from "../../../../regions/ProductRegion/Product";
import formatCurrency from "../../../../totals/formatCurrency";
import calculateItemTotal from "../../../../totals/calculateItemTotal";
import styles from "./styles";
import getUrlParams from "../../../../urlParams/getUrlParams";

function Item({ item,  updateQuantity }) {
	const cartId = getUrlParams("cart_id");
	const notFoundImage = "https://via.placeholder.com/1280x960.png?text=Image+Not+Found";

	const handleIncrease = () => {
		const newQuantity =	item.quantity + 1;
		const productId = item.product.id;
		updateQuantity(cartId, newQuantity, productId);		
	};

	const handleDecrease = () => {
		if (item.quantity > 0) {
			const newQuantity =	item.quantity - 1;
			const productId = item.product.id;
			updateQuantity(cartId, newQuantity, productId);	
		}
	};

	const renderItemTotal = () => (
		<h4 className={styles.itemTotal}>
			Item Total:{" "}
			<span className="item-total">
				{formatCurrency(calculateItemTotal(item.quantity, item.product.price))}
			</span>
		</h4>
	);

	return (
		<>
			<div className={`item ${styles.itemSpacing}`} data-product={item.product.id}>
				<div className={styles.itemContainer}>
					<img
						className={`thumbnail ${styles.thumbnail}`}
						src={item.product.thumbnail ? item.product.thumbnail : notFoundImage}
						alt={`Image of ${item.product.title}`}
					/>
					<div className={styles.itemDetails}>
						<Product product={item.product}></Product>
						{renderItemTotal()}
						<div className={styles.buttonFlex}>
							<div className={styles.buttonContainer}>
								<button
									className={`decrease-quantity ${styles.quantityButton}`}
									onClick={handleDecrease}
								>
									-
								</button>
								<h4 className={`item-quantity ${styles.quantity}`}>{item.quantity}</h4>
								<button
									className={`increase-quantity ${styles.quantityButton}`}
									onClick={handleIncrease}
								>
									+
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</>
	);
}

export default Item;
