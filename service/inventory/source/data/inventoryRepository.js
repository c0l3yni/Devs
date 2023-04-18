const fs = require("fs");

function inventory(path) {
    if(!path){
        path = "./data/inventory.json";
    }
    const inventoryString = fs.readFileSync(path, {encoding: "utf8"}, (err, data) => {
        if (err) {
            throw "failed to read from inventory.json";
        }
        return data;
    });

    return {
        data: JSON.parse(inventoryString)
    }
}

module.exports = inventory;
