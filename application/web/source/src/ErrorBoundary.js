import React from "react";
import { Navigate } from "react-router-dom";

const HighOrderBoundary = (MyComponent) =>
	class ErrorBoundary extends React.Component {
		state = { hasError: false };

		componentDidMount() {
			addEventListener("error", () => {
				this.setState({ hasError: true });
			});
		}
		componentDidCatch() {
			this.setState({ hasError: true });
		}

		render() {
			if (this.state.hasError) {
				this.setState({ hasError: false });
				return <Navigate state={{ isUnhandledException: true }} to="error" replace />;
			}
			return <MyComponent />;
		}
	};

export default HighOrderBoundary;
