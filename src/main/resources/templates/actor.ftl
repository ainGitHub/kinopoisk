<#-- @ftlvariable name="person" type="ru.dz.entity.Person" -->
<#-- @ftlvariable name="careers" type="java.util.List<ru.dz.entity.Career>" -->
<#-- @ftlvariable name="genres" type="java.util.List<ru.dz.entity.Genre>" -->
<#include "temp/mainTemplate.ftl">
<@main_template title="Профиль"/>

<#macro banner></#macro>

<#macro container>
<section class="container" style="width: 800px">
    <#if person??>
        <h1>${(person.firstName)!} ${(person.lastName)!}</h1>

        <div class="img-box" style="margin-top: 50px">
            <img src="${(person.image)!}" alt="photo" width="180" height="280"/>

            <div>
                карьера:
                <#if careers?has_content>
                    <#list careers as career>
                    ${career.post}<#sep>,
                    </#list>
                </#if>
                <br>
                <#if person.growth??>рост: ${(person.growth)!} см<br></#if>
                дата рождения: ${(person.birthday?date)!} <br>
                место рождения: ${(person.city)!} <br>
                жанры:
                <#if genres?has_content>
                    <#list genres as genres>
                    ${(genres.name)!}<#sep>,
                    </#list>
                </#if>
            </div>
        </div>
    <#else><h3><span>Данные не найдены</span></h3>
    </#if>

</section>
</#macro>
