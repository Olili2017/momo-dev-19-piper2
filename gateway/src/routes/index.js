const parentRoutes = require('./parent')
const studentRoutes = require('./student')

const register = (app) => {

    // app.get('/test', (req,res) => {
    //     // momoClient.checkAccountBalance("disbursement",thru => { console.log(thru) }, err => { console.log(err)})
    //     // momoClient.initiateFundTransfer("collection",{ amount : "2000"}, () => { console.log("done here")},err => { console.log(err)})
    //     // momoClient.initiateDisburse({ amount : "20.0"}, () => { console.log("done here")},err => { console.log(err)})
    //     momoClient.initiateDisburse(
    //         { amount : "20.0", toMMUserNumber : "256772649119"},
    //         (ref) => { momoClient.confirmDisbursement(ref,
    //             (conf) => {
    //                 // console.log(conf)
    //                 res.json(conf) }, err => { console.log(err) }) },err => { console.log(err)})

    //     // res.json("done")
    // })

    parentRoutes.register(app)
    studentRoutes.register(app)

    // agent : signup
    // agent : login
    // agent : withraw
    // agent : deposit
}

exports = module.exports = { register }