import expect from "expect"
import calculateSubTotal from "../../src/totals/calculateSubTotal"

const scenarios = [
    [[
      {quantity: 2, product: {price: 5}}
    ], 10],
    [[
      {quantity: 5, product: {price: 5}},
      {quantity: 5, product: {price: 10}},
      {quantity: 5, product: {price: 5}}
    ], 100],
    [[
      {quantity: 1, product: {price: 2}},
      {quantity: 1, product: {price: 10}},
      {quantity: 3, product: {price: 5}}
    ], 27],
    [7, 0],
    [10000, 0],
    [undefined, 0],
    [null, 0],
    [-1, 0],
    ["poooop", 0],
    [true, 0],
    [false, 0],
    [[], 0]
]

test.each(scenarios)("calculateSubTotalTest cart_id:%p", (input, expected) => {
    const actual = calculateSubTotal(input)
    expect(actual).toEqual(expected)
})