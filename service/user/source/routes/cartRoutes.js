const router = require("express").Router();
const { findAll, find, update } = require("../data/cartService")

router.route("/").get(findAll);
router.route("/:id").get(find).put(update);

module.exports = router;
