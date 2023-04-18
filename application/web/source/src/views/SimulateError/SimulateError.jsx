import React from "react";

function SimulateError() {

	const throwError = () => {
		throw "MAJOR ERROR";
	};

	return <div>{throwError()}</div>;
}

export default SimulateError;
