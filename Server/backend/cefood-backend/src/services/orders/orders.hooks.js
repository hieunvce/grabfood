const { authenticate } = require('@feathersjs/authentication').hooks;

const accessFilter = async context => {
  if (context.params.user != undefined && context.params.user) {
    context.data.userId = context.params.user._id;
  } else {
    error = new Error('Access denied.');
    throw error;
  }
}

const queryFilter = async context => {
  if (context.params.user != undefined && context.params.user) {
    if (context.params.user.role == "user") {
      context.params.query.userId = context.params.user._id;
    }
  } else {
    error = new Error('Access denied.');
    throw error;
  }
}

const returnAllRecords = async context => {
  context.params.paginate = false;
}

module.exports = {
  before: {
    all: [authenticate('jwt')],
    find: [queryFilter, returnAllRecords],
    get: [queryFilter],
    create: [accessFilter],
    update: [],
    patch: [accessFilter],
    remove: []
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
