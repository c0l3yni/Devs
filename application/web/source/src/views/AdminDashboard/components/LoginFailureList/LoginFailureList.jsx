import React, { useEffect, useState } from "react";
import LoginFailure from "../LoginFailure/LoginFailure";
import styles from "./styles";
import getUrlParams from "../../../../urlParams/getUrlParams";
import {
	createMapWithLast28Days,
	getLogFile,
	logFileToArray,
	populateMapWithFailures,
} from "./aggregateFailures";

function LoginFailureList() {
	const [failureLogs, setFailureLogs] = useState([]);
	useEffect(() => {
		const logFile = getUrlParams("failure_log")? getUrlParams("failure_log"):"loginFailures";
		getLogFile(`./logs/${logFile}.log`).then((logFile) => {
			const logFileArray = logFileToArray(logFile);
			const lastDate = logFileArray[logFileArray.length - 1];
			const mapLocalFile = createMapWithLast28Days(lastDate);
			const mappedFailures = populateMapWithFailures(mapLocalFile, logFileArray);
			setFailureLogs(Array.from(mappedFailures));
		});
	}, []);

	const renderLoginFailureList = () =>
		failureLogs.map((failure) => {
			return <LoginFailure key={failure[0]} date={failure[0]} failureCount={failure[1]} />;
		});

	return (
		<div id="login-failure-list" className={styles.listContainer}>
			<h2 id="login-failures" className={styles.title}>
				Login Failures
			</h2>
			<ul className={styles.list}>{renderLoginFailureList()}</ul>
		</div>
	);
}

export default LoginFailureList;
