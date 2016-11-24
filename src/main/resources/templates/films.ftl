<#-- @ftlvariable name="films" type="java.util.List<ru.dz.entity.Film>" -->
<#include "temp/mainTemplate.ftl">
<@main_template title="Поиск"/>

<#macro banner>
    <#include "temp/searching.ftl" />
</#macro>

<#macro container>
<section class="container clearfix">
    <aside role="complementary">
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
            <#--<p class="meta">On April 26 | By: <a href="#">$hekh@r d-Ziner</a></p>-->
                <img src="${film.image!}" alt="Lorem ipsum dolor...">
                <p style="font-size: 16px">${film.description!}</p>
                <p><a href="page.html" class="more-link">Reading continued <span class="icon">:</span></a></p>
            </li>
        </#list>
        </ul>
    <#else><h3 align="center">Данные не найдены</h3>
    </#if>
    </article>
</section>
</#macro>
