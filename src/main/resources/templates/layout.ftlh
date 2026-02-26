<#import "spring.ftl" as spring>
<#macro layout>
    <!doctype html>
    <html lang="ru" data-bs-theme="dark">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <#if _csrf??>
            <meta name="_csrf" content="${_csrf.token}">
            <meta name="_csrf_header" content="${_csrf.headerName}">
        </#if>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

        <style>
            :root {
                --ig-bg:        #0f0f0f;
                --ig-surface:   #1c1c1e;
                --ig-surface-2: #262626;
                --ig-text:      #f0f0f0;
                --ig-text-sec:  #a8a8a8;
                --ig-border:    #333333;
                --ig-accent:    #0095f6;
                --ig-accent-hover: #0870d4;
            }

            body {
                background-color: var(--ig-bg);
                color: var(--ig-text);
            }

            .navbar {
                background-color: var(--ig-surface) !important;
                border-bottom: 1px solid var(--ig-border);
            }

            .navbar-brand {
                color: #fcaf45 !important;
            }

            .nav-link, .text-warning {
                color: var(--ig-text) !important;
            }

            .btn-warning {
                background-color: var(--ig-accent);
                border-color: var(--ig-accent);
                color: white;
            }

            .btn-warning:hover {
                background-color: var(--ig-accent-hover);
                border-color: var(--ig-accent-hover);
            }

            .btn-outline-warning {
                color: var(--ig-accent);
                border-color: var(--ig-accent);
            }

            .btn-outline-warning:hover {
                background-color: var(--ig-accent);
                color: white;
            }

            main.container {
                background-color: var(--ig-bg);
            }

            footer {
                background-color: var(--ig-surface);
                border-top: 1px solid var(--ig-border);
                color: var(--ig-text-sec);
            }

            .form-control {
                background-color: var(--ig-surface-2);
                border-color: var(--ig-border);
                color: var(--ig-text);
            }

            .form-control:focus {
                border-color: var(--ig-accent);
                box-shadow: 0 0 0 0.25rem rgba(0, 149, 246, 0.25);
            }

            .form-control::placeholder {
                color: var(--ig-text-sec);
            }

            .text-warning {
                color: #fcaf45 !important;
            }
        </style>

        <title>Microgram</title>
    </head>
    <body class="d-flex flex-column min-vh-100">

    <nav class="navbar navbar-expand-lg shadow-sm">
        <div class="container-fluid px-3 px-md-4">
            <a class="navbar-brand fw-bold fs-4" href="/">Microgram</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarContent">
                <div class="ms-auto d-flex align-items-center gap-3">
                    <#assign known = SPRING_SECURITY_CONTEXT?? >
                    <a href="/users/search" class="text-decoration-none fw-semibold me-3 d-flex align-items-center">Поиск</a>
                    <#if known>
                        <#assign user = SPRING_SECURITY_CONTEXT.authentication.principal>
                        <#if user??>
                            <a href="/users/profile" class="text-decoration-none fw-semibold me-3">
                                ${user.username}
                            </a>

                            <form action="/auth/logout" method="post" class="m-0">
                                <#if _csrf??>
                                    <input
                                            type="hidden"
                                            name="${(_csrf.parameterName)!'csrf-param-name'}"
                                            value="${(_csrf.token)!'csrf-token'}"
                                    >
                                </#if>
                                <button type="submit" class="btn btn-outline-warning px-4">Выйти</button>
                            </form>
                        </#if>
                    <#else>
                        <a href="/auth/login"    class="btn btn-warning px-4">Войти</a>
                        <a href="/auth/register" class="btn btn-outline-warning px-4">Регистрация</a>
                    </#if>
                </div>
            </div>
        </div>
    </nav>

    <main class="container py-4 py-md-5 flex-grow-1">
        <#nested spring>
    </main>

    <footer class="mt-auto py-3 border-top">
        <div class="container text-center">
            <span class="fw-medium">Microgram © ${.now?string("yyyy")}</span>
        </div>
    </footer>

    </body>
    </html>
</#macro>