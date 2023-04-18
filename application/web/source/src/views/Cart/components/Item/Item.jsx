import React, { useState } from "react";
import Product from "../../../../regions/ProductRegion/Product";
import {getCartId} from "../../../../cart";
import { updateItemQuantity } from "../../../../services/User/cartApi";
import formatCurrency from "../../../../totals/formatCurrency";
import calculateItemTotal from "../../../../totals/calculateItemTotal";
import styles from "./styles";

function Item({ item, setUpdated }) {
	const cartId = getCartId();
	const notFoundImage = "https://via.placeholder.com/1280x960.png?text=Image+Not+Found";
	const [quantity, setQuantity] = useState(item.quantity);

	const handleIncrease = () => {
		setQuantity((prevState) => prevState + 1);
		item.quantity += 1;
		updateItemQuantity(cartId, item);
		setUpdated([]);
	};

	const handleDecrease = () => {
		if (quantity > 0) {
			setQuantity((prevState) => prevState - 1);
			item.quantity -= 1;
			updateItemQuantity(cartId, item);
			setUpdated([]);
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
								<h4 className={`item-quantity ${styles.quantity}`}>{quantity}</h4>
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
