let loginForm = document.getElementById("loginForm");

loginForm.addEventListener("submit",
    (e) => {
    e.preventDefault();
    let userName = document.getElementById('username');
    let password = document.getElementById('password');
    fetch('http://localhost:8080/user/login?username=' + userName.value + '&password=' + password.value)
        .then(res => console.log(res));
    if(userName.value == null || password.value == null){

        console.log('please fill out both fields');
        window.alert('please fill out both fields');
    }else{
        console.log("username: " + userName.value + '\npassword: ' + password.value);
}
});