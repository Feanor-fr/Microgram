window.addEventListener("load", function () {

    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute("content");
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute("content");

    document.querySelectorAll(".like-btn").forEach(button => {

        button.addEventListener("click", function () {

            const publicationId = button.dataset.publication;
            const icon = button.querySelector("i");

            const isLiked = icon.classList.contains("bi-heart-fill");

            const url = isLiked
                ? "/publication/like/delete"
                : "/publication/like/";

            fetch(url, {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                    [csrfHeader]: csrfToken
                },
                body: "publicationId=" + publicationId
            })
                .then(response => response.json())
                .then(data => {

                    document.getElementById("likes-count-" + publicationId)
                        .textContent = data.likesCount;

                    if (isLiked) {
                        icon.classList.remove("bi-heart-fill", "fill");
                        icon.classList.add("bi-heart");
                    } else {
                        icon.classList.remove("bi-heart");
                        icon.classList.add("bi-heart-fill", "fill");
                    }
                });
        });
    });
});