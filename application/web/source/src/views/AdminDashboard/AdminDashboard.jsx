import React from "react";
import LoginFailureList from "./components/LoginFailureList/LoginFailureList";
import styles from "./styles";
function AdminDashboard() {
	return (
		<div id="admin-dashboard">
			<h1 className={styles.header}> Admin Dashboard</h1>
			<LoginFailureList />
		</div>
	);
}

export default AdminDashboard;
