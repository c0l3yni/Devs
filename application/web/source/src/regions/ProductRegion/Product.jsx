import React from "react";
import cleanProductDescription from "./cleanProductDescription";
import formatCurrency from "../../totals/formatCurrency";
import styles from "./styles"

const Product = ({ product, children }) => {
	const notFoundImage = "https://via.placeholder.com/1280x960.png?text=Image+Not+Found";
	return children ? (
		<div className={`product ${styles.productRegion}`} key={product.id} id={product.id}>
			<div className={styles.product}>
				<img
					className={styles.thumbnail}
					src={product.thumbnail ? product.thumbnail : notFoundImage}
					alt={`Image of ${product.title}`}
				></img>
				<div className={styles.details}>
					<h1 className={styles.title}>{product.title}</h1>
					<div className={styles.description}>
						<p className={`description ${styles.truncate}`}>
							{cleanProductDescription(product.description)}
						</p>
					</div>
					<div className={styles.priceAndAddToCartButtonSpacingAndPosition}>
						<div className={styles.priceAndAddToCartButtonContainer}>
							<h1 className={`unit-price ${styles.price}`}>
								{formatCurrency(product.price)}
							</h1>
							{children}
						</div>
					</div>
				</div>
			</div>
		</div>
	) : (
		<li className={`product ${styles.cartProduct}`} key={product.id} id={product.id}>
			<h3 className={styles.cartProductTitle}>{product.title}</h3>
			<p className={`description ${styles.truncate2}`}>
				{cleanProductDescription(product.description)}
			</p>
			<h4>
				Unit Price: <span className="unit-price">{formatCurrency(product.price)} </span>
			</h4>
		</li>
	);
};

export default Product;
