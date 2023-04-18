const router = require("express").Router();
const { findAll, find } = require("../data/inventoryService")

router.route("/").get(findAll);
router.route("/:id").get(find);

module.exports = router;
