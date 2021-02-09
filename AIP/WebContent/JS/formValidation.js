function validate()
{
	var filter=/^[A-Za-z]+$/;
	var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	if(document.form.u_name.value>20&&!filter.test(document.form.u_name.value)){
		alert("Check your user name!!");
		
		return false;
	}
	if(document.form.u_pwd.value!=document.form.u_pwd1.value&&document.form.u_pwd.value>20){
		alert("Check your password!!");
		
	return false;
	}
	
	
	if(reg.test(document.form.u_email.value)){
		alert("Check your Email id");
		
		return false;
	}
	 var e = document.getElementById("country");
     var u_country = e.options[e.selectedIndex].value;
	if(u_country==0){
		console.log("Select atleast one option");
	}
	 var f = document.getElementById("city");
     var u_city = f.options[f.selectedIndex].value;
	if(u_city==0){
		console.log("Select atleast one option");
	}
	
}
