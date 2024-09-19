document.getElementById("form").addEventListener("submit", function(event) {
    event.preventDefault();

    // Get the values of the email and password fields
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    // Reset error messages
    document.getElementById("emailError").textContent = "";
    document.getElementById("passwordError").textContent = "";

    // Variables to track errors
    let isValid = true;

    // Email check
    if (!validateEmail(email)) {
        document.getElementById("emailError").textContent = "Inserisci un'email valida.";
        isValid = false;
    }

    // Password check
    if (!validatePassword(password)) {
        document.getElementById("passwordError").textContent = "La password deve essere di almeno 8 caratteri e contenere almeno una lettera maiuscola, una minuscola e un numero.";
        isValid = false;
    }

    // If all thhe parameters are ok, the app can proceed
    if (isValid) {
        alert("Login riuscito!");
        event.target.submit();
    }
});

function validateEmail(email) {
    // RegExp per validare un'email
    const re = /^[\w\-\.]+@([\w-]+\.)+[\w-]{2,}$/gm;
    return re.test(email);
}

function validatePassword(password) {
    const pattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+[\]{};':"\\|,.<>/?]).{8,}$/;
    return pattern.test(password);
}
