<#import "../layout.ftlh" as reg>
<@reg.layout ; spring>

    <section class="py-5" data-bs-theme="dark">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-md-8 col-lg-6">

                    <div class="card border-0 shadow-lg rounded-4 overflow-hidden"
                         style="background-color: #1c1c1e; border: 1px solid #333;">

                        <div class="card-body p-4 p-md-5">

                            <div class="text-center mb-4">
                                <h3 class="fw-bold mb-1" style="color: #f0f0f0; font-size: 2.1rem;">
                                    Регистрация
                                </h3>
                                <p class="text-secondary small">
                                    Уже есть аккаунт? <a href="/auth/login" class="text-primary fw-semibold">Войти</a>
                                </p>
                            </div>

                            <form id="register-form" action="/auth/register" method="post">
                                <#if _csrf??>
                                    <input type="hidden"
                                           name="${(_csrf.parameterName)!'csrf-param-name'}"
                                           value="${(_csrf.token)!'csrf-token'}">
                                </#if>

                                <div class="mb-3">
                                    <@spring.formInput "userDto.email"
                                    'class="form-control bg-dark border-secondary text-white placeholder-gray"
                                         id="email" placeholder="Электронная почта" autocomplete="email"' />
                                    <@spring.showErrors "<br>" "error text-danger" />
                                </div>

                                <div class="mb-3">
                                    <@spring.formInput "userDto.login"
                                    'class="form-control bg-dark border-secondary text-white placeholder-gray"
                                         id="login" placeholder="Логин" autocomplete="username"' />
                                    <@spring.showErrors "<br>" "error text-danger" />
                                </div>

                                <div class="mb-3">
                                    <@spring.formInput "userDto.userName"
                                    'class="form-control bg-dark border-secondary text-white placeholder-gray"
                                         id="userName" placeholder="Никнейм"' />
                                    <@spring.showErrors "<br>" "error text-danger" />
                                </div>

                                <div class="mb-3">
                                    <@spring.formPasswordInput "userDto.password"
                                    'class="form-control bg-dark border-secondary text-white placeholder-gray"
                                         id="password" placeholder="Пароль" autocomplete="new-password"' />
                                    <@spring.showErrors "<br>" "error text-danger" />
                                </div>

                                <div class="mb-4">
                                    <@spring.formTextarea "userDto.description"
                                    'class="form-control bg-dark border-secondary text-white placeholder-gray"
                                         id="description" rows="3" placeholder="О себе"' />
                                    <@spring.showErrors "<br>" "error text-danger" />
                                </div>

                                <button type="submit"
                                        class="btn btn-primary w-100 fw-bold py-3 rounded-pill shadow">
                                    Зарегистрироваться
                                </button>

                            </form>

                        </div>
                    </div>

                </div>
            </div>
        </div>
    </section>

</@reg.layout>