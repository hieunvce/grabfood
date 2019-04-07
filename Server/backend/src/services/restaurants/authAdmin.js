const errors = require('@feathersjs/errors');

exports.authenticateAdmin = function(context) {
  context.params.payload = context.params.payload || {};
  if (context.params.user.role == 'c2VjcmV0Y29kZQo=') {
    return Promise.resolve(context);
  } else {
    return Promise.reject(new errors[501]('Fail authentication'));
  }
};