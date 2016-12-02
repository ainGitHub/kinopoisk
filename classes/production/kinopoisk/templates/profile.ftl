<#-- @ftlvariable name="user" type="ru.dz.entity.UserInfo" -->
<#include "temp/mainTemplate.ftl">
<@main_template title="Личный кабинет"/>

<#macro banner></#macro>

<#macro container>
<section class="container">
    <#if user??>
        <h1>${(user.username)!}</h1>
    </#if>
    <div class="img-box" style="margin-top: 50px">
        <img src="/resources/images/photo/unknown.gif" alt="photo" width="180" height="280"/>

        <div>
            <a href="#">Изменить данные</a><br>
            дата рождения: <br>
            пол: <br>
            город: <br>
            email: <br>
            Список рецензий: - <br>
            <a href="#">Написать рецензию</a>
        </div>
    </div>
</section>
</#macro>