const fs = require("fs");

function carts(path) {
    if(!path){
        path = "./data/cart.json";
    }
    const cartsString = fs.readFileSync(path, {encoding: "utf8"}, (err, data) => {
        if (err) {
            throw "failed to read from carts.json";
        }
        return data;
    });

    return {
        data: JSON.parse(cartsString),
        update(newCartsData) {
            fs.writeFileSync(path, JSON.stringify(newCartsData, null, 2), (err) => {
                if (err) {
                    throw "failed to write to json";
                }
            });
        },
    }
}

module.exports = carts;
