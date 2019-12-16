const axios = require('axios')
const uuidClient = require('./UuidClient.js')
const momoApi = axios.create({
    baseURL: 'https://sandbox.momodeveloper.mtn.com'
});

const user = {
    disbursements : {
        uuid : "970b0f7e-6d45-43e1-a766-ff283b22c5ff",
        apikey : "09b3d75b593547e0a4abaf1b49712610",
        primaryKey : "5b25e89d81e242c1966b5c701c662546",
        env : "sandbox"
    },
    collections : {
        uuid : "970b0f7e-6d45-43e1-a766-ff283b22c5ff",
        apikey : "09b3d75b593547e0a4abaf1b49712610",
        primaryKey : "1c99293d2b024a199c18ec3f9b00e159",
        env : "sandbox"
    },
    account : {
        msisdn : "256772649119"
    }
}

class MomoClient {

    // type can be disbursement for sending or collection for recieving
    initiateCollection(payload, done, error){
        const { amount, fromMMUserNumber } = payload

        const referenceId = Math.ceil(Math.random() * 999999).toString();

        let primaryKey = user.collections.primaryKey
        let env = user.collections.env


        uuidClient.getUUID(uuid => {

            this.getBearerToken("collection", token => {

                momoApi.post(
                    `/collection/v1_0/requesttopay`,
                    {
                        amount: amount,
                        currency: "EUR",
                        externalId: referenceId,
                        payer: {
                            partyIdType: "MSISDN",
                            partyId: fromMMUserNumber
                        },
                        payerMessage: "payer message",
                        payeeNote: "payee note"
                    },
                    {
                        headers : {
                            'X-Target-Environment' : env,
                            'Ocp-Apim-Subscription-Key' : primaryKey,
                            'X-Reference-Id' : uuid,
                            "Authorization" : `Bearer ${token}`
                        }
                    })
                    .then(
                        res => {
                            // no response
                            done(uuid)
                        },
                        err => {
                            error(err)
                        }
                )
            })
        })

    }

    confirmCollection (referenceId, done, error){


        this.getBearerToken("collection", token => {

            momoApi.get(
                `/collection/v1_0/requesttopay/${referenceId}`,
                {
                    headers : {
                        "X-Target-Environment" : user.collections.env,
                        "Ocp-Apim-Subscription-Key" : user.collections.primaryKey,
                        "Authorization" : `Bearer ${token}`
                    }
                })
                .then(confirmation => {
                    done(confirmation.data)
                }, err => {
                    error(err)
                })
        })

    }

    initiateDisburse(payload, done, error){
        const { amount, toMMUserNumber } = payload
        const referenceId = Math.ceil(Math.random() * 999999).toString();


        let primaryKey = user.disbursements.primaryKey
        let env = user.disbursements.env

        uuidClient.getUUID(uuid => {

            this.getBearerToken("disbursement", token => {

                momoApi.post(
                    `/disbursement/v1_0/transfer`,
                    {
                        amount: amount,
                        currency: "EUR",
                        externalId: referenceId,
                        payee: {
                        partyIdType: "MSISDN",
                        partyId: toMMUserNumber
                        },
                        payerMessage: "payer message",
                        payeeNote: "payee note"
                    },
                    {
                        headers : {
                            'X-Target-Environment' : env,
                            'Ocp-Apim-Subscription-Key' : primaryKey,
                            'X-Reference-Id' : uuid,
                            "Authorization" : `Bearer ${token}`
                        }
                    })
                    .then(
                        res => {
                            // no response
                            done(uuid)
                        },
                        err => {
                            error(err)
                        }
                )
            })
        })

    }

    confirmDisbursement (referenceId, done, error){


        this.getBearerToken("collection", token => {

            momoApi.get(
                `/disbursement/v1_0/transfer/${referenceId}`,
                {
                    headers : {
                        "X-Target-Environment" : user.disbursements.env,
                        "Ocp-Apim-Subscription-Key" : user.disbursements.primaryKey,
                        "Authorization" : `Bearer ${token}`
                    }
                })
                .then(confirmation => {
                    done(confirmation.data)
                }, err => {
                    error(err)
                })
        })

    }

    checkAccountBalance(type, next, error){

        this.getBearerToken(type, token => {


            let env, primaryKey

            if (type === "disbursement"){
                primaryKey = user.disbursements.primaryKey
                env = user.disbursements.env
            } else {
                primaryKey = user.collections.primaryKey
                env = user.collections.env
            }

            momoApi.get(`/${type}/v1_0/account/balance`,
                {
                    headers: {
                        "X-Target-Environment" : env,
                        "Ocp-Apim-Subscription-Key" : primaryKey,
                        "Authorization" : `Bearer ${token}`
                    }
                })
                .then(balance => {
                    next(balance.data);
                }, err => {
                    error(err);
                })
         })
    }

    // type can be disbursement for sending or collections for recieving
    getBearerToken(type, next, error){

        let userID, primaryKey, apiKey

        if (type === "disbursement"){
            userID = user.disbursements.uuid
            apiKey = user.disbursements.apikey
            primaryKey = user.disbursements.primaryKey
        } else {
            userID = user.collections.uuid
            apiKey = user.collections.apikey
            primaryKey = user.collections.primaryKey
        }

        momoApi.post(`/${type}/token/`,
            {},
            {
                headers: {
                    "Ocp-Apim-Subscription-Key" : primaryKey,
                    "Authorization" : `Basic ${Buffer.from(userID + ':' + apiKey).toString('base64')}`
                }
            })
            .then(response => {
                next(response.data.access_token)
            }, err => {
                error(err)
            })
    }

}

const momoClient = new MomoClient()
exports = module.exports = momoClient