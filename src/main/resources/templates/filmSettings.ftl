<#-- @ftlvariable name="film" type="ru.dz.entity.Film" -->
<#-- @ftlvariable name="genres" type="java.util.List<ru.dz.entity.Genre>" -->
<#-- @ftlvariable name="actors" type="java.util.List<ru.dz.entity.Person>" -->
<#-- @ftlvariable name="directors" type="java.util.List<ru.dz.entity.Person>" -->
<#-- @ftlvariable name="screenwriters" type="java.util.List<ru.dz.entity.Person>" -->
<#include "temp/mainTemplate.ftl">
<@main_template title="Фильм"/>

<#macro banner></#macro>

<#macro container>
<section class="container" xmlns="http://www.w3.org/1999/html">
    <article class="post content">
        <ul class="post-list">
            <li>
                <#if film??>
                    <h1>${(film.name)!}</h1>

                    <div class="img-box" style="margin-top: 50px">
                        <img src="${(film.getImage())!}" alt="photo" width="180" height="280"/>

                        <div>
                            дата премьеры: ${(film.getYear()?date)!} <br>
                            страна: ${(film.getCountry())!} <br>
                            режиссер:
                            <#if directors?has_content>
                                <#list directors as director>
                                    <a href="/actor/${(director.id)!}" style="text-decoration: none">
                                    ${(director.firstName)!} ${(director.lastName)!}</a>
                                    <#if directors?size gt 1>
                                        (<a href="/admin/delete/director/${film.getId()}/${(director.id)!}">удалить</a>)
                                    </#if><#sep>,
                                </#list>
                            </#if><br>
                            сценарий:
                            <#if screenwriters?has_content>
                                <#list screenwriters as screenwriter>
                                    <a href="/actor/${(screenwriter.id)!}" style="text-decoration: none">
                                    ${(screenwriter.firstName)!} ${(screenwriter.lastName)!}</a>
                                    <#if screenwriters?size gt 1>
                                        (<a href="/admin/delete/writer/${film.getId()}/${(screenwriter.id)!}">удалить</a>)
                                    </#if><#sep>,
                                </#list>
                            </#if><br>
                            жанр:
                            <#if genres?has_content>
                                <#list genres as genre>
                                ${(genre.name)!}
                                    <#if genres?size gt 1>
                                        (<a href="/admin/delete/genre/${film.getId()}/${(genre.id)!}">удалить</a>)
                                    </#if><#sep>,
                                </#list>
                            </#if><br>
                            актеры:
                            <#if actors?has_content>
                                <#list actors as actor>
                                    <a href="/actor/${(actor.id)!}" style="text-decoration: none">
                                    ${(actor.firstName)!} ${(actor.lastName)!}</a>
                                    <#if actors?size gt 1>
                                        (<a href="/admin/delete/actor/${film.getId()}/${(actor.id)!}">удалить</a>)
                                    </#if><#sep>,
                                </#list>
                            </#if><br>
                            время: ${(film.getDuration())!} мин.<br>
                            возрастное ограничение: ${(film.getAgeLimit())!}<br>
                            <a href="" data-toggle="modal" data-target="#trailer"
                               style="text-decoration: none">
                                посмотреть трейлер</a><br>
                            рейтинг:
                            <#if film.getRating()??> ${(film.getRating())!} из 5 <#else> 0 из 5 </#if>
                            (<a href="/admin/rating/delete/${film.getId()}">сбросить</a>)
                            <br><br>

                            <a href="" data-toggle="modal" data-target="#changeFilm">Изменить информацию о фильме</a>
                            <br>
                        </div>
                        <div style="text-align: justify; margin-top: 30px">
                        ${(film.description)!}
                        </div>
                        <br><br><br>

                        <h1>Отзывы о фильме</h1><br>

                        <#if film.getReviews()?has_content>
                            <#list film.getReviews() as review>
                                <h2>${(review.getUserInfo().getUsername())!}</h2>
                                (<a href="/admin/review/delete/${review.getId()}/${film.getId()}">удалить отзыв</a>)
                                <br><br>
                                <h5>${(review.getContent())!}</h5><br>
                            </#list>
                        <#else>
                            К данному фильму нет ни одного отзыва
                        </#if>
                        <br><br>
                    </div>

                    <div class="modal fade bd-example-modal-lg" id="trailer" tabindex="-1" role="dialog"
                         aria-labelledby="myLargeModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content" style="width:854px; height:480px">
                                <iframe width="854" height="480" src="${(film.trailer)!}" frameborder="0"
                                        allowfullscreen></iframe>
                            </div>
                        </div>
                    </div>

                    <div class="modal fade" id="changeFilm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="myModalLabel">Изменение информации о фильме</h4>
                                </div>

                                <form method='GET' action="/admin/change/${film.getId()}">
                                    <div class="modal-body">
                                        <br/>
                                        <label>
                                            <i>Название фильма:</i><br>
                                            <input class="modal-input" value="${film.getName()}" name="name"/><br><br>
                                            <i>Страна:</i><br>
                                            <input class="modal-input" value="${film.getCountry()}" name="country"/><br><br>
                                            <i>Время (мин.):</i><br>
                                            <input class="modal-input" value="${film.getDuration()}"
                                                   name="duration"/><br><br>
                                            <i>Возрастное ограничение:</i><br>
                                            <input class="modal-input" value="${film.getAgeLimit()}"
                                                   name="age"/><br><br>
                                            <i>Дата премьеры:</i><br>
                                            <input type='date' name='year'/><br><br>
                                            <i>Добавить режиссера:</i><br>
                                            <#if producers?has_content>
                                                <select name="director">
                                                    <option value="nothing" selected>Не выбрано</option>
                                                    <#list producers as p>
                                                        <option value="${(p.id)!}">${(p.firstName)!} ${(p.lastName)!}</option>
                                                    </#list>
                                                </select>
                                            </#if>
                                            <br><br>
                                            <i>Добавить сценариста:</i><br>
                                            <#if writers?has_content>
                                                <select name="writer">
                                                    <option value="nothing" selected>Не выбрано</option>
                                                    <#list writers as w>
                                                        <option value="${(w.id)!}">${(w.firstName)!} ${(w.lastName)!}</option>
                                                    </#list>
                                                </select>
                                            </#if>
                                            <br><br>
                                            <i>Добавить актёра:</i><br>
                                            <#if persons?has_content>
                                                <select name="actor">
                                                    <option value="nothing" selected>Не выбрано</option>
                                                    <#list persons as p>
                                                        <option value="${(p.id)!}">${(p.firstName)!} ${(p.lastName)!}</option>
                                                    </#list>
                                                </select>
                                            </#if>
                                            <br><br>
                                            <i>Добавить жанр фильма:</i><br>
                                            <#if janres?has_content>
                                                <select name="genre">
                                                    <option value="nothing" selected>Не выбрано</option>
                                                    <#list janres as j>
                                                        <option value="${(j.id)!}">${(j.name)!}</option>
                                                    </#list>
                                                </select>
                                            </#if>
                                            <br><br>
                                            <i>Описание (не более 1000 символов):</i><br>
                                        <textarea class="review-text" wrap="hard" rows="6" cols="60" maxlength="1000"
                                                  name='description'>${film.getDescription()}</textarea>
                                        </label>
                                        <br/>
                                    </div>
                                    <div class="modal-footer">
                                        <input type="submit" class="btn btn-primary" value="Сохранить изменения"/>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                <#else>
                    <h3><span>Данные не найдены</span></h3>
                </#if>
            </li>
        </ul>
    </article>
</section>
</#macro>