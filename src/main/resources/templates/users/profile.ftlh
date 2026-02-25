<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <div class="container" style="max-width: 935px; margin-top: 30px;">
        <div class="row mb-5">
            <div class="col-md-4 d-flex justify-content-center">
                <div class="position-relative">
                    <#if profileUser.avatar?? && profileUser.avatar?has_content>
                        <img src="/images/${profileUser.avatar}" class="rounded-circle border border-secondary" style="width: 150px; height: 150px; object-fit: cover;">
                    <#else>
                        <img src="/static/images/avatar.jpg" class="rounded-circle border border-secondary" style="width: 150px; height: 150px; object-fit: cover;">
                    </#if>
                </div>
            </div>

            <div class="col-md-8">
                <div class="d-flex align-items-center mb-3 gap-3">
                    <h2 class="h3 mb-0">${profileUser.login}</h2>

                    <#if isOwner>
                        <form action="/users/avatar" method="post" enctype="multipart/form-data" class="m-0">
                            <#if _csrf??><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></#if>
                            <label for="avatarInput" class="btn btn-outline-light btn-sm fw-bold mb-0" style="cursor: pointer;">Сменить фото</label>
                            <input type="file" id="avatarInput" name="file" accept="image/*" class="d-none" onchange="this.form.submit();">
                        </form>
                    <#elseif isAuthenticated>
                        <button class="btn btn-primary btn-sm fw-bold px-4">Подписаться</button>
                    </#if>
                </div>

                <div class="d-flex mb-3 gap-4">
                    <div><b>${profileUser.publicationsCount!0}</b> публикаций</div>
                    <div><b>${profileUser.followers!0}</b> подписчики</div>
                    <div><b>${profileUser.subscriptions!0}</b> подписки</div>
                </div>

                <div class="fw-bold">${profileUser.userName!""}</div>
                <div class="text-secondary">${profileUser.description!""}</div>
            </div>
        </div>

        <hr class="border-secondary">

        <div class="text-center py-5">
            <h4 class="text-secondary">Публикаций пока нет</h4>
            <#if isOwner>
                <a href="/publications/create" class="btn btn-link text-info fw-bold">Добавить публикацию</a>
            </#if>
        </div>
    </div>

</@main.layout>