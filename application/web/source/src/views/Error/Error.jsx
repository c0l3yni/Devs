import React from "react";
import crackedEgg from "../../../public/images/crackedEgg.png";
import ErrorMessage from "./components/ErrorMessage";
import styles from "./styles";

const Error = () => {
	return (
		<div id="error" className={styles.errorContainer}>
			<div className={styles.errorColumn}>
				<h3 className={styles.textSize}>:(</h3>
				<div className={styles.errorCenter}>
					<ErrorMessage />
					<img className={styles.errorMessage} src={crackedEgg} alt="Cracked egg" />
				</div>
			</div>
		</div>
	);
};

export default Error;
