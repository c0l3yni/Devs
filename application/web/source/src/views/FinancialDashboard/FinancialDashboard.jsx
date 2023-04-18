import React from "react";
import styles from "./styles";
import RevenueList from "./components/RevenueList/RevenueList";

function FinancialDashboard() {
    return (
        <div id="financial-dashboard">
            <h1 className={styles.title}>Financial Dashboard</h1>
            <RevenueList/>
        </div>
    );
}

export default FinancialDashboard;
