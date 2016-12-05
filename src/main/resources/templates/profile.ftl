<#-- @ftlvariable name="user" type="ru.dz.entity.UserInfo" -->
<#include "temp/mainTemplate.ftl">

<@main_template title="Личный кабинет"/>

<#macro banner></#macro>

<#macro container>


<section class="container">
    <#if user??>
        <h2>${(user.username)!}&nbsp&nbsp${(user.secondName)!}</h2>

        <div class="img-box" style="margin-top: 50px">
            <img src="${(user.getImage())!}" alt="photo" width="250" height="280"/>

            <a class="btn" href="/logout">Выйти</a>
            <div>
                Дата рождения: <#if (user.birthday)??> ${(user.birthday)?string("dd.MM.yyyy")}
                (<a href="/delete-date/${(user.id)!}">удалить</a>)
            <#else> не указана </#if> <br>
                Пол: <#if (user.gender)?has_content> ${(user.gender)!}<#else>  не указан </#if> <br>
                Город: <#if (user.city)?has_content> ${(user.city)!}<#else>  не указан </#if> <br>
                Email: <#if (user.email)?has_content> ${(user.email)!}<#else> не указан </#if> <br>
                <a href="" data-toggle="modal" data-target="#myModal">Изменить данные</a><br><br>

                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">Изменение данных пользователя</h4>
                            </div>

                            <form method='GET' action="/change/${(user.id)!}">
                                <div class="modal-body">
                                    <br/>
                                    <label>
                                        Дата рождения:&nbsp&nbsp
                                        <input type='date' name='birthday'/><br><br>
                                        Пол: &nbsp&nbsp
                                        <select name="gender">
                                            <#if user.gender??>
                                                <option <#if user.gender == 'мужской'> selected </#if> value="man">
                                                    Мужской
                                                </option>
                                                <option <#if user.gender == 'женский'> selected </#if> value="woman">
                                                    Женский
                                                </option>
                                            <#else>
                                                <option value="man">Мужской</option>
                                                <option value="woman">Женский</option>
                                            </#if>
                                        </select>
                                        <br><br>
                                        Город:&nbsp&nbsp <input type='text' value="${(user.city)!}" name='city'
                                                                id="city"/>&nbsp&nbsp
                                        <span id="valid-city"></span><br><br>
                                        E-mail:&nbsp&nbsp <input type='text' value="${(user.email)!}" name='email'
                                                                 id="email"/>&nbsp&nbsp
                                        <span id="valid-email"></span><br>
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

                Список отзывов:
                <br>
                <#if review?has_content>
                    <#list review as r>
                        <div class="reviews">
                            <b>Фильм:</b> <i>${(r.getFilm().getName())!}</i>
                            <a href="/review/remove/${(r.getId())!}" class="remove"><img
                                    src="/resources/images/remove.png"></a> <br>
                            <b>Отзыв:</b> ${(r.getContent())!}
                        </div>
                        <br>
                    </#list>
                <#else>
                    пока вы не оставили ни одного отзыва <br>
                </#if>
                <br>
                <a href="" data-toggle="modal" data-target="#addReview">Добавить отзыв</a><br>


                <div class="modal fade" id="addReview" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">Отзыв о фильме</h4>
                            </div>

                            <form method='POST' action="/review/add/${(user.id)!}">
                                <div class="modal-body">
                                    <br/>
                                    <label>
                                        Выберите фильм: <br>
                                        <select size="5" name="film">
                                            <option selected value="none">Не выбрано</option>
                                            <#list films as film>
                                                <option value="${(film.getId())!}">${(film.getName())!}</option>
                                            </#list>
                                        </select>
                                        <br><br>
                                        Напишите свой отзыв (не более 200 символов): <br>
                                        <textarea class="review-text" wrap="hard" rows="5" cols="50" maxlength="200"
                                                  placeholder="Отзыв..." name='content'></textarea>
                                    </label>
                                    <br><br>
                                </div>
                                <div class="modal-footer">
                                    <input type="submit" class="btn btn-primary" value="Добавить отзыв"/>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>


            </div>
        </div>
    </#if>
</section>
</#macro>