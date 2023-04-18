const express = require("express");
const app = express();
const cors = require("cors");
const routes = require("./routes");

app.use(express.urlencoded({ extended: true }));
app.use(express.json());
app.use(cors({ origin: "http://localhost:8000" }));
app.use(routes);

app.listen(process.env.PORT || 8001, () => {
	console.log("Server is running on port 8001");
});
