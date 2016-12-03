<#-- @ftlvariable name="film" type="ru.dz.entity.Film" -->
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
                            год: ${(film.getYear())!} <br>
                            страна: ${(film.getCountry())!} <br>
                            режиссер: <br>
                            сценарий: <br>
                            жанр: <br>
                            время: ${(film.getDuration())!} <br>
                            возрастное ограничение: ${(film.getAgeLimit())!}<br>
                            рейтинг: <#if film.getRating()??> ${(film.getRating()/film.getVoters())!} из 5 <#else> 0 из
                            5</#if><br>

                            <div id="reviewStars-input">
                                <input id="star-4" type="radio" name="reviewStars" data-id="${film.getId()!}"/>
                                <label title="good" for="star-4"></label>

                                <input id="star-3" type="radio" name="reviewStars" data-id="${film.getId()!}"/>
                                <label title="regular" for="star-3"></label>

                                <input id="star-2" type="radio" name="reviewStars" data-id="${film.getId()!}"/>
                                <label title="poor" for="star-2"></label>

                                <input id="star-1" type="radio" name="reviewStars" data-id="${film.getId()!}"/>
                                <label title="gorgeous" for="star-1"></label>

                                <input id="star-0" type="radio" name="reviewStars" data-id="${film.getId()!}"/>
                                <label title="bad" for="star-0"></label>
                            </div>
                            <br>
                            <br>
                        </div>
                        <div style="text-align: justify; margin-top: 30px">
                        ${(film.description)!}
                        </div>
                        <br><br><br>
                        <h1>Отзывы о фильме</h1><br>

                        <#if film.getReviews()?has_content>
                            <#list film.getReviews() as review>
                                <h2>${review.getUserInfo().getUsername()}</h2> &nbsp&nbsp <br>
                                <h5>${review.getContent()}</h5>
                            </#list>
                        <#else>
                            У этого фильма пока нет ни одного отзыва
                        </#if>
                        <br><br>

                        <#if user??>
                            <form method="POST" action="/film/review/add/${film.getId()!}">
                                <input type="text" name="content" class="form-control review" placeholder="Отзыв...">
                                <button type="submit" class="btn">Добавить отзыв</button>
                            </form>
                        <#else>
                            Отзыв могут оставлять только авторизованные пользователи
                        </#if>
                    </div>

                <#else>
                    <h3><span>Данные не найдены</span></h3>
                </#if>
            </li>
        </ul>
    </article>
</section>
</#macro>