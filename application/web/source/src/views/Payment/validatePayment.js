export function validateCurrency(currency) {
    return currency === "usd";
}

export function validateSource(source) {
    const validSources = [
        "tok_amex",
        "tok_visa",
        "tok_visa_debit",
        "tok_mastercard",
        "tok_mastercard_debit",
        "tok_mastercard_prepaid",
        "tok_discover",
        "tok_diners",
        "tok_jcb",
        "tok_unionpay",
    ];

    return validSources.includes(source);
}

export function verifyPaymentSuccess(data) {
    return data.status === "succeeded" && data.paid;
}
