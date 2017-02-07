// Functions That will handle sessions in local storage 
function removeHtmlStorage(name) {
    localStorage.removeItem(name);
    localStorage.removeItem(name+'_time');
}

function setHtmlStorage(name, value, expires) {

    if (expires===undefined || expires===null) { 
    	expires = 3600; 
    } // default: 1h if Not Defined

    var date = new Date();
    var schedule = Math.round((date.setSeconds(date.getSeconds()+expires))/1000);

    localStorage.setItem(name, value);
    localStorage.setItem(name+'_time', schedule);
}

function statusHtmlStorage(name) {

    var date = new Date();
    var current = Math.round(+date/1000);

    // Get Schedule
    var stored_time = localStorage.getItem(name+'_time');
    if (stored_time===undefined || stored_time===null) { 
    	stored_time = 0;
    }

    // Expired
    if (stored_time < current) {

        // Remove
        removeHtmlStorage(name);

        return 0;

    } else {

        return 1;
    }
}