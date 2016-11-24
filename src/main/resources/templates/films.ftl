<#-- @ftlvariable name="films" type="java.util.List<ru.dz.entity.Film>" -->
<#include "temp/mainTemplate.ftl">
<@main_template title="Поиск"/>

<#macro banner></#macro>

<#macro container>
<section class="container">
    <#include "temp/searching.ftl" />

    <#if films?has_content>
        <#list films as film>
            <div class="img-box" style="margin-top: 20px">
                <a href="/film/${film.getId()}">
                    <img src="${(film.image)!}" alt="photo" width="58" height="83"/></a>
                <div>
                ${(film.name)!}<br>
                ${(film.country)!}, реж. <br>
                    жанр: <br>
                    актеры: <br>
                </div>
                <div class="description">
                ${(film.description)!}
                </div>
            </div>
            <hr>
        </#list>
    <#else><h3 align="center">Данные не найдены</h3>
    </#if>
</section>
</#macro>
