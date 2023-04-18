import React, { useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import postPayment from "../../../../tekEggService/stripe";
import { validateCurrency, validateSource, verifyPaymentSuccess } from "../../validatePayment";
import styles from "./styles";

function PaymentForm({ total }) {
	const [currencyErrorMessage, setCurrencyErrorMessage] = useState("");
	const [sourceErrorMessage, setSourceErrorMessage] = useState("");

	const currencyRef = useRef(null);
	const sourceRef = useRef(null);
	const navigate = useNavigate();

	const navToPurchase = (data) => {
		navigate(`/purchase/${verifyPaymentSuccess(data)}`);
	};

	const onPaymentSubmit = async (e) => {
		e.preventDefault();
		const isValid = validateFields();
		if (isValid) {
			const paymentResponse = await postPayment(
				total,
				currencyRef.current.value,
				sourceRef.current.value
			);
			navToPurchase(paymentResponse);
		}
	};

	function validateFields() {
		const isCurrencyValid = validateCurrency(currencyRef.current.value);
		const isSourceValid = validateSource(sourceRef.current.value);
		if (!isCurrencyValid) setCurrencyErrorMessage("Currency invalid");
		if (!isSourceValid) setSourceErrorMessage("Source invalid");
		return isCurrencyValid && isSourceValid;
	}

	return (
		<form className={styles.paymentContainer} onSubmit={(e) => onPaymentSubmit(e)}>
			<div className={styles.paymentColumn}>
				<div className={styles.paymentLayout}>
					<label
						htmlFor="currency-input"
						className={styles.labelDesign}
					>
						Currency
					</label>
				</div>

				<div className={styles.currencyErrorMessageColumn}>
					<p id="currency-error-message" className={styles.errorMessageColor}>
						{currencyErrorMessage}
					</p>
					<input
						name="currency"
						type="text"
						ref={currencyRef}
						onBlur={() => currencyRef.current.value}
						className={styles.currencyErrorMessageInput}
					/>
				</div>
			</div>

			<div className={styles.paymentSourceColumn}>
				<div className={styles.paymentSourceLayout}>
					<label
						htmlFor="payment-source"
						className={styles.paymentSourceLabel}
					>
						Source
					</label>
				</div>
				<div className={styles.sourceErrorMessageColumn}>
					<p id="source-error-message" className={styles.errorMessageColor}>
						{sourceErrorMessage}
					</p>
					<input
						name="source"
						type="text"
						ref={sourceRef}
						onBlur={() => sourceRef.current.value}
						className={styles.sourceErrorMessageInput}
					/>
				</div>
			</div>

			<div className={styles.submitColumn}>
				<div className="">
					<input
						type="submit"
						id="submit"
						value="submit"
						className={styles.submitInput}
					/>
				</div>
			</div>
		</form>
	);
}

export default PaymentForm;
