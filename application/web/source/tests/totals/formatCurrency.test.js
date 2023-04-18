import expect from "expect";
import formatCurrency from "../../src/totals/formatCurrency.js";

const scenarios = [
	[49, "$0.49"],
	[null, "$0.00"],
	["poop", "$0.00"],
	[undefined, "$0.00"],
	[-50000, "-$500.00"],
	["1000000", "$10,000.00"],
	[true, "$0.00"],
	[false, "$0.00"],
	[0.4, "$0.00"],
];

test.each(scenarios)("formatCurrencyTest amount:%p", (input, expected) => {
	const actual = formatCurrency(input);
	expect(actual).toEqual(expected);
});
