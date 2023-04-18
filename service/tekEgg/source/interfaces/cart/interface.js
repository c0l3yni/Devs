const CONTENT = "Content-Type";
const TYPE = "application/json";
const {
  getCartRequest,
  addProducts,
  putCartRequest,
  postCartRequest
} = require("./orchestration");

const cartInterface = {
  async getAll(req, res) {
    res.header(CONTENT, TYPE);
    const cartsData = await getCartRequest(false, res);
    const cartsWithProducts = cartsData.data
    await Promise.all(
      cartsWithProducts.map(async (cart) => {
        return await addProducts(cart, res);
      }));

    res.json(cartsWithProducts);
  },

  async getCartById(req, res) {
    res.header(CONTENT, TYPE);
    const cartId = req.params.id;
    const cartData = await getCartRequest(cartId, res);
    await addProducts(cartData.data[0], res);
    res.json(cartData.data);
  },

  async updateCartQuantity(req, res) {
    res.header(CONTENT, TYPE);
    const cartId = req.params.id;
    const cartsData = await putCartRequest(cartId, req.body, res);
    await addProducts(cartsData.data[0], res);
    res.json(cartsData.data);
  },

  async createCart(req, res) {
    res.header(CONTENT, TYPE);
    const cartsData = await postCartRequest(req.body, res);
    await addProducts(cartsData.data[0], res);
    res.json(cartsData.data);
  },
};

module.exports = cartInterface;
