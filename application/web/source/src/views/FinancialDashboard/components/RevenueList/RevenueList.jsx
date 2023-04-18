import React from "react";
import styles from "./styles";
import revenueData from "../../../../revenue.json";
import Revenue from "../Revenue/Revenue";

function RevenueList() {
	function renderRevenueList() {
		return revenueData.map((revenue) => {
			return <Revenue revenue={revenue} key={revenue.id} />;
		});
	}

	return (
		<div className={styles.revenueRegion}>
			<table className={styles.revenueList} id="revenue-list">
				<caption className={styles.revenueTitle} id="revenue-title">
					Revenue
				</caption>
				<tbody>
					<tr>
						<th className={styles.month}>Month</th>
						<th className={styles.netRevenue}>Net Revenue</th>
					</tr>
					{renderRevenueList()}
				</tbody>
			</table>
		</div>
	);
}
export default RevenueList;
