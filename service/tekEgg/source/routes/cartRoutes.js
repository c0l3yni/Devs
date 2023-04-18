const router = require("express").Router();
const {
  getAll,
  getCartById,
  updateCartQuantity,
  createCart
} = require("../interfaces/cart/interface");

router.route("/").get(getAll).post(createCart);
router.route("/:id").get(getCartById).put(updateCartQuantity);

module.exports = router;
