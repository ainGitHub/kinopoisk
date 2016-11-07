<#-- @ftlvariable name="user" type="ru.dz.entity.UserInfo" -->
<#include "temp/mainTemplate.ftl">
<@main_template title="Личный кабинет" scripts=[]/>

<#macro body>
<div id="content">
    <div class="line-hor"></div>
    <div class="box">
        <div class="border-right">
            <div class="border-left">
                <div class="inner">
                    <#if user??>
                        <h3><span>User name</span></h3>

                        <div class="img-box1 photo">
                            <img src="/resources/images/photo/user.gif" alt="photo" width="180" height="280"/>
                            <div style="margin-left: 400px">
                                <a href="#">Изменить данные</a><br>
                                дата рождения: 01.01.1990 <br>
                                пол: женский <br>
                                город: Казань <br>
                                email: email@mail.ru<br>
                                Список рецензий: - <br>
                                <a href="#">Написать рецензию</a>
                            </div>
                        </div>
                    <#else><h3><span>Данные не найдены</span></h3>
                    </#if>

                </div>
            </div>
        </div>
    </div>
</div></#macro>