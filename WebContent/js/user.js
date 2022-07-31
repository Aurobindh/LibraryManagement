"use strict"
export default class User {
    get username() {
        return this.name;
    }
    set username(name) {
        this.name = name;
    }
    get dob() {
        return this.date;
    }
    set dob(dob) {
        this.date = dob;
    }
    get mail() {
        return this.email;
    }
    set mail(mail) {
        this.email = mail;
    }
    get address() {
        return this.addr;
    }
    set address(val) {
        this.addr = val;
    }
    get phone() {
        return this.num;
    }
    set phone(phone) {
        this.num = phone;
    }
}


