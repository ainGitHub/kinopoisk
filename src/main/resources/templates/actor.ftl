<#-- @ftlvariable name="person" type="ru.dz.entity.Person" -->
<#include "temp/mainTemplate.ftl">
<@main_template title="Профиль"/>

<#macro banner></#macro>

<#macro container>
<section class="container">
    <#if person??>
        <h1>Галь Гадот</h1>

        <div class="img-box" style="margin-top: 50px">
            <img src="/resources/images/photo/1.jpg" alt="photo" width="180" height="280"/>

            <div>
                карьера: Актриса <br>
                рост: 1.78 м <br>
                дата рождения: 30 апреля 1985 <br>
                место рождения: Рош-ха-Аин, Израиль <br>
                жанры: боевик, триллер, драма
            </div>
        </div>
    <#else><h3><span>Данные не найдены</span></h3>
    </#if>
</section>
</#macro>
