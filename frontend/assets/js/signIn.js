document
  .getElementById("signin-form")
  .addEventListener("submit", async function (event) {

    event.preventDefault();

    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value;

    try {
      const response = await fetch("http://localhost:8080/auth/sign-in-mail", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password }),
      });

      if (!response.ok) {
        throw new Error("Error");
      }

      const data = await response.json();

      localStorage.setItem("token", data.token);

      window.location.href = "profile.html";
    } catch (error) {
      console.log(error);
      document.getElementById("message").innerText =
        "Error! check password or email";
    }
  });
