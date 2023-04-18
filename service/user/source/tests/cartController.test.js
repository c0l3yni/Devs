const { expect } = global;
const cartController = require("../controllers/cartController");
const carts = require("../data/cartRepository");
const mockRepository = carts("./tests/carts.json");

test("get all", () => {
	const expected = mockRepository.data;
	const actual = cartController(mockRepository).getAll();
	expect(actual).toEqual(expected);
});

const getScenarios = ["2", "11", "-1"];

test.each(getScenarios)("get by id", (input) => {
	const actual = cartController(mockRepository).get(input);
	const expected = mockRepository.data.find((cart) => cart.id === input);
	expect(actual).toEqual(expected);
});

const updateScenarios = ["1", "2", "3"];

test.each(updateScenarios)("update item quantity", (input) => {
	const cartToUpdate = mockRepository.data.find((cart) => cart.id === input);
	cartToUpdate.items[0].quantity += 1;
	const actual = cartController(mockRepository).updateItemQuantity(input, cartToUpdate);
	const expected = mockRepository.data.find((cart) => cart.id === input);
	expect(actual).toEqual(expected);
});
