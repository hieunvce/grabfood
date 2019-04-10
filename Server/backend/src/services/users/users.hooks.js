const {
  authenticate
} = require('@feathersjs/authentication').hooks;

const {
  hashPassword,
  protect
} = require('@feathersjs/authentication-local').hooks;

module.exports = {
  before: {
    all: [],
    find: [authenticate('jwt'),
      context => {
        if (typeof (context.params.user) != undefined && context.params.user) {
          if (context.params.user.role == 'c2VjcmV0Y29kZQo=') {
            // eslint-disable-next-line no-console
            console.log('Admin got users');
          } else {
            context.params.query.email = String(context.params.user.email);
          }
        }
      }
    ],
    get: [authenticate('jwt')],
    create: [hashPassword()],
    update: [hashPassword(), authenticate('jwt')],
    patch: [hashPassword(), authenticate('jwt')],
    remove: [authenticate('jwt')]
  },

  after: {
    all: [
      // Make sure the password field is never sent to the client
      // Always must be the last hook
      protect('password')
    ],
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
