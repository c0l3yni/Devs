import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import styles from "./styles";
function ErrorMessage() {
	const [errorMessage, setErrorMessage] = useState("404 Page Not Found");
	const location = useLocation();
	const navigate = useNavigate();
	let isUnhandledException = location.state?.isUnhandledException;

	const navToLanding = () => {
		navigate("/");
	};

	useEffect(() => {
		if (isUnhandledException) {
			setErrorMessage("Oops, something went wrong on our end");
		}
	}, []);

	return (
		<div className={styles.errorMessageContainer}>
			<h4 className={styles.messageTextSize}>
				<span id="error-message"> {errorMessage}</span>
			</h4>
			<span>
				Please{" "}
				<button
					onClick={navToLanding}
					id="return-home-button"
					className={styles.button}
				>
					return to the homepage
				</button>
			</span>
		</div>
	);
}

export default ErrorMessage;
