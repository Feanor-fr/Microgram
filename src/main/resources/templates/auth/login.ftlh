<#import "../layout.ftlh" as main>
<@main.layout>

    <section class="py-5" data-bs-theme="dark">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-md-8 col-lg-6">

                    <div class="card border-0 shadow-lg rounded-4 overflow-hidden"
                         style="background-color: #1c1c1e; border: 1px solid #333;">

                        <div class="card-body p-4 p-md-5">

                            <div class="text-center mb-5">
                                <h3 class="fw-bold mb-2" style="color: #f0f0f0; font-size: 2.1rem;">
                                    Вход
                                </h3>
                                <p class="text-secondary small">
                                    Нет аккаунта?
                                    <a href="/auth/register" class="text-primary fw-semibold">Зарегистрироваться</a>
                                </p>
                            </div>

                            <form id="login-form" action="/auth/login" method="post">
                                <#if _csrf??>
                                    <input type="hidden"
                                           name="${(_csrf.parameterName)!'csrf-param-name'}"
                                           value="${(_csrf.token)!'csrf-token'}">
                                </#if>


                                <div class="mb-3">
                                    <label for="username" class="form-label fw-medium text-white">Email</label>
                                    <input type="text"
                                           id="username"
                                           name="username"
                                           class="form-control bg-dark border-secondary text-white placeholder-gray"
                                           autocomplete="username"
                                    >
                                </div>

                                <div class="mb-4">
                                    <label for="password" class="form-label fw-medium text-white">Пароль</label>
                                    <input type="password"
                                           id="password"
                                           name="password"
                                           class="form-control bg-dark border-secondary text-white placeholder-gray"
                                           placeholder="••••••••"
                                           autocomplete="current-password"
                                    >
                                </div>


                                <#if SPRING_SECURITY_LAST_EXCEPTION?? || error??>
                                    <div class="alert alert-danger mb-4 text-center small fw-medium" role="alert">
                                        Неверный email или пароль
                                    </div>
                                </#if>

                                <button type="submit"
                                        class="btn btn-primary w-100 fw-bold py-3 rounded-pill shadow">
                                    Войти
                                </button>

                            </form>

                        </div>
                    </div>

                </div>
            </div>
        </div>
    </section>

</@main.layout>