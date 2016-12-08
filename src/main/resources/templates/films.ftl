<#-- @ftlvariable name="films" type="java.util.List<ru.dz.entity.Film>" -->
<#include "temp/mainTemplate.ftl">
<@main_template title="Поиск"/>

<#macro banner>

</#macro>

<#macro container>
<section role="main" id="root" class="container clearfix films">
<#--  <aside role="complementary">
        <h2>Addtional info</h2>
        <p>Vestibulum viverra <strong>consectetur enim vel rutrum</strong>. Mauris hendrerit sodales congue. Etiam
            malesuada nibh id sapien tincidunt vitae rhoncus nunc tincidunt.</p>
        <p>Curabitur posuere libero sit amet est tristique egestas. Duis porta tempor tristique. Nam in erat sed leo
            lacinia vestibulum vitae in ipsum.</p>
        <p><a href="#">Jump now <span class="icon">:</span></a></p>
    </aside>
    <article class="post content">
        <#if films?has_content>
            <ul class="post-list">
                <#list films as film>
                    <li>
                        <h2><a href="/film/${film.getId()}">${film.name}</a></h2>
                        <img src="${film.image!}" alt="Lorem ipsum dolor..." width="206" height="306">
                        <p style="font-size: 16px" class="description">${film.description!}</p>
                        <p><a href="/film/${film.getId()}" class="more-link">Продолжить<span class="icon">:</span></a>
                        </p>
                    </li>
                </#list>
            </ul>
        <#else><h3 align="center">Данные не найдены</h3>
        </#if>
    </article>-->
</section>
<script src="/resources/react/react.js"></script>
<script src="/resources/react/react-dom.js"></script>
<script src="/resources/react/browser.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/babel" src="/resources/index.js"></script>
</#macro>
