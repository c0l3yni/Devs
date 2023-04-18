const axios = require("axios");
const router = axios.create({
	baseURL: "http://localhost:8000",
});

export async function getCartById(id) {
	try {
		const res = await router.get(`/cart/${id}`);
		return res.data[0];
	} catch (error) {
		console.log(error);
	}
}

export function updateItemQuantity(cart) {
	try {
		const res = router.put(`/cart/${cart.id}`, {cart});
		return res;
	} catch (error) {
		console.log(error);
	}
}
