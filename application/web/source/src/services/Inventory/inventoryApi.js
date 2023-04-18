const axios = require("axios");
const router = axios.create({
    baseURL: "http://localhost:8001",
});

export async function getAllProducts() {
    try {
        const res = await router.get("/inventory");
        return res.data;
    } catch (error) {
        console.log(error);
    }
}

