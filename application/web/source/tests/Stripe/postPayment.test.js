import postPayment from "../../src/tekEggService/stripe";
import { expect } from "expect";

const scenarios = [
  [
    {
      amount: 50,
      currency: "usd",
      source: "tok_visa",
    },
    true,
  ],
  [
    {
      amount: 49,
      currency: "usd",
      source: "tok_visa",
    },
    false,
  ],
  [
    {
      amount: 100,
      currency: "bogus",
      source: "tok_visa",
    },
    false,
  ],
  [
    {
      amount: 9999999,
      currency: "usd",
      source: "bogus",
    },
    false,
  ],
];
test.each(scenarios)("postPaymentTest data:%s", async (input, expected) => {
  const data = await postPayment(input.amount, input.currency, input.source);
  const actual = expected ? !data.error: !data.response.data.error;
  expect(actual).toBe(expected);
});
