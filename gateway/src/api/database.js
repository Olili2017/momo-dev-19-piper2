const axios = require('axios')
const databaseService = axios.create({
    baseURL : "http://localhost:8901"
})

class Database {

    createParent(parent, done, error){
        databaseService.post("/momo/parent/add",
        parent
        ).then(
            result => {
                done(result.data)
            },
            err => {
                error(err)
            }
        )
    }

    createChild(child, done, error){
        databaseService.post("/momo/parent/student/add",child)
        .then(result => {
            done(result.data)
        }, err => {
            error(err)
        })
    }

    depositToParent(account, amount, success){
        databaseService.patch("/momo/parent/account/deposit/",{ amount : amount, to : account})
            .then(response => {
                success(response.data)
            }, err => {
                error(err)
            })
    }

    depositToStudent(payload, success, error){
        databaseService.patch("/momo/parent/deposit/",payload)
            .then(response => {
                success(response.data)
            }, err => {
                error(err)
            })
    }

    getTransactions(){

    }

    getParent(phone, next, error){
        databaseService.get(`/momo/parent/${phone}`)
            .then(
                parent => {
                    next(parent.data.data)
                },
                err => {
                    error(err)
                }
            )
    }

    getChildren(parentPhone,next,error){
        databaseService.get(`/momo/parent/${parentPhone}/children`)
            .then(
                children => {
                    next(children.data.data)
                },
                err => {
                    error(err)
                }
            )
    }
}

const database = new Database()
exports = module.exports = database