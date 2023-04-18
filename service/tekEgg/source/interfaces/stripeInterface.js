const axios = require("axios");
const CONTENT = "Content-Type";
const TYPE = "application/json";

const stripeInterface = {
  post(req, res) {
    res.header(CONTENT, TYPE);
    const SECURE_PROTOCOL = "https";
    const body = req.body;
    axios
      .post(`${SECURE_PROTOCOL}://api.stripe.com/v1/charges`, body, {
        headers: {
          Authorization: `Bearer ${process.env.API_KEY}`,
          "Content-Type": "application/x-www-form-urlencoded",
        },
      })
      .then((result) => res.json(result.data))
      .catch((err) => res.status(400).send(err.response.data));
  },
};

module.exports = stripeInterface;
