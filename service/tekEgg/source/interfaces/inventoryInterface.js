const axios = require("axios");
const CONTENT = "Content-Type";
const TYPE = "application/json";

const inventoryService = {
    getAll(req, res) {
        res.header(CONTENT, TYPE);
        axios.get("http://localhost:8001/inventory")
            .then((result) => res.json(result.data))
            .catch(err => res.send(err));
    },
    getProductById(req, res) {
        res.header(CONTENT, TYPE);
        const productId = req.params.id;
        axios.get(`http://localhost:8001/inventory/${productId}`)
            .then((result) => res.json(result.data))
            .catch(err => res.send(err));
    }
};

module.exports = inventoryService;
