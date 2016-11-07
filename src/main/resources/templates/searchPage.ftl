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
                <#--<#if films?has_content>-->
                <#--<#list films as film></#list>-->
                <#--<#else><h3><span>Данные не найдены</span></h3>-->
                <#--</#if>-->
                    <div>
                        <h3><span>Поиск</span></h3>

                        <form id="search-form-1" action="">
                            <fieldset>
                                <div class="field">
                                    <label>Название:</label>
                                    <input type="text" name="name"/>
                                </div>
                                <div class="field">
                                    <label>Жанр:</label>
                                    <input type="text" name="genre"/>
                                </div>
                                <div class="field">
                                    <label>Год выпуска:</label>
                                    <input type="text" name="year"/>
                                </div>
                                <div class="field">
                                    <label>Актер, режиссер:</label>
                                    <input type="text" name="actor"/>
                                </div>
                                <div class="wrapper">
                                    <a href="#" class="link2"
                                       onclick="document.getElementById('contacts-form').submit()">
								<span>
									<span>Поиск</span>
								</span>
                                    </a>
                                </div>
                            </fieldset>
                        </form>

                        <h3><span>Сортировать по</span></h3>

                        <form action="">
                            <fieldset>
                                <label>
                                    релевантности
                                    <select name="rel" style="background: black; color: white">
                                        <option value="asc"> по возрастанию</option>
                                        <option value="desc"> по убыванию</option>
                                    </select>
                                </label><br>
                                <label>
                                    рейтингу
                                    <select name="rating" style="background: black; color: white">
                                        <option value="asc"> по возрастанию</option>
                                        <option value="desc"> по убыванию</option>
                                    </select>
                                </label><br>    
                                <label>
                                    году выпуска
                                    <select name="year" style="background: black; color: white">
                                        <option value="asc"> по возрастанию</option>
                                        <option value="desc"> по убыванию</option>
                                    </select>
                                </label>
                            </fieldset>
                        </form>

                    </div>
                    <div class="img-box1 photo" style="margin-top: 20px">
                        <img src="/resources/images/photo/2.jpg" alt="photo" width="58" height="83"/>

                        <div>
                            Чудо-женщина<br>
                            США, реж. Пэтти Дженкинс<br>
                            фантастика, фэнтези, боевик<br>
                            Галь Гадот, Крис Пайн<br>
                        </div>
                    </div>
                    <hr>

                    <div class="img-box1 photo" style="margin-top: 20px">
                        <img src="/resources/images/photo/2.jpg" alt="photo" width="58" height="83"/>

                        <div>
                            Чудо-женщина<br>
                            США, реж. Пэтти Дженкинс<br>
                            фантастика, фэнтези, боевик<br>
                            Галь Гадот, Крис Пайн<br>
                        </div>
                    </div>
                    <hr>

                    <div class="img-box1 photo" style="margin-top: 20px">
                        <img src="/resources/images/photo/2.jpg" alt="photo" width="58" height="83"/>

                        <div>
                            Чудо-женщина<br>
                            США, реж. Пэтти Дженкинс<br>
                            фантастика, фэнтези, боевик<br>
                            Галь Гадот, Крис Пайн<br>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div></#macro>