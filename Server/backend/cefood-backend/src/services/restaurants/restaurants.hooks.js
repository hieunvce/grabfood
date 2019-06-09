const { authenticate } = require('@feathersjs/authentication').hooks;

const adminAccessOnlyFilter = async context => {
  if (typeof (context.params.user.role) != undefined && context.params.user.role == 'admin') { 
  } else {
    error = new Error('Access denied.');
    throw error;
  }
}
const returnAllRecords = async context => {
  context.params.paginate=false;
}
module.exports = {
  before: {
    all: [ ],
    find: [returnAllRecords],
    get: [],
    create: [authenticate('jwt'),adminAccessOnlyFilter],
    update: [authenticate('jwt'),adminAccessOnlyFilter],
    patch: [authenticate('jwt'),adminAccessOnlyFilter],
    remove: [authenticate('jwt'),adminAccessOnlyFilter]
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
