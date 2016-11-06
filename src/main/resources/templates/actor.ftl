<#-- @ftlvariable name="person" type="ru.dz.entity.Person" -->
<#include "temp/mainTemplate.ftl">
<@main_template title="Профиль" scripts=[]/>

<#macro body>
<div id="content">
    <div class="line-hor"></div>
    <div class="box">
        <div class="border-right">
            <div class="border-left">
                <div class="inner">
                    <#if person??>
                        <h3><span>Галь Гадот</span></h3>

                        <div class="img-box1 photo">
                            <img src="/resources/images/photo/1.jpg" alt="photo" width="180" height="280"/>
                            <div style="margin-left: 400px">
                                карьера: Актриса <br>
                                рост: 1.78 м <br>
                                дата рождения: 30 апреля 1985 <br>
                                место рождения: Рош-ха-Аин, Израиль <br>
                                жанры: боевик, триллер, драма
                            </div>
                        </div>
                    <#else><h3><span>Данные не найдены</span></h3>
                    </#if>

                </div>
            </div>
        </div>
    </div>
</div></#macro>