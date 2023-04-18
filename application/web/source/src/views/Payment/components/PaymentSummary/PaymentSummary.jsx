import React from "react";
import formatCurrency from "../../../../totals/formatCurrency";
import styles from "./styles";

function PaymentSummary({subtotal, shippingAmount, total}) {
    return (
        <div>
            <h2>
                Subtotal: <span id="subtotal-amount">{formatCurrency(subtotal)}</span>
            </h2>
            <h2>
                Shipping: <span id="shipping-amount">{formatCurrency(shippingAmount)}</span>
            </h2>
            <h2 className={styles.total}>
                Total: <span id="total"> {formatCurrency(total)}</span>
            </h2>
        </div>
    );
}

export default PaymentSummary;
