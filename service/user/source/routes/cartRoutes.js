const router = require("express").Router();
const { findAll, find, update, create } = require("../data/cartService")

router.route("/").get(findAll).post(create);
router.route("/:id").get(find).put(update);

module.exports = router;
