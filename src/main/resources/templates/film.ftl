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
                возрастное ограничение: ${(film.getAgeLimit())!}
            </div>
            <div style="text-align: justify; margin-top: 30px">
            ${(film.description)!}
            </div>
        </div>

    <#else><h3><span>Данные не найдены</span></h3>
    </#if>
</section>
</#macro>