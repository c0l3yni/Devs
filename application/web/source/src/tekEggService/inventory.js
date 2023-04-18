const axios = require("axios");
const router = axios.create({
    baseURL: "http://localhost:8000",
});

export async function getAllProducts() {
    try {
        const res = await router.get("/inventory");
        return res.data;
    } catch (error) {
        console.log(error);
    }
}

