const router = require("express").Router();
const cartRoutes = require("./cartRoutes");
const inventoryRoutes = require("./inventoryRoutes")
const stripeRoute = require("./stripeRoute")

router.use("/cart", cartRoutes);
router.use("/inventory", inventoryRoutes);
router.use("/stripe", stripeRoute);

module.exports = router;