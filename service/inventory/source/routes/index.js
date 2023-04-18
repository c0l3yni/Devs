const router = require("express").Router();
const routes = require("./inventoryRoutes");

router.use("/inventory", routes);

module.exports = router;
