document.addEventListener('DOMContentLoaded', () => {
    const commentForm = document.querySelector("#commentForm");
    const commentsContainer = document.querySelector("#commentsContainer");
    const commentsCountEl = document.querySelector("#commentsCount");

    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute("content");
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute("content");

    commentForm.addEventListener("submit", (e) => {
        e.preventDefault();

        const formData = new FormData(commentForm);
        const data = {};
        formData.forEach((value, key) => data[key] = value);

        fetch("/publication/comment/", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                [csrfHeader]: csrfToken
            },
            body: JSON.stringify(data)
        })
            .then(res => res.json())
            .then(res => {

                const div = document.createElement("div");
                div.className = "comment-item mb-2 d-flex justify-content-between align-items-start";
                div.innerHTML = `
                <div class="small text-dark">
                    <strong>${res.comment.ownerLogin}</strong> ${res.comment.commentText}
                    <div class="small text-dark">${res.comment.createdDate}</div>
                </div>
                ${res.comment.isOwner ? `
                    <button type="button" 
                            data-id="${res.comment.id}" 
                            data-publication="${res.comment.publicationId}" 
                            class="btn btn-sm btn-outline-danger delete-comment-btn">
                        <i class="bi bi-trash"></i>
                    </button>` : ''}
            `;

                commentsContainer.appendChild(div);
                commentsCountEl.textContent = res.commentsCount;
                commentForm.reset();
            });
    });

    commentsContainer.addEventListener("click", (e) => {
        const btn = e.target.closest(".delete-comment-btn");
        if(!btn) return;

        const commentId = btn.dataset.id;
        const publicationId = btn.dataset.publication;

        fetch(`/publication/comment/delete/${commentId}?publicationId=${publicationId}`, {
            method: "POST",
            headers: { [csrfHeader]: csrfToken }
        })
            .then(res => res.json())
            .then(res => {
                btn.closest(".comment-item").remove();
                commentsCountEl.textContent = res.commentsCount;
            });
    });
});