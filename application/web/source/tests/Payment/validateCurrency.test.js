import { expect } from "expect";
import {validateCurrency} from "../../src/views/Payment/validatePayment";

const scenarios = [
	["usd", true],
	["cad", false],
	[true, false],
	[false, false],
	[null, false],
	[undefined, false],
	[1, false],
];

test.each(scenarios)("validateCurrencyTest currency: %p", (input, expected) => {
	const actual = validateCurrency(input);
	expect(actual).toEqual(expected);
});
