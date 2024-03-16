document.addEventListener("DOMContentLoaded", function() {
    var form = document.getElementById('form-rejestracja');

    form.addEventListener('submit', function(event) {
        var haslo = document.getElementById('haslo').value;
        var potwierdzHaslo = document.getElementById('potwierdzHaslo').value;
        var errorMessage = document.getElementById('error-message');
        var hasloField = document.getElementById('haslo');
        var potwierdzHasloField = document.getElementById('potwierdzHaslo');

        if (haslo !== potwierdzHaslo) {
            event.preventDefault(); // Zapobiegaj wysłaniu formularza
            errorMessage.style.display = 'block'; // Pokaż komunikat o błędzie
            potwierdzHasloField.classList.add('is-invalid'); // Dodaj klasę dla niewłaściwego pola
        } else {
            errorMessage.style.display = 'none'; // Ukryj komunikat o błędzie
            potwierdzHasloField.classList.remove('is-invalid'); // Usuń klasę dla niewłaściwego pola
        }
    });
});
