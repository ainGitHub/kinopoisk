<#-- @ftlvariable name="film" type="ru.dz.entity.Film" -->
<#-- @ftlvariable name="genres" type="java.util.List<ru.dz.entity.Genre>" -->
<#-- @ftlvariable name="actors" type="java.util.List<ru.dz.entity.Person>" -->
<#-- @ftlvariable name="directors" type="java.util.List<ru.dz.entity.Person>" -->
<#-- @ftlvariable name="screenwriters" type="java.util.List<ru.dz.entity.Person>" -->
<#include "temp/mainTemplate.ftl">
<@main_template title="Фильм"/>

<#macro banner></#macro>

<#macro container>
<section class="container">
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
                                    ${(director.firstName)!} ${(director.lastName)!}</a><#sep>,
                                </#list>
                            </#if><br>
                            сценарий:
                            <#if screenwriters?has_content>
                                <#list screenwriters as screenwriter>
                                    <a href="/actor/${(screenwriter.id)!}" style="text-decoration: none">
                                    ${(screenwriter.firstName)!} ${(screenwriter.lastName)!}</a><#sep>,
                                </#list>
                            </#if><br>
                            жанр:
                            <#if genres?has_content>
                                <#list genres as genres>
                                ${(genres.name)!}<#sep>,
                                </#list>
                            </#if><br>
                            актеры:
                            <#if actors?has_content>
                                <#list actors as actor>
                                    <a href="/actor/${(actor.id)!}" style="text-decoration: none">
                                    ${(actor.firstName)!} ${(actor.lastName)!}</a><#sep>,
                                </#list>
                            </#if><br>
                            время: ${(film.getDuration())!} мин.<br>
                            возрастное ограничение: ${(film.getAgeLimit())!}<br>
                            <a href="" data-toggle="modal" data-target="#trailer"
                               style="text-decoration: none">
                                посмотреть трейлер</a><br>
                            рейтинг:
                            <#if film.getRating()??> ${(film.getRating())!} из 5 <#else> 0 из 5</#if>
                            <br>

                            <div id="reviewStars-input">
                                <input id="star-4"
                                       <#if film.getVoters()?? && (film.getRating() lt 5.5)>checked</#if>
                                       type="radio" name="reviewStars"
                                       data-id="${film.getId()!?c}"/>
                                <label title="good" for="star-4"></label>

                                <input id="star-3"
                                       <#if film.getVoters()?? && (film.getRating() lt 4.51)>checked</#if>
                                       type="radio" name="reviewStars"
                                       data-id="${film.getId()!?c}"/>
                                <label title="regular" for="star-3"></label>

                                <input id="star-2"
                                       <#if film.getVoters()?? && (film.getRating() lt 3.51)>checked</#if>
                                       type="radio" name="reviewStars"
                                       data-id="${film.getId()!?c}"/>
                                <label title="poor" for="star-2"></label>

                                <input id="star-1"
                                       <#if film.getVoters()?? && (film.getRating() lt 2.51)>checked</#if>
                                       type="radio" name="reviewStars"
                                       data-id="${film.getId()!?c}"/>
                                <label title="gorgeous" for="star-1"></label>

                                <input id="star-0"
                                       <#if film.getVoters()?? && (film.getRating() lt 1.51)>checked</#if>
                                       type="radio" name="reviewStars"
                                       data-id="${film.getId()!?c}"/>
                                <label title="bad" for="star-0"></label>
                            </div>

                            <br>
                            <br>
                        </div>
                        <div style="text-align: justify; margin-top: 30px">
                        ${(film.description)!}
                        </div>
                        <br><br>

                        <div class="review" style="margin-left: 200px; width: 500px">
                            <h3>Отзывы о фильме</h3><br>
                            <#if user??>
                                <form method="POST" action="/film/review/add/${film.getId()!?c}">
                                    <input type="text" name="content" class="form-control review" placeholder="Отзыв...">
                                    <button type="submit" class="btn">Добавить отзыв</button>
                                </form>
                            <#else>
                                Отзыв могут оставлять только авторизованные пользователи
                            </#if>
                            <br><br>
                            <#if film.getReviews()?has_content>
                                <#list film.getReviews() as review>
                                    <p><span style="color: blue">${(review.getUserInfo().getUsername())!}</span><br>
                                    ${(review.getContent())!}</p>
                                    <hr>
                                </#list>
                            <#else>
                                У этого фильма пока нет ни одного отзыва
                            </#if>

                        </div>

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

                <#else>
                    <h3><span>Данные не найдены</span></h3>
                </#if>
            </li>
        </ul>
    </article>
</section>
</#macro>