
export default function calculateSubTotal(items) {
    if (!items || !Array.isArray(items)) return 0;
    let currentTotal = 0;
    items.forEach((item) => {
        currentTotal += item.product.price * item.quantity;
    });
    return currentTotal;
}
