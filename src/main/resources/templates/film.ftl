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
                        <h3><span>Чудо-женщина</span></h3>

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
                            Перед тем как стать Чудо-Женщиной, она была Дианой — принцессой амазонок,
                            обученной быть непобедимой воительницей. И когда на берегах огражденного от внешнего
                            мира райского острова, который служил ей родиной, терпит крушение американский пилот
                            и рассказывает о серьезном конфликте, бушующем во внешнем мире, Диана покидает свой
                            дом, чтобы справиться с этой угрозой. И там, сражаясь бок о бок с человеком в войне
                            за мир, Диана обнаружит всю полноту своей власти… и свое истинное предназначение.
                        </div>
                    <#else><h3><span>Данные не найдены</span></h3>
                    </#if>

                </div>
            </div>
        </div>
    </div>
</div></#macro>