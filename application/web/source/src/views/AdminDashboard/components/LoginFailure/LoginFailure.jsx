import React from "react";
import styles from "./styles";
function LoginFailure({ date, failureCount }) {
	return (
		<li className={`login-failure-region ${styles.listItem}`}>
			Date: <span className="date-of-failures">{date}</span>, Failures:{" "}
			<span className="login-failures-count">{failureCount}</span>
			<button className={styles.dropdownButton}>↓</button>
		</li>
	);
}

export default LoginFailure;
