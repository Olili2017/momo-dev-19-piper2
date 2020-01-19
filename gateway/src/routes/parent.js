const momoClient = require('../api/MomoClient')
const database = require('../api/database')

const register = (app) => {

    // parent : initiate send money to student
    app.post("/parent/deposit/initiate", (req, res) => {
        // TODO: send money to student account
        momoClient.initiateCollection(
            {
                amount : req.body.amount,
                fromMMUserNumber : req.body.from
            }, response => {
                res.json({message: "Success! Please approve transaction.", reference : response})
            }, err => {
                res.json(err)
            }
            )
    })

    // parent : confirm deposit done
    app.put("/parent/deposit/confirm/", (req, res) => {

        momoClient.confirmCollection(
            req.body.referenceId,
            response => {
                switch(response.status){
                    case "SUCCESSFUL":
                        database.getParent(response.payer.partyId, parent => {
                            //console.log(`Saught is : ${response.payer.partyId}`, parent);

                            if (parent.accountNumber !== undefined){

                                // TODO: Change parent account balance before deposit to student
                                database.depositToParent(parent.accountNumber, response.amount, (money) => {
                                        //console.log(money);

                                    database.depositToStudent(
                                        {
                                            amount : response.amount,
                                            from : parent.accountNumber,
                                            to : req.body.to
                                        },
                                        depositResponse => {
                                            res.json(depositResponse)
                                            console.log("deposited ", depositResponse)
                                        },
                                        err => {
                                            res.json(err)
                                        })
                                })

                            }else {
                                res.json({message : "unkown account"})
                                //console.log("unkown acc")
                            }

                        }, err => {
                            res.json(err)
                        })
                        break;
                    case "FAILED":
                            break
                    case "PENDING":
                            break
                    default:
                        break
                }
            },
            err => {
                res.json(err)
            }
            )
    })

    // parent : get previous transaction
    app.get("/parent/{phone}/transactions", (req, res) => {
        // TODO: get parent previous transaction

    })

    // parent : get account balace
    app.get("/parent/{phone}/balance", (req, res) => {
        // momoClient.checkAccountBalance("disbursement", balance => {

        // },
        // error => {

        // })

    })

    // parent : view money requests
    app.get("/parent/requests", (req,res) => {
        // TODO: view money request from students
    })

    // parent : signup
    app.post("/parent/register", (req, res) => {
        database.createParent(req.body, response => {
            res.json(response)
        }, err => {
            res.json(err)
        })
    })

    // parent : login
    app.post("/parent/login", (req, res) => {
        // TODO: login parent
    })

    // parent: add child to parent
    app.post("/parent/child/add", (req, res) => {
        database.createChild(req.body, response => {
            res.json(response)
        }, err => {
            res.json(err)
        })
    })

    app.get("/parent/:phone/children", (req,res) => {
        database.getChildren(req.params.phone, response => {
            console.log(response)
            res.json(response)
        }, err => {
            res.json(err)
        })
    })
}

exports = module.exports = {
    register
}