// Functions That will handle sessions in local storage
function removeHtmlStorage(name) {
    localStorage.removeItem(name);
    localStorage.removeItem(name+'_time');
}

function setHtmlStorage(name, value, expires) {

  var date;
  var schedule;

  if (expires===undefined || expires==='null') {
    var expires = 3600; // default: 1h if Not Defined
    date = new Date();
    schedule = Math.round((date.setSeconds(date.getSeconds()+expires))/1000);
  } else {
    date = new Date();
    schedule = Math.round((date.setSeconds(date.getSeconds()+expires))/1000);
  }


  localStorage.setItem(name, value);
  localStorage.setItem(name+'_time', schedule);
}

function statusHtmlStorage(name) {

  var date = new Date();
  var current = Math.round(+date/1000);

  // Get Schedule
  var stored_time = localStorage.getItem(name+'_time');

  if (stored_time===undefined || stored_time==='null') {
    var stored_time = 0;

    // Expired
    if (stored_time < current) {
        // Remove
        removeHtmlStorage(name);
        return 0;
    } else {
        return 1;
    }
  } else {
    // Expired
    if (stored_time < current) {
        // Remove
        removeHtmlStorage(name);
        return 0;
    } else {
        return 1;
    }
  }
  // shouldn't be reachable, but this is JavaScript so who knows
  return 0;
}
/*
// Status
var cache_status = statusHtmlStorage('cache_name');

// Has Data
if (cache_status == 1) {

    // Get Cache
    var data = localStorage.getItem('cache_name');
    alert(data);

// Expired or Empty Cache
} else {

    // Get Data
    var data = 'Pay in cash :)';
    alert(data);

    // Set Cache (30 seconds)
    if (cache) { setHtmlStorage('cache_name', data, 30); }

}*/
