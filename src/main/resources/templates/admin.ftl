<#-- @ftlvariable name="films" type="java.util.List<ru.dz.entity.Film>" -->
<#include "temp/mainTemplate.ftl">
<@main_template title="Поиск"/>

<#macro banner>

</#macro>

<#macro container>
<section class="admin-main" xmlns="http://www.w3.org/1999/html">

    <#if films?? && films?has_content>
        <#list films as film>
            <div class="film">
                <h2><a href="/admin/film/${film.id?c}">${film.name}</a></h2><br>
                <p><a class="more-link" href="/admin/film/${film.id?c}">Просмотреть подробную информацию</a></p>
                <img src="${film.image}" width="180" height="280"/><br><br>
                <p>${film.description}</p><br><br>
                <div class="more-link-delete">
                    <p><a href="/admin/delete/film/${film.id?c}"> Удалить</a></p>
                </div>
            </div>
        </#list>
    </#if>

</section>
<script src="/resources/react/browser.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</#macro>
