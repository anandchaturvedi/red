function sendJSON(){
      
			fetch('http://localtest.com:8080/action', {
			method: 'POST',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json',
				'Access-Control-Allow-Origin': '*',
				'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS'
				
			},
			body: JSON.stringify({ "name": document.querySelector('#name').value})
			})
			.then(response => response.json())
			.then(response => console.log(JSON.stringify(response)))
 }