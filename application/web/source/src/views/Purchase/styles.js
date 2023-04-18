export default {
  purchaseContainer: ["text-center", "text-gray-800", "py-20", "px-6"].join(
    " "
  ),
  confirmationMessage: (result) =>
    [
      result ? "text-green-600" : "text-red-600",
      "font-extrabold",
      "text-7xl",
      "mt-0",
      "mb-6",
    ].join(" "),
};
