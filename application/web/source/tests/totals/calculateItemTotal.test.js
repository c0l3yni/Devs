import expect from "expect";
import calculateItemTotal from "../../src/totals/calculateItemTotal";

test("calculateItemTotalTest", () => {
	const expected = 6;
	const actual = calculateItemTotal(2, 3);
	expect(actual).toEqual(expected);
});
