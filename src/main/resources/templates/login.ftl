<#include "temp/mainTemplate.ftl">
<@main_template title="Вход" scripts=[]/>

<#macro body>
<div id="content">
    <div class="line-hor"></div>
    <div class="box">
        <div class="border-right">
            <div class="border-left">
                <div class="inner">
                    <h3 style="margin-left: 400px"><span>Вход</span></h3>

                    <form id="contacts-form" action="">
                        <fieldset>
                            <div class="field">
                                <label>Логин:</label>
                                <input type="text" name="login"/>
                            </div>
                            <div class="field">
                                <label>Пароль:</label>
                                <input type="text" name="pass"/>
                            </div>
                            <div class="wrapper">
                                <a href="#" class="link2" onclick="document.getElementById('contacts-form').submit()">
								<span>
									<span>Войти</span>
								</span>
                                </a>
                            </div>
                        </fieldset>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div></#macro>
