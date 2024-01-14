// Check local storage when the page loads
document.addEventListener('DOMContentLoaded', (event) => {
    if (localStorage.getItem('darkMode') === 'true') {
        document.body.classList.add('dark-mode');
    }
    if (localStorage.getItem('sliderPosition') === 'true') {
        document.querySelector('input[type="checkbox"]').checked = true;
    }
});

window.changeTheme = function() {
    let body = document.body;
    body.classList.toggle('dark-mode');
    localStorage.setItem('darkMode', body.classList.contains('dark-mode').toString());

    let slider = document.querySelector('input[type="checkbox"]');
    localStorage.setItem('sliderPosition', slider.checked.toString());
}
