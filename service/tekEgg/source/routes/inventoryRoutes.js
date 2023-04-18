const router = require("express").Router();
const { getAll, getProductById } = require("../interfaces/inventoryInterface")

router.route("/").get(getAll);
router.route("/:id").get(getProductById);

module.exports = router;
