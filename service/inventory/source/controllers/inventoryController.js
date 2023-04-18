const inventory = require("../data/inventoryRepository");

function inventoryController(repository) {
    if(!repository){
        repository = inventory();
    }
    return {
        getAll() {
            return repository.data;
        },
        get(id){
            return repository.data.find((product) => product.id === id);
        }
    }
}

module.exports = inventoryController;
