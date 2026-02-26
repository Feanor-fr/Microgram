<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <div class="container d-flex justify-content-center" style="margin-top: 30px;">
        <div class="card border-0 shadow-lg"
             style="width: 100%; max-width: 500px; background-color: var(--ig-surface);
         border-radius: 12px; border: 1px solid var(--ig-border) !important;">

            <div class="card-header border-bottom text-center py-3"
                 style="border-color: var(--ig-border) !important;">
                <h5 class="mb-0 fw-bold">Создание публикации</h5>
            </div>

            <div class="card-body p-4">

                <@spring.bind "publicationCreateDto"/>

                <form action="/publication/create"
                      method="post"
                      enctype="multipart/form-data">

                    <#if _csrf??>
                        <input type="hidden"
                               name="${(_csrf.parameterName)!'csrf-param-name'}"
                               value="${(_csrf.token)!'csrf-token'}">
                    </#if>

                    <div class="mb-4 text-center">

                        <img id="imagePreview"
                             src="#"
                             alt="Превью"
                             class="img-fluid rounded mb-3 d-none"
                             style="max-height: 400px; object-fit: contain; width: 100%;">

                        <label for="fileInput"
                               class="btn btn-warning fw-bold px-4 w-100"
                               style="cursor: pointer;">
                            Выбрать фото
                        </label>

                        <input type="file"
                               id="fileInput"
                               name="file"
                               accept="image/png, image/jpeg, image/jpg"
                               class="d-none"
                               onchange="previewImage(event)">

                        <@spring.showErrors "<br>" "text-danger"/>
                    </div>

                    <div class="mb-4">
                        <label class="form-label text-secondary fw-semibold">
                            Подпись
                        </label>

                        <@spring.formTextarea "publicationCreateDto.description"
                        'class="form-control" rows="4"
                         placeholder="Добавьте подпись..." maxlength="500"
                         style="resize: none;"'/>

                        <@spring.showErrors "<br>" "error text-danger" />
                    </div>

                    <div class="d-grid">
                        <button type="submit"
                                class="btn btn-warning fw-bold py-2">
                            Поделиться
                        </button>
                    </div>

                </form>
            </div>
        </div>
    </div>

</@main.layout>