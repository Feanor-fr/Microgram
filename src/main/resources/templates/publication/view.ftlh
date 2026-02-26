<#import "../layout.ftlh" as layout>

<@layout.layout>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-md-10 col-lg-8 col-xl-7">

                <div class="card bg-white border-0 shadow-sm rounded-3 overflow-hidden mb-4">

                    <div class="d-flex align-items-center justify-content-between px-3 py-3 border-bottom">
                        <div>
                            <a href="#"
                               class="fw-semibold text-dark text-decoration-none fs-5">
                                ${publication.owner}
                            </a>
                        </div>

                        <#if isOwner?? && isOwner>
                            <form action="/publication/delete/${publication.id}" method="post">
                                <#if _csrf??>
                                    <input type="hidden"
                                           name="${(_csrf.parameterName)!'csrf-param-name'}"
                                           value="${(_csrf.token)!'csrf-token'}">
                                </#if>

                                <button type="submit" class="btn btn-outline-danger btn-sm">
                                    <i class="bi bi-trash"></i> Удалить
                                </button>
                            </form>
                        </#if>

                    </div>

                    <div class="bg-dark">
                        <img src="/images/pub/${publication.photo}"
                             alt="Publication photo by ${publication.owner}"
                             class="w-100 h-100">
                    </div>

                    <div class="d-flex justify-content-between align-items-center px-3 py-2">
                        <div class="d-flex gap-3 fs-4">
                            <button class="btn btn-link text-dark p-0" disabled title="Comments coming soon">
                                <i class="bi bi-heart"></i>
                            </button>
                            <button class="btn btn-link text-dark p-0" disabled title="Comments coming soon">
                                <i class="bi bi-chat text-dark"></i>
                            </button>
                        </div>
                    </div>

                    <div class="px-3 pb-1 text-dark">
                        <strong id="likes-count-${publication.id}">${publication.likes}</strong> likes
                    </div>

                    <div class="px-3 pb-2 text-dark">
                        <span class="fw-semibold">${publication.owner}</span>
                        ${publication.description}
                    </div>

                    <div class="px-3 pb-3 small text-dark">
                        ${publication.createdDate?date("yyyy-MM-dd")?string.medium}
                    </div>

<#--                    <div class="border-top px-3 py-4 bg-light text-center text-muted">-->
<#--                        <i class="bi bi-chat-square-dots fs-1 mb-2 d-block"></i>-->
<#--                        <p class="mb-1 fw-medium">Comments</p>-->
<#--                        <p class="small mb-0">Комментарии появятся позже</p>-->
<#--                    </div>-->

                </div>

            </div>
        </div>
    </div>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

    <style>
        .like-btn i.bi-heart.fill {
            color: #ed4956 !important;
        }
        .card {
            max-width: 700px;
            margin: 0 auto;
        }
        @media (max-width: 576px) {
            .card {
                border-radius: 0;
                margin-bottom: 0;
            }
            .ratio-4x5 {
                aspect-ratio: 4/5 !important;
            }
        }
    </style>

</@layout.layout>