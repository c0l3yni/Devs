import expect from "expect";
import cleanProductDescription from "../../src/regions/ProductRegion/cleanProductDescription";

const scenarios = [
	["description", "description"],
	["(U+2408)", ""],
	["", ""],
	[null, ""],
	[undefined, ""],
	[true, ""],
	[false, ""],
	[1, ""]];

test.each(scenarios)("cleanProductDescription description:%p", (input, expected) => {
	const actual = cleanProductDescription(input);
	expect(actual).toEqual(expected);
});
