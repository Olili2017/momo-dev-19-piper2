const momoClient = require('../api/MomoClient')

const register = (app) => {

    // parent : send money to student
    app.post("/parent/deposit", (req, res) => {
        // TODO: send money to student account
    })

    // parent : get previous transaction
    app.get("/parent/{phone}/transactions", (req, res) => {
        // TODO: get parent previous transaction
    })

    // parent : view money requests
    app.get("/parent/requests", (req,res) => {
        // TODO: view money request from students
    })

    // parent : signup
    app.post("/parent/register", (req, res) => {
        // TODO: signup parent
    })

    // parent : login
    app.post("/parent/login", (req, res) => {
        // TODO: login student
    })

    // parent: add student to parent
    app.post("/parent/student/add", (req, res) => {
        // TODO: create student
    })
}

exports = module.exports = {
    register
}