/**
 * 
 */
function button(args) {
	var div = ['parcEtu', 'addEtu', 'suprEtu', 'modInfo'];
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