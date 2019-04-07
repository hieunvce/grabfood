// Initializes the `restaurants` service on path `/restaurants`
const createService = require('feathers-mongodb');
const hooks = require('./restaurants.hooks');

module.exports = function (app) {
  const paginate = app.get('paginate');
  const mongoClient = app.get('mongoClient');
  const options = { paginate };

  // Initialize our service with any options it requires
  app.use('/restaurants', createService(options));

  // Get our initialized service so that we can register hooks and filters
  const service = app.service('restaurants');

  mongoClient.then(db => {
    service.Model = db.collection('restaurants');
  });

  service.hooks(hooks);
};
