function AjaxData(ajaxUrl, method){
	this.url = ajaxUrl;
	this.method = method;
	var broughtData;

	AjaxData.prototype.set = function(){
		// $.ajax({
		// 	type: type,
		// 	url: url,
		// 	success: function(data) {
		// 		return data;
		// 	}
		// });
		var request = new XMLHttpRequest();
		request.open(this.method, this.url, true);
		request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
		
		request.onload = function() {
	  		if (request.status >= 200 && request.status < 400) {
		    	// Success
		    	broughtData = request.responseText;
		  	} else {
		    	// We reached our target server, but it returned an error
		  	}
		}	

		request.onerror = function() {
	  		// There was a connection error of some sort
		};
		request.send(data);
	}

	AjaxData.prototype.get = function() {
		return broughtData;
	}

}