const mongoose = require("mongoose");


var sd = require('silly-datetime');

function findTransactionByUserId(patronId, callback) {
    callback(transactionRecords);
}

function addTransaction(transaction, callback) {
    transaction.createDate = sd.format(new Date(), 'YYYY-MM-DD HH:mm:ss');
      callback(transactionRecords);
}

function deleteTransactionById(id, callback) {
        callback(transactionRecords);
}

function findAllTransactions(callback) {
        callback(list);
}
