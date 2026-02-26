<#import "../layout.ftlh" as layout>

<@layout.layout>
    <div class="row justify-content-center">
        <div class="col-12 col-md-8 col-lg-6">
            <h2 class="mb-4 text-center">Поиск пользователей</h2>

            <form action="/users/search" method="get" class="mb-5">
                <div class="input-group">
                    <select name="search" class="form-select border-end-0" style="max-width: 130px;">
                        <option value="login" ${(search?? && search == 'login')?string('selected', '')}>Логин</option>
                        <option value="userName" ${(search?? && search == 'userName')?string('selected', '')}>Имя</option>
                        <option value="email" ${(search?? && search == 'email')?string('selected', '')}>Email</option>
                    </select>

                    <input type="text" name="word" class="form-control"
                           placeholder="Введите запрос..."
                           value="${word!''}">

                    <button class="btn btn-warning" type="submit">
                        Найти
                    </button>
                </div>
            </form>

            <div class="user-list">
                <#if foundUsers??>
                    <#if foundUsers?has_content>
                        <div class="list-group shadow-sm">
                            <#list foundUsers as user>
                                <a href="/users/profile?email=${user.email}"
                                   class="list-group-item list-group-item-action border-secondary bg-dark text-white d-flex align-items-center p-3 mb-2 rounded border">
                                    <div class="avatar-placeholder me-3 rounded-circle bg-secondary d-flex align-items-center justify-content-center"
                                         style="width: 45px; height: 45px;">
                                        ${user.login?substring(0,1)?upper_case}
                                    </div>
                                    <div>
                                        <div class="fw-bold">${user.email}</div>
                                        <div class="fw-bold">${user.login}</div>
                                        <div class="small text-secondary">${user.userName!''}</div>
                                    </div>
                                </a>
                            </#list>
                        </div>
                    <#else>
                        <div class="text-center text-secondary py-5">
                            <h5>Пользователи не найдены</h5>
                            <p>Попробуйте изменить критерии поиска</p>
                        </div>
                    </#if>
                </#if>
            </div>
        </div>
    </div>
</@layout.layout>