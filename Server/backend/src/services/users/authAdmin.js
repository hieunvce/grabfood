const errors = require('@feathersjs/errors');
const adminEmail = 'admin@gmail.com';

exports.authenticateAdmin = function(context) {
  context.params.query.email = context.params.query.email || {};
  //console.log("adminAuth: "+JSON.stringify(context.params.query.email));
  let email = context.params.query.email;
  if ( email == adminEmail) {
    return Promise.resolve(context);
  } else {
    return Promise.reject(new errors[501]('Fail authentication'));
  }
};