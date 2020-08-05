const mongoose = require("mongoose");

const booksSchema = {
  author: String,
  ISBN: String,
  price: String,
  returned: Boolean,
  title: String,
};

const Book = mongoose.model("Book", booksSchema);

const book1 = new Book({
"author":"Rizwan Joyce",
"iSBN":"978-2-294-75916-1",
"price":"0.11",
"returned":true,
"title":"Guide Pratique de la Consultation en Pédiatrie (Onzième Édition)"
});

const book2 = new Book({
  "author":"Khalil Kirkpatrick",
  "iSBN":"978-0-12-815485-4",
  "price":"67.22",
  "returned":true,
  "title":"Free-Surface Flow"
});

const book3 = new Book({
  "author":"Beatriz Zhang",
  "iSBN":"978-0-12-813166-4",
  "price":"14.90",
  "returned":false,
  "title":"Medicine Price Surveys, Analyses and Comparisons"
});

const defaultItems = [book1, book2, book3];

const listSchema = {
  name: String,
  items: [booksSchema]
};

const List2 = mongoose.model("List2", listSchema);

function findAllBook(callback) {
  callback(bookList);
}

function findBookByISBN(ISBN,callback) {
  callback(books);
}

function addBook(book,callback) {
  callback(book);

}

function updateBook(id,book,callback) {
  callback(book);

}

function deleteBookById(id,callback) {
  console.log("deleted" + book);


}
