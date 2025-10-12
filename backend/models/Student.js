const mongoose = require("mongoose");

const studentSchema = new mongoose.Schema({
  name: String,
  email: String,
  branch: String,
  gender: String,
  skills: [String],
  address: String,
  phone: String,
});

module.exports = mongoose.model("Student", studentSchema);
