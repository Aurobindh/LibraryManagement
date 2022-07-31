"use strict"
export default class Book {
    get bookname() {
        return this.book;
    }
    set bookname(val) {
        this.book = val;
    }
    get authorname() {
        return this.author;
    }
    set authorname(authorname) {
        this.author = authorname;
    }
    get bookcount() {
        return this.count;
    }
    set bookcount(bookcount) {
        this.count = bookcount;
    }
}