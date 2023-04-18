import React from "react";
import formatCurrency from "../../../../totals/formatCurrency";
import styles from "../RevenueList/styles";

function Revenue({revenue}) {
	return (
		<tr  className="revenue-region">
			<td className={`revenue-month ${styles.month}`}>{revenue.month}</td>
			<td className={`revenue-amount ${styles.netRevenue}`}>
				{formatCurrency(revenue.netRevenue)}
			</td>
		</tr>
	);

}

export default Revenue;
