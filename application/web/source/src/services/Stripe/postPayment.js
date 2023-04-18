import "isomorphic-fetch";

export default async function postPayment(amount, currency, source) {
	const SECURE_PROTOCOL = "https";
	const response = await fetch(`${SECURE_PROTOCOL}://api.stripe.com/v1/charges`, {
		method: "POST",
		mode: "cors",
		headers: {
			Authorization:
				"Bearer sk_test_51LDBFTDYbK9Hq19fUWSl7UxCxINLUUZ96XtI6bmT3r381Uaib5QcntRMf7IoXsXoYd0ihWDk2gcnUrKbdSoVh2oq00hEBBdAdu",
			"Content-Type": "application/x-www-form-urlencoded",
		},
		body: new URLSearchParams({
			amount: amount,
			currency: currency,
			source: source,
		}),
	});
	return response.json();
}
