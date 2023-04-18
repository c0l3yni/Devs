const cartController = require("../controllers/cartController");
const CONTENT = "Content-Type";
const TYPE = "application/json";

const cartService = {
  findAll(req, res) {
    res.header(CONTENT, TYPE);
    res.json(cartController().getAll());
  },

  find(req, res) {
    res.header(CONTENT, TYPE);
    const cart = cartController().get(req.params.id);
    if (!cart) {
      res.status(404).send("Cart not found");
      return;
    }
    res.json([cart]);
  },

  update(req, res) {
    res.header(CONTENT, TYPE);
    const cartId = req.params.id;
    const cartToUpdate = req.body.cart;
    const cart = cartController().updateItemQuantity(cartId, cartToUpdate);
    if (!cart) {
      res.status(404).send("Item not found");
      return;
    }
    res.json([cart]);
  },

  create(req, res) {
    res.header(CONTENT, TYPE);
    const cartToCreate = req.body.cart;
    const cart = cartController().create(cartToCreate);
    if (!cart) {
      res.status(400).send("Invalid Cart Provided");
      return;
    }
    res.json([cart]);
  },
};

module.exports = cartService;
