"use strict"

//import's from other javascript files
import User from './user.js';
import Book from './book.js';

//Load function resets the hiding properties in the index.jsp
function load() {
	let mainpage = document.getElementsByClassName("mainpage")[0];
	let innermainpage = mainpage.getElementsByTagName("div");
	for(let i=0; i<innermainpage.length; i++) {
		innermainpage[i].style.display = 'none';
	}
}

//While loading the index.jsp reset's the hiding properties
window.onload = load;

//All Onclik Events triggerd in the Sidepane handled here The clicked option alone shown in the display properties
document.getElementById("addbok").onclick = function(event) {
	load();
	document.getElementsByClassName('addbok')[0].style.display = 'block';
}

document.getElementById("addusr").onclick = function(event) {
	load();
	document.getElementsByClassName('addusr')[0].style.display = 'block';
}

document.getElementById("alctbook").onclick = function(event) {
	load();
	document.getElementsByClassName('alctbook')[0].style.display = 'block';
}

document.getElementById("bookdta").onclick = function(event) {
	load();
	document.getElementsByClassName('bookdta')[0].style.display = 'block';
}

document.getElementById("userdta").onclick = function(event) {
	load();
	document.getElementsByClassName('userdta')[0].style.display = 'block';
}

document.getElementById("allocationdta").onclick = function(event) {
	load();
	document.getElementsByClassName('allocationdta')[0].style.display = 'block';
}

// Add Book Form submit event trigger's here
document.getElementById("addbook").onsubmit = function() {
	
	let book = new Book();

	book.bookname = document.getElementsByName("bookname")[0].value;
	book.authorname = document.getElementsByName("authorname")[0].value;
	book.bookcount = document.getElementsByName("bookcnt")[0].value;

	if(notnull(book.bookname,book.authorname,book.bookcount)) {
		
		if(isLetter(book.bookname,book.authorname) && TryParseInt(book.bookcount,0) >= 1) {
			return true;
		}
		else {
			alert("Fill the fields correctly");
			return false;
		}
	}	
	else {

		alert("fill all the fields");
		return false;
	}
}

//Add User Form submit event trigger's here
document.getElementById("adduser").onsubmit = function() {
	
	let user = new User();

	user.username = document.getElementsByName("username")[0].value;
	user.dob = document.getElementsByName("dob")[0].value;
	user.mail = document.getElementsByName("email")[0].value;
	user.address = document.getElementsByName("address")[0].value;
	user.phone = document.getElementsByName("phone")[0].value;

	if(notnull(user.username,user.dob,user.mail,user.address,user.phone)) {
		
		if(isLetter(user.username)) {
			return true;
		}
		else {
			alert("Fill the fields correctly");
			return false;
		}
	}	
	else {

		alert("fill all the fields");
		return false;
	}
}

// Functions used for validation's
function notnull(...params) {
	for(let param of params) {
		if(param == '') return false;
	}
	return true;
}

function isLetter(...strs) {
	for(let str of strs) {
		if(!(str.length > 1 && str.match(/^[a-zA-Z\s]*$/))) return false;
	}
	return true;
}

function TryParseInt(str,defaultValue) {
	var retValue = defaultValue;
	if(str !== null) {
		if(str.length > 0) {
			if (!isNaN(str)) {
				retValue = parseInt(str);
			}
		}
	}
	return retValue;
}
