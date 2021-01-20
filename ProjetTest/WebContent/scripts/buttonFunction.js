/**
 * 
 */
function button(args) {
	var div = ['parcEtu', 'parcNote', 'parcMatiere', 'parcUser', 'addEtu', 'suprEtu', 'connect', 'addNote', 'suprNote', 'addMatiere', 'suprMatiere', 'newUser'];
	for(const element of div) {
		var x = document.getElementById(element);
		if(element == args) {
			x.style.display="block";
		}
		else {
			x.style.display="none";
		}
	}
	x = document.getElementById("acceuil");
	x.style.display="none";
}