const momoClient = require('../api/MomoClient')

const register = (app) => {

    // student : withdraw money
    app.post("/student/withdraw", (req, res) => {
        // TODO: withdraw student money
    })

    // student : check balance
    app.get("/student/balance", (req, res) => {
        // TODO: check student balance
    })

    // student : get previous transactions
    app.get("/student/{account}/transactions", (req, res) => {
        // TODO: get student tranactios
    })

    // student : request upkeep
    app.post("/student/request", () => {
        // TODO: request upkeep
    })
    // student : pending requests
    app.get("/student/{account}/request/pending", (req, res) => {
        // TODO: get pending requests
    })

    // student : login
    app.post("/student/login", (req, res) => {
        // TODO: login student
    })
}

exports = module.exports = {
    register
}