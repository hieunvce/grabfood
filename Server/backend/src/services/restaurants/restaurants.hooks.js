const {
  authenticate
} = require('@feathersjs/authentication').hooks;
const authAdmin = require('./authAdmin');

module.exports = {
  before: {
    all: [],
    find: [],
    get: [],
    create: [
      authenticate('jwt'),
      context => {
        authAdmin.authenticateAdmin(context);
      }
    ],
    update: [authenticate('jwt'),
      context => {
        authAdmin.authenticateAdmin(context);
      }
    ],
    patch: [authenticate('jwt'),
      context => {
        authAdmin.authenticateAdmin(context);
      }
    ],
    remove: [authenticate('jwt'),
      context => {
        authAdmin.authenticateAdmin(context);
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
