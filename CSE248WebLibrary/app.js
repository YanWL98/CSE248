
const express = require('express');
const bodyParser = require('body-parser');
const mongoose = require("mongoose");
const _ = require("lodash");

var bookService = require('./service/bookService.js');
var userService = require('./service/userService.js');
var transactionService = require('./service/transactionService.js');
var reservedService = require('./service/reservedService.js');


const app = express();

app.set('view engine', 'html');
app.use("/resources", express.static(__dirname + "/resources"));
app.use(bodyParser.urlencoded({extended: true}));
app.engine('html', require('ejs').renderFile);

mongoose.connect("mongodb://localhost:27017/myDB", {useNewUrlParser: true});


app.get('/', function (req, res) {
    res.render("login.html");
});

app.get('/login', function (req, res) {
    let username = req.query.username;
    let password = req.query.password;
    if (null != username && null != password) {
        userService.findAllUsers(function (list) {
            list.forEach(function (users) {
                if (val.name == username && (val.name + val.id) == password) {
                    let user = {
                        phone: val.phone,
                        name: val.name,
                        id: val.id
                    }
                    res.redirect("/book_list");
                } else {
                  console.log("failed");
                }
            })
        });
    } else {
        res.render('login.html', {message: 'failed'});
    }
})


app.get("/book_list", function (req, res) {
    bookService.findAllBook(function (list) {
        res.render("book_list.html", {
            bookList: list
        });
    });
});



app.get("/deleteBook/:_id", function (req, res) {
    let _id = req.params._id;
    bookService.deleteBookById(_id, function () {
            res.redirect("/book_list");
    });
});


app.get("/book_add", function (req, res) {
    res.render("book_add.html");
});


app.get("/addBook", function (req, res) {
    let title = req.query.title;
    let author = req.query.author;
    let ISBN = req.query.ISBN;
    let price = req.query.price;
    let book = {
        title: title,
        author: author,
        ISBN: ISBN,
        price: price,
        returned: true
    };
    bookService.addBook(book, function () {
            res.redirect("/book_list");
    })
});


app.get("/users", function (req, res) {
    userService.findAllUsers(function (list) {
        res.render("user_list.html", {
            userList: list
        });
    })
});


app.get("/transcations/:userId", function (req, res) {
    let id = req.params.userId;
    transactionService.findTransactionByUserId(function (list) {
        res.render("user_borrow_list.html", {
            borrowList: list
        });
    })
});


app.get("/booking/:iSBN", function (req, res) {
    let ISBN = req.params.iSBN;
    let patronId = req.params.id;
    reservedService.findAllReserveds(function (list) {
      var reserID = 0;
        let reserved = {
            patronId: patronId,
            isbn: ISBN,
            id: Number(reserID + 1),
            createDate: new Date(),
            isReturn: true
        }
        reservedService.addReserved(reserved, function (n) {
            if (n > 0) {
              console.log("succeed");
            } else {
              console.log("failed");
            }
        })
    });
});


app.get("/borrow/:iSBN", function (req, res) {
    let ISBN = req.params.iSBN;
    let patronId = req.params.id;
    transactionService.findAllTransactions(function (list) {
        var reserID = 0;
        let reserved = {
            patronId: patronId,
            isbn: ISBN,
            id: Number(reserID + 1),
        }
        transactionService.addTransaction(reserved, function (n) {
            if (n > 0) {
                console.log("succeed");
            } else {
                console.log("failed");
            }
        })
    });
});


app.listen(4444);

console.log('nodejs server run at 4444');
