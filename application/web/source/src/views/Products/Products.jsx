import React from "react";
import ProductList from "./components/ProductList/ProductList";
import styles from "./styles";

function Products() {
	return (
		<div id="products-view" className={styles.productsView}>
			<h1 className={styles.header}>Products</h1>
			<ProductList />
		</div>
	);
}

export default Products;
