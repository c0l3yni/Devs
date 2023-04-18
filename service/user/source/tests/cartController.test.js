const {expect} = global;
const cartController = require("../controllers/cartController");
const carts = require("../data/cartRepository");
const mockRepository = carts("./tests/carts.json")

test("get all", () => {
    const expected = mockRepository.data;
    const actual = cartController(mockRepository).getAll();
    expect(actual).toEqual(expected);
});

const getScenarios = ["2", "11", "-1"];

test.each(getScenarios)("get by id", (input) => {
    const actual = cartController(mockRepository).get(input);
    const expected = mockRepository.data.find((cart) => cart.id === input)
    expect(actual).toEqual(expected);
});

const updateScenarios = [
    {cartId: "3", productId: "1", addedQuantity: 1},
    {cartId: "4", productId: "1", addedQuantity: 1},
    {cartId: "3", productId: "1", addedQuantity: -1},
];

test.each(updateScenarios)("update item quantity", (input) => {
    const {cartId, productId, addedQuantity} = input;
    const cartToUpdate = mockRepository.data.find((cart) => cart.id === cartId);
    const itemToUpdate = cartToUpdate.items.find((item) => item.product.id === productId);
    const newQuantity = itemToUpdate.quantity + addedQuantity;
    itemToUpdate.quantity = newQuantity;
    const expected = cartToUpdate;
    const actual = cartController(mockRepository).updateItemQuantity(cartId, productId, newQuantity);
    expect(actual).toEqual(expected);
});
