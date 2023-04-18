const router = require("express").Router();
const routes = require("./cartRoutes");

router.use("/cart", routes);

module.exports = router;
