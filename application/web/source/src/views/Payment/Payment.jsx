import React, { useEffect, useState } from "react";
import calculateSubTotal from "../../totals/calculateSubTotal";
import PaymentForm from "./components/PaymentForm/PaymentForm";
import PaymentSummary from "./components/PaymentSummary/PaymentSummary";
import { getCartById } from "../../tekEggService/cart";
import styles from "./styles";
import getUrlParams from "../../urlParams/getUrlParams";

function Payment() {
    const [cart, setCart] = useState({});
    const subtotal = cart ? calculateSubTotal(cart.items) : 0;
    const shippingAmount = subtotal ? 1000 : 0;
    const total = subtotal + shippingAmount;

    useEffect(() => {
        const cartId = getUrlParams("cart_id");
        if (cartId) {
            getCartById(cartId).then((cart) => {
                setCart(cart);
            });
        }
    }, []);
    return (
        <div className={styles.paymentContainer}>
            <h1 className={styles.header}>Payment Page</h1>
            <div id="payment" className={styles.paymentRegion}>
                <div className={styles.paymentContent}>
                    <div className={styles.paymentSummary}>
                        <PaymentSummary subtotal={subtotal} shippingAmount={shippingAmount} total={total} />
                    </div>
                    <div className={styles.paymentForm}>
                        <PaymentForm total={total} />
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Payment;
