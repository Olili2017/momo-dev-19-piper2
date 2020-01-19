const express = require('express')
const routes = require('./src/routes')

const app = express()
const port = 8902

app.use(express.json())

app.use((req, res, next) => {
    console.log('\x1B[35m', `[piper2-momo-gateway] ${req.method} - ${req.path}` );
    next()
})

routes.register(app)

app.listen(port, '192.168.42.130', console.log('\x1B[35m',`[piper2-momo-gateway] Listening on port ${port}`))