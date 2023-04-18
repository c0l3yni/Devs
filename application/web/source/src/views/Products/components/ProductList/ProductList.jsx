import React, { useEffect, useState } from "react";
import { isProductInCart } from "../../../../cart";
import Product from "../../../../regions/ProductRegion/Product";
import { getAllProducts } from "../../../../tekEggService/inventory";
import { getCartById } from "../../../../tekEggService/cart";
import styles from "./styles";
import getUrlParams from "../../../../urlParams/getUrlParams";

function ProductList() {
	const [inventory, setInventory] = useState([]);
	const [items, setItems] = useState([]);
	useEffect(() => {
		getAllProducts().then((inventoryData) => {
			setInventory(inventoryData);
		});
		const cartId = getUrlParams("cart_id");
		if (cartId) getCartById(cartId).then((cart) => setItems(cart.items));
	}, []);
	const renderEmptyMessage = () => {
		return <p id="empty">No products available</p>;
	};
	const renderProductList = () => {
		return (
			<ul id="product-list" className={styles.productListRegion}>
				{inventory.map((product) => {
					const isProductOutStock = product.stock <= 0;
					
					return (
						<Product product={product} key={product.id}>
							<button
								disabled={isProductOutStock || isProductInCart(product, items)}
								className={`add-to-cart ${styles.productButton(
									isProductOutStock || isProductInCart(product, items)
								)}`}
							>
								{isProductOutStock ? (
									<p className={`out-of-stock ${styles.outOfStockText}`}>
										Out of Stock
									</p>
								) : (
									"Add To Cart"
								)}
							</button>
						</Product>
					);
				})}
			</ul>
		);
	};
	return inventory ? renderProductList() : renderEmptyMessage();
}

export default ProductList;
