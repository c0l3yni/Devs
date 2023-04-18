import React from "react";
import Item from "../Item/Item";
import styles from "./styles"

const ItemList = ({ items, updateQuantity }) => {
    const renderEmptyMessage = () => {
        return <p id="empty">Your cart is empty</p>;
    };

    const renderItemList = () => {
        return (
            <div className={styles.itemListContainer}>
                <ul id="item-list">
                    {items.map((item) => (
                        <Item item={item} key={item.product.id}  total={total} updateQuantity={updateQuantity} />
                    ))}
                </ul>
            </div>
        );
    };

    return items ? renderItemList() : renderEmptyMessage();
};

export default ItemList;
