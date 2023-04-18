import React, {useEffect, useState} from "react";
import ItemList from "./components/ItemList/ItemList.jsx";
import {getCartId} from "../../cart";
import calculateSubTotal from "../../totals/calculateSubTotal.js";
import {getCartById} from "../../services/User/cartApi.js";
import CartTotal from "./components/CartTotal/CartTotal.jsx";
import styles from "./styles";

function Cart() {
    const [cart, setCart] = useState({});
    const [updated, setUpdated] = useState([]);
    const [total, setTotal] = useState(0);

    useEffect(() => {
        const cartId = getCartId();
        if (cartId) getCartById(cartId).then((cart) => setCart(cart));
    }, []);

    useEffect(() => {
        if (cart.items) {
            setTotal(calculateSubTotal(cart.items));
        }
    }, [cart.items, updated]);

    return (
        <div id="cart" className={styles.cartContainer}>
            <h1 className={styles.header}>Cart Page</h1>
            <div className={styles.gridContainer}>
                <div className={styles.grid}>
                    <ItemList items={cart.items} setUpdated={setUpdated}/>
                    <CartTotal cart={cart} total={total}/>
                </div>
            </div>
        </div>
    );
}

export default Cart;
