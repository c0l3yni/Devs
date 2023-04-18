import React from "react";
import { useNavigate } from "react-router-dom";
import formatCurrency from "../../../../totals/formatCurrency";
import styles from "./styles";

function CartTotal({ cart, total }) {
	const navigate = useNavigate();
	const isTotalUnderMin = cart && total < 50;
	const isTotalOverMax = cart && total > 90000000;
	const isTotalOutOfBounds = isTotalOverMax || isTotalUnderMin;

	function renderTotalUnderMinMessage() {
		return (
			<p className="text-red-600" id="minimum-total-message">
				Your cart total needs to be more than 50 cents to checkout
			</p>
		);
	}

	function renderTotalOverMaxMessage() {
		return (
			<p className="text-red-600" id="maximum-total-message">
				Your cart total needs to be less than $900,000.00 to checkout
			</p>
		);
	}

	const renderTotalOutOfBoundsMessage = () => {
		return isTotalUnderMin ? renderTotalUnderMinMessage() : isTotalOverMax ? renderTotalOverMaxMessage() : null;
	}

	return (
		<div className={styles.paddingBetweenColumns}>
			<div className={styles.totalBorder}>
				<div className={styles.totalContainer}>
					<p className={styles.totalTitle}>Total: </p>
					<div>
						<span id="total" className="mr-5">
							{formatCurrency(total)}
						</span>
						<button
							id="checkout"
							className={styles.checkoutButton({isTotalOutOfBounds})}

							onClick={() => navigate(`/payment?cart_id=${cart.id}`)}
							disabled={isTotalOutOfBounds}
						>
							Checkout
						</button>
					</div>
				</div>
				{renderTotalOutOfBoundsMessage()}
			</div>
		</div>
	);
}

export default CartTotal;
