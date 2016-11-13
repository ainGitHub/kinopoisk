<#-- @ftlvariable name="film" type="ru.dz.entity.Film" -->
<#include "temp/mainTemplate.ftl">
<@main_template title="Фильм" scripts=[]/>

<#macro body>
<div id="content">
    <div class="line-hor"></div>
    <div class="box">
        <div class="border-right">
            <div class="border-left">
                <div class="inner">
                    <#if films??>
                        <#list films as film>
                            <h3><span>${film.name}</span></h3>

                        <div class="img-box1 photo">
                            <img src="/resources/images/photo/2.jpg" alt="photo" width="180" height="280"/>

                            <div style="margin-left: 300px">
                                год: 2017 <br>
                                страна: США <br>
                                режиссер: Пэтти Дженкинс <br>
                                сценарий: Аллан Хейнберг, Джефф Джонс, Уильям М. Марстон <br>
                                жанр: фантастика, фэнтези, боевик, приключения <br>
                                время: - <br>
                                возрастное ограничение:
                            </div>
                        </div>
                        <div style="text-align: justify; margin-top: 50px">
                            <#if film.description??>
                                ${film.description}
                            </#if>
                        </div>
                        </#list>
                    <#else><h3><span>Данные не найдены</span></h3>
                    </#if>

                </div>
            </div>
        </div>
    </div>
</div></#macro>