const mongoose = require("mongoose");

const usersSchema = {
  name: String,
  id: String,
  phone: String
};

const User = mongoose.model("User", usersSchema);

const user1 = new User({
  "phone" : "293-711-9380",
  "name" : "Rizwan Joyce",
  "id" : "2"
});

const user2 = new User({
  "phone" : "325-339-1296",
  "name" : "Khalil Kirkpatrick",
  "id" : "3"
});

const user3 = new User({
  "phone" : "159-877-8682",
  "name" : "Beatriz Zhang",
  "id" : "4"
});

const defaultItems = [user1, user2, user3];

const listSchema = {
  name: String,
  items: [usersSchema]
};

const List1 = mongoose.model("List1", listSchema);

function findAllUsers(callback) {
  callback(userList);
}
