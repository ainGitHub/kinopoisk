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
                    <#if film??>
                            <h3><span>${(film.name)!}</span></h3>

                        <div class="img-box1 photo">
                            <img src="${(film.getImage())!}" alt="photo" width="180" height="280"/>

                            <div style="margin-left: 300px">
                                год: ${(film.getYear())!} <br>
                                страна: ${(film.getCountry())!} <br>
                                режиссер:  <br>
                                сценарий:  <br>
                                жанр:  <br>
                                время: ${(film.getDuration())!} <br>
                                возрастное ограничение: ${(film.getAgeLimit())!}
                            </div>
                        </div>
                        <div style="text-align: justify; margin-top: 30px">
                                ${(film.description)!}
                        </div>
                    <#else><h3><span>Данные не найдены</span></h3>
                    </#if>

                </div>
            </div>
        </div>
    </div>
</div></#macro>