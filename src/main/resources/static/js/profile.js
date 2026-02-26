window.addEventListener('load', function () {

    const button = document.getElementById("subscribeBtn");
    if (!button) return;

    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute("content");
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute("content");

    button.addEventListener("click", function () {

        fetch("/users/subscribe", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
                [csrfHeader]: csrfToken
            },
            body: "subscriberEmail=" + button.dataset.email
        })
            .then(response => response.json())
            .then(data => {

                button.textContent = data.subscribed ? "Подписки" : "Подписаться";

                document.querySelector(".followers-count").textContent = data.followers;
            });
    });
});