const { expect } = global;
const inventoryController = require("../controllers/inventoryController");
const inventory = require("../data/inventoryRepository");
const mockRepository = inventory("./tests/inventory.test.json");

test("get all", () => {
	const expected = mockRepository.data;
	const actual = inventoryController(mockRepository).getAll();
	expect(actual).toEqual(expected);
});

const getScenarios = ["2", "4", "-1"];

test.each(getScenarios)("get by id", (input) => {
	const actual = inventoryController(mockRepository).get(input);
	const expected = mockRepository.data.find((product) => product.id === input);
	expect(actual).toEqual(expected);
});
