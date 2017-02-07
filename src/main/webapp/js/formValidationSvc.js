app.service('formValidationSvc', function() {
  this.containsNumber = function(str) {
    var renum = /[0-9]+/;
    return renum.test(str);
  }

  this.containsSpecial = function(str) {
    var respecial = /[-=\[\]\\\;\,\.\/~!@#\$%\^&\*()\_\+{}\|:<>\?]+/;
    return respecial.test(str);
  }

  this.validatePhoneFormat = function(str) {
  	var rephone = /[0-9]{10}/;
  	return rephone.text(str);
  }

  this.validatePasswordFormat = function(firstPassword, secondPassword) {
    var result = {pass: true, err: ""};
    if(firstPassword !== secondPassword){
      result.pass = false;
      result.err = "Passwords not equal";
    } else if(firstPassword.length < 8){
      result.pass = false;
      result.err = "Password must be at least eight characters!";
    } else if(firstPassword.length > 75){
      result.pass = false;
      result.err = "Password must be at most seventy-five characters!";
    } else if(firstPassword === firstPassword.toLowerCase()){
      result.pass = false;
      result.err = "Password must contain at least one uppercase letter!";
    } else if(firstPassword === firstPassword.toUpperCase()){
      result.pass = false;
      result.err = "Password must contain at least one lowercase letter!";
    } else if(!this.containsNumber(firstPassword)){
      result.pass = false;
      result.err = "Password must contain at least one number!";
    } else if(!this.containsSpecial(firstPassword)){
      result.pass = false;
      result.err = "Password must contain at least one special character!"
    }
    return result;
  }
});
