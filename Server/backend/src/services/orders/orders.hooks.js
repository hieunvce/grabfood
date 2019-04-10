const {
  authenticate
} = require('@feathersjs/authentication').hooks;
const errors = require('@feathersjs/errors');

module.exports = {
  before: {
    all: [authenticate('jwt')],
    find: [authenticate('jwt'),
      //context => {
      //  context.params.query.userId = String(context.params.user._id);
      //}
    ],
    get: [authenticate('jwt')],
    create: [authenticate('jwt'),
      context => {
        context.data.createdAt = new Date();
      }
    ],
    update: [
      // eslint-disable-next-line no-unused-vars
      context => {
        return Promise.reject(new errors[501]('Fail authentication'));
      }
    ],
    patch: [
      // eslint-disable-next-line no-unused-vars
      context => {
        return Promise.reject(new errors[501]('Fail authentication'));
      }
    ],
    remove: [
      // eslint-disable-next-line no-unused-vars
      context => {
        return Promise.reject(new errors[501]('Fail authentication'));
      }
    ]
  },

  after: {
    all: [],
    find: [],
    get: [],
    create: [],
    update: [],
    patch: [],
    remove: []
  },

  error: {
    all: [],
    find: [],
    get: [],
    create: [],
    update: [],
    patch: [],
    remove: []
  }
};
