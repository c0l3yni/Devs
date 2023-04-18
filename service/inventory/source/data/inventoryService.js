const inventoryController = require("../controllers/inventoryController");
const CONTENT = "Content-Type";
const TYPE = "application/json";

const inventoryService = {
    findAll(req, res) {
        res.header(CONTENT, TYPE);
        res.json(inventoryController().getAll());
    },
    find(req, res) {
		res.header(CONTENT, TYPE);
		const product = inventoryController().get(req.params.id);
		if (!product) {
			res.status(404).send("product not found");
			return;
		}
		res.json([product]);
	}
};

module.exports = inventoryService;
