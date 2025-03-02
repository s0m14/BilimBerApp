document
  .getElementById("verification-form")
  .addEventListener("submit", async function (event) {
    event.preventDefault();

    const email = document.getElementById("email").value.trim();
    const code = document.getElementById("verification-code").value.trim();

    try {
      const response = await fetch("http://localhost:8080/auth/verify", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, code }),
      });

      if (!response.ok) {
        throw new Error("error");
      } 

      const data = await response.json();

      localStorage.setItem("token", data.token);

      console.log("awsd");

      window.location.href = "profile.html";
    } catch (error) {
      console.log(error);
    }
  });
