export default {
	productListRegion: [
		"container",
		"mx-auto",
		"p-4",
		"grid",
		"sm:grid-cols-1",
		"md:grid-cols-2",
		"xl:grid-cols-3",
	].join(" "),
	isOutOfStock: "text-red-600 bg-gray-400",
	isInStock: "text-white bg-emerald-500 hover:bg-emerald-600",
	productButton(stock) {
		return [
			stock ? this.isOutOfStock: this.isInStock,
			"flex",
			"border-0",
			"py-2",
			"px-6",
			"focus:outline-none",
			"rounded",
			"ml-10",
		].join(" ")
	},
	outOfStockText: [
		"mt-1"
	].join(" ")
};
