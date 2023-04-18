const cleanProductDescription = (description) => {
	const hiddenBackspaceCharacter = "(U+2408)";
	const isValidDescription =
		typeof description === "string" &&
		description &&
		!description.includes(hiddenBackspaceCharacter);
	return isValidDescription ? description : "";
};

export default cleanProductDescription;
