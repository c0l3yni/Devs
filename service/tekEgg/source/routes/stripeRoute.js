const router = require("express").Router();
const { post } = require("../interfaces/stripeInterface")

router.route("/").post(post);

module.exports = router;