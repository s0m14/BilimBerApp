document.addEventListener("DOMContentLoaded", function () {
  
  const form = document.getElementById("signup-form");
  const message = document.getElementById("message");

  form.addEventListener("submit", async function (event) {
    event.preventDefault();

    const username = document.getElementById("username").value.trim();
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirm-password").value;


    if (password !== confirmPassword) {
      showMessage("Passwords do not match!", "error");
      return;
    }

    try {
      const response = await fetch("http://localhost:8080/auth/sign-up", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, email, password }),
      });

      if (response.ok) {
        showMessage("Registration successful!", "success");
        console.log("ok");
        window.location.href = "verification.html";
      } else {
        console.log("Registration failed");

        showMessage("Registration failed. Try again.", "error");
      }
    } catch (error) {
      showMessage("Server error. Try later.", "error");
    }
  });

  function showMessage(text, type) {
    message.textContent = text;
    message.className = `message ${type}`;
  }
});
