document.getElementById('messageForm').addEventListener('submit', function(event) {
    event.preventDefault();
    let message = document.getElementById('message').value;
    fetch('/messages', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(message)
    })
    .then(response => response.json())
    .then(() => {
        document.getElementById('message').value = '';
        loadMessages();
    })
    .catch(error => console.error('Error:', error));
});

function loadMessages() {
    fetch('/messages/last10')
        .then(response => response.json())
        .then(messages => {
            let tableBody = document.getElementById('messagesTableBody');
            tableBody.innerHTML = '';  // Clear existing messages

            // Loop through messages and create table rows
            messages.forEach(message => {
                let tr = document.createElement('tr');
                let tdDate = document.createElement('td');
                let tdText = document.createElement('td');

                tdDate.textContent = new Date(message.date).toLocaleString();
                tdText.textContent = message.text;

                tr.appendChild(tdDate);
                tr.appendChild(tdText);
                tableBody.appendChild(tr);
            });
        })
        .catch(error => console.error('Error:', error));
}

loadMessages();