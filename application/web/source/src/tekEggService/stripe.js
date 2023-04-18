const axios = require("axios");
const router = axios.create({
	baseURL: "http://localhost:8000",
});

export default async function postPayment(amount, currency, source) {
	const body = new URLSearchParams({
		amount: amount,
		currency: currency,
		source: source,
	})
	try {
		const res = await router.post("/stripe", body);
		return res.data;
	} catch (error) {
		console.log(error.response.data);
		return error;
	}
}