const register = (app) => {

    app.get('/test', (req,res) => {
        res.json("hello")
    })

}

exports = module.exports = { register }