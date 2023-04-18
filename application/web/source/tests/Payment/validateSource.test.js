import expect from "expect";
import {validateSource} from "../../src/views/Payment/validatePayment";
const scenarios = [
    ["tok_amex", true],
    ["tok_visa", true],
    ["tok_visa_debit", true],
    ["tok_mastercard", true],
    ["tok_mastercard_debit", true],
    ["tok_mastercard_prepaid", true],
    ["tok_discover", true],
    ["tok_diners", true],
    ["tok_jcb", true],
    ["tok_unionpay", true],
    ["tok_fake", false],
    [1, false],
    [false, false],
    [true, false],
    ["", false],
    [undefined, false],
    [null, false]
];

test.each(scenarios)("validateSourceTest source:%p", (input, expected) => {
    const actual = validateSource(input);
    expect(actual).toEqual(expected);
})
