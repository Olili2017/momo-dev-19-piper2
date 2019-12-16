const axios = require('axios')

class UuidClient {

    getUUID(callback){

            axios.get("https://www.uuidgenerator.net/api/version4")
                    .then(result => {
                        // leave trim here ... code breaks if not trimmed
                        callback(result.data.trim())
                    })
                    .catch(err => {
                        // res.json(`Error : ${err}`)
                    })
    }


}

const uuidClient = new UuidClient()
exports = module.exports = uuidClient