<div>
    <h3><span>Поиск</span></h3>
    <div>
        <form class="search-form-1" action="/search/films/name" method="get">
        <fieldset>
            <div class="field">
                <label>Название:</label>
                <input type="text" name="name"/>
            </div>
            <input type="submit" class="link2" value="Поиск по названию">
        </fieldset>
        </form>
    </div>
    <br>
    <div>
        <form class="search-form-1" action="/search/films/description" method="get">
            <fieldset>
                <div class="field">
                    <label>Описание:</label>
                    <input type="text" name="description"/>
                </div>
                <input type="submit" class="link2" value="Поиск по описанию">
            </fieldset>
        </form>
    </div>
<#--  <div class="field">
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
  </div>-->


    <#--<h3><span>Сортировать по</span></h3>-->
    <#--<form action="">-->
        <#--<fieldset>-->
            <#--<label>-->
                <#--релевантности-->
                <#--<select name="rel" style="background: black; color: white">-->
                    <#--<option value="asc"> по возрастанию</option>-->
                    <#--<option value="desc"> по убыванию</option>-->
                <#--</select>-->
            <#--</label><br>-->
            <#--<label>-->
                <#--рейтингу-->
                <#--<select name="rating" style="background: black; color: white">-->
                    <#--<option value="asc"> по возрастанию</option>-->
                    <#--<option value="desc"> по убыванию</option>-->
                <#--</select>-->
            <#--</label><br>-->
            <#--<label>-->
                <#--году выпуска-->
                <#--<select name="year" style="background: black; color: white">-->
                    <#--<option value="asc"> по возрастанию</option>-->
                    <#--<option value="desc"> по убыванию</option>-->
                <#--</select>-->
            <#--</label>-->
        <#--</fieldset>-->
    <#--</form>-->

</div>