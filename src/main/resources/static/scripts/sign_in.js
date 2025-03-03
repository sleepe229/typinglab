function signIn() {
    const login = document.getElementById('firstname').value;
    const password = document.getElementById('lastname').value;

    fetch('http://localhost:8080/api/typinglab/users/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ login: login, password: password })
    })
        .then(response => response.json())
        .catch((err) => {
            console.error('Error:', err);
        });
}