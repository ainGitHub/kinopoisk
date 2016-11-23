<#-- @ftlvariable name="films" type="java.util.List<ru.dz.entity.Film>" -->
<#include "temp/mainTemplate.ftl">
<@main_template title="Поиск" scripts=[]/>

<#macro body>
<div id="content">
    <div class="line-hor"></div>
    <div class="box">
        <div class="border-right">
            <div class="border-left">
                <div class="inner">
                    <#include "temp/searching.ftl" />
                    <#if films?has_content>
                        <#list films as film>
                            <div class="img-box1 photo" style="margin-top: 20px">
                                <img src="${(film.image)!}" alt="photo" width="58" height="83"/>

                                <div>
                                    ${(film.name)!}<br>
                                    ${(film.country)!}, реж. Пэтти Дженкинс<br>
                                    фантастика, фэнтези, боевик<br>
                                    Галь Гадот, Крис Пайн<br>
                                </div>
                            </div>
                            <hr>
                        </#list>
                    <#else><h3 align="center">Данные не найдены</h3>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div></#macro>