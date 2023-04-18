const formatCurrency = (amount) => {
	const isValidAmount = amount && parseInt(amount);
	const dollarFormatter = new Intl.NumberFormat("en-US", { style: "currency", currency: "USD" });
	const dollars = isValidAmount ? amount / 100 : 0;
	return dollarFormatter.format(dollars);
};

export default formatCurrency;
