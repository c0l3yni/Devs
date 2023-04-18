const axios = require("axios");
const CART_ENDPOINT = "http://localhost:8002/cart";

async function getCartRequest(id, res) {
  const uri = id ? `${CART_ENDPOINT}/${id}` : CART_ENDPOINT;
  try {
    return await axios.get(uri);
  } catch (err) {
    res.status(400).send(err);
  }
}

async function addProducts(cart, res) {
  await Promise.all(
    cart.items.map(async (item) => {
      const { id } = item.product;
      try {
        const response = await axios.get(
          `http://localhost:8001/inventory/${id}`
        );
        item.product = response.data[0];
      } catch (err) {
        res.status(400).send(err);
      }
    })
  );
}

async function putCartRequest(id, body, res) {
  const uri = `${CART_ENDPOINT}/${id}`;
  try {
    const cartsData = await axios.put(uri, body);
    return cartsData;
  } catch (err) {
    res.status(400).send(err);
  }
}

async function postCartRequest(body, res) {
  const uri = `${CART_ENDPOINT}`;
  try {
    const cartsData = await axios.post(uri, body);
    return cartsData;
  } catch (err) {
    res.status(400).send(err);
  }
}

module.exports = { getCartRequest, addProducts, putCartRequest, postCartRequest };
