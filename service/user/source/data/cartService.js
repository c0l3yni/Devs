const cartController = require("../controllers/cartController");
const CONTENT = "Content-Type";
const TYPE = "application/json";

const cartService = {
	findAll(req, res) {
		res.header(CONTENT, TYPE);
		res.json(cartController().getAll());
	},

	find(req, res) {
		res.header(CONTENT, TYPE);
		const cart = cartController().get(req.params.id);
		if (!cart) {
			res.status(404).send("Cart not found");
			return;
		}
		res.json([cart]);
	},

	update(req, res) {
		res.header(CONTENT, TYPE);
		const cartId = req.params.id;
		const productId = req.body.productId;
		const newQuantity = req.body.quantity;
		const cart = cartController().updateItemQuantity(cartId, productId, newQuantity);
		if (!cart) {
			res.status(404).send("Item not found");
			return;
		}
		res.json([cart]);
	},
};

module.exports = cartService;
