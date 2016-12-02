<#-- @ftlvariable name="film" type="ru.dz.entity.Film" -->
<#include "temp/mainTemplate.ftl">
<@main_template title="Фильм"/>

<#macro banner></#macro>

<#macro container>
<section class="container">
    <#if film??>
        <h1>${(film.name)!}</h1>

        <div class="img-box" style="margin-top: 50px">
            <img src="${(film.getImage())!}" alt="photo" width="180" height="280"/>

            <div>
                год: ${(film.getYear())!} <br>
                страна: ${(film.getCountry())!} <br>
                режиссер: <br>
                сценарий: <br>
                жанр: <br>
                время: ${(film.getDuration())!} <br>
                возрастное ограничение: ${(film.getAgeLimit())!}<br>

                <div id="reviewStars-input">
                    <input id="star-4" type="radio" name="reviewStars"/>
                    <label title="gorgeous" for="star-4"></label>

                    <input id="star-3" type="radio" name="reviewStars"/>
                    <label title="good" for="star-3"></label>

                    <input id="star-2" type="radio" name="reviewStars"/>
                    <label title="regular" for="star-2"></label>

                    <input id="star-1" type="radio" name="reviewStars"/>
                    <label title="poor" for="star-1"></label>

                    <input id="star-0" type="radio" name="reviewStars"/>
                    <label title="bad" for="star-0"></label>
                </div>
                <br>
            </div>
            <div style="text-align: justify; margin-top: 30px">
            ${(film.description)!}
            </div>
        </div>

    <#else><h3><span>Данные не найдены</span></h3>
    </#if>
</section>
</#macro>