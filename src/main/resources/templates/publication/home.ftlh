<#import "../layout.ftlh" as home>
<@home.layout>

    <div class="container py-4">
        <div class="row justify-content-center">
            <div class="col-12 col-md-10 col-lg-8 col-xl-7">

                <#if publications?has_content>
                    <#list publications as publication>
                        <div class="card bg-white border-0 shadow-sm rounded-3 overflow-hidden mb-5">
                            
                            <div class="d-flex align-items-center justify-content-between px-3 py-3 border-bottom">
                                <div>
                                    <a href="/users/profile?email=${publication.owner}"
                                       class="fw-semibold text-dark text-decoration-none fs-5">
                                        ${publication.owner}
                                    </a>
                                </div>
                            </div>

                            <div class="bg-dark">
                                <a href="/publication/${publication.id}">
                                    <img src="/images/pub/${publication.photo}"
                                         alt="Publication photo by ${publication.owner}"
                                         class="w-100 h-100">
                                </a>
                            </div>

                            <div class="d-flex justify-content-between align-items-center px-3 py-2">
                                <div class="d-flex gap-3 fs-4">
                                    <button type="button"
                                            data-publication="${publication.id}"
                                            class="btn btn-link text-dark p-0 like-btn">
                                        <i class="bi
                                              <#if publication.likedByCurrentUser?? && publication.likedByCurrentUser>
                                                   bi-heart-fill fill
                                             <#else>
                                              bi-heart
                                             </#if>">
                                        </i>
                                    </button>

                                    <a href="/publication/${publication.id}"
                                       class="btn btn-link text-dark p-0 d-flex align-items-center gap-1"
                                       title="View comments">
                                        <i class="bi bi-chat"></i>
                                        <span class="small">
                                         ${publication.comments!0}
                                        </span>
                                    </a>

                                </div>
                            </div>

                            <div class="px-3 pb-1 text-dark">
                                <strong id="likes-count-${publication.id}">
                                    ${publication.likes!0}
                                </strong> likes
                            </div>

                            <div class="px-3 pb-2 text-dark">
                                <span class="fw-semibold">${publication.owner}</span>
                                ${publication.description!""}
                            </div>

                            <div class="px-3 pb-3 small text-muted">
                                ${publication.createdDate}
                            </div>

                        </div>
                    </#list>
                <#else>
                    <div class="text-center py-5">
                        <h3 class="text-secondary">Публикаций пока нет.</h3>
                        <p class="text-muted">Подпишитесь на кого-нибудь, чтобы увидеть их посты!</p>
                    </div>
                </#if>

            </div>
        </div>
    </div>


    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

    <style>
        .card {
            max-width: 700px;
            margin: 0 auto;
        }

        @media (max-width: 576px) {
            .card {
                border-radius: 0;
                margin-bottom: 20px;
            }
        }
    </style>

    <script src="/static/js/like.js"></script>
</@home.layout>