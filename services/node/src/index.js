const route = require('koa-route');
const Koa = require('koa');
const app = new Koa();
const axios = require('axios');
const sleep = require('sleep-async')().Promise;

// set up a standard controller
const controller = async (ctx, service) => {
  let response;

  try {
    const headers = {};
    const url = `http://${service}/`;

    response = await axios.get(url, { headers });
  } catch (error) {
    return (ctx.body = {
      message: 'Wrapped by nodejs',
      response: `Error talking to service ${service}`,
    });
  }

  ctx.body = {
    message: 'Wrapped by nodejs',
    response: response.data,
  };
};

// set up the root controller to do some work
app.use(
  route.get('/', async (ctx) => {
    const sleepTime = Math.floor(Math.random() * 1000) + 0;
    const requestTime = Math.floor(Math.random() * 4) + 0;

    // do a random sleep
    try {
      if (sleepTime > 900) {
        const err = new Error('I cant wait that long!');
        throw err;
      }
      await sleep.sleep(sleepTime);
    } catch (error) {}

    // do a request to httpbin with a delay
    await axios.get(`https://httpbin.org/delay/${requestTime}`);

    ctx.body = {
      message: 'Hello from nodejs!',
      sleep: sleepTime,
      requestTime: requestTime,
    };
  })
);

// set up the routes
app.use(route.get('/:service', controller));

app.listen(80);
