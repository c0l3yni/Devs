import { expect } from "expect";
import {verifyPaymentSuccess} from "../../src/views/Payment/validatePayment";
const scenarios = [
  [
    {
      status: "succeeded",
      paid: true,
    },
    true,
  ],
  [
    {
      status: "failed",
      paid: false,
    },
    false,
  ],
  [
    {
      status: "succeeded",
      paid: false,
    },
    false,
  ],
  [
    {
      status: "failed",
      paid: true,
    },
    false,
  ],
  [
    {},
    false,
  ]
];

test.each(scenarios)("verifyPaymentSuccessTest payment:%s", (input, expected) => {
  const actual = verifyPaymentSuccess(input);
  expect(actual).toBe(expected);
});
