import React from "react";
import { useParams } from "react-router-dom";
import styles from "./styles";

const Purchase = () => {
	const { result } = useParams();

	return (
		<div id="purchase" className={styles.confirmationMessage}>
			<h1 id="confirmation-message" className={styles.confirmationMessage(result === "true")}>
				{result === "true" ? "Purchase was successful" : "Purchase failed"}
			</h1>
		</div>
	);
};

export default Purchase;
