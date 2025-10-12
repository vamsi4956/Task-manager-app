import React, { useState } from "react";
import axios from "axios";

function StudentForm() {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    dob: "",
    gender: "",
    phone: "",
    branch: "",
    skills: [],
    address: "",
  });

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    if (type === "checkbox") {
      setFormData({
        ...formData,
        skills: checked
          ? [...formData.skills, value]
          : formData.skills.filter((skill) => skill !== value),
      });
    } else {
      setFormData({ ...formData, [name]: value });
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post("http://localhost:5000/api/students", formData);
      alert("Student registered successfully!");
      setFormData({
        name: "",
        email: "",
        dob: "",
        gender: "",
        phone: "",
        branch: "",
        skills: [],
        address: "",
      });
    } catch (err) {
      console.error(err);
      alert("Error registering student");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Student Registration</h2>
      <input
        type="text"
        name="name"
        placeholder="Full Name"
        value={formData.name}
        onChange={handleChange}
        required
      />
      <input
        type="email"
        name="email"
        placeholder="Email"
        value={formData.email}
        onChange={handleChange}
        required
      />
      <input
        type="date"
        name="dob"
        value={formData.dob}
        onChange={handleChange}
        required
      />
      <div>
        Gender:
        <input
          type="radio"
          name="gender"
          value="Male"
          checked={formData.gender === "Male"}
          onChange={handleChange}
          required
        /> Male
        <input
          type="radio"
          name="gender"
          value="Female"
          checked={formData.gender === "Female"}
          onChange={handleChange}
        /> Female
      </div>
      <input
        type="tel"
        name="phone"
        placeholder="10-digit number"
        value={formData.phone}
        onChange={handleChange}
      />
      <select name="branch" value={formData.branch} onChange={handleChange} required>
        <option value="">Select Branch</option>
        <option value="CSE">CSE</option>
        <option value="ECE">ECE</option>
        <option value="MECH">MECH</option>
      </select>
      <div>
        Skills:
        <input type="checkbox" name="skills" value="Java" onChange={handleChange} /> Java
        <input type="checkbox" name="skills" value="Python" onChange={handleChange} /> Python
        <input type="checkbox" name="skills" value="Web" onChange={handleChange} /> Web
      </div>
      <textarea
        name="address"
        placeholder="Address"
        value={formData.address}
        onChange={handleChange}
      />
      <button type="submit">Register</button>
    </form>
  );
}

export default StudentForm;
