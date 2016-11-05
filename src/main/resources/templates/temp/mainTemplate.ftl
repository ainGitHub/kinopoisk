<#macro main_template styles=[] scripts=[] title="Кинопоиск">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>${title}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="description" content="Place your description here"/>
    <meta name="keywords" content="put, your, keyword, here"/>
    <meta name="author" content="Templates.com - website templates provider"/>
    <link href="/resources/css/style.css" rel="stylesheet" type="text/css"/>
    <#list styles as css>
        <link rel="stylesheet" href="/resources/${css}" type="text/css" />
    </#list>
    <script src="/resources/js/jquery-1.4.2.min.js" type="text/javascript"></script>
    <script src="/resources/js/cufon-yui.js" type="text/javascript"></script>
    <script src="/resources/js/cufon-replace.js" type="text/javascript"></script>
    <#--<script src="/resources/js/Gill_Sans_400.font.js" type="text/javascript"></script>-->
    <script src="/resources/js/script.js" type="text/javascript"></script>
    <#list scripts as js>
        <script src="/resources/js/${js}" type="text/javascript" defer></script>
    </#list>
    <!--[if lt IE 7]>
    <script type="text/javascript" src="/resources/js/ie_png.js"></script>
    <script type="text/javascript">ie_png.fix('.png, .link1 span, .link1');</script>
    <link href="/resources/css/ie6.css" rel="stylesheet" type="text/css"/>
    <![endif]-->


</head>

<body id="page1">
<div class="tail-top">
    <div class="tail-bottom">
        <div id="main">
            <#include "header.ftl" />
            <@body />
        </div>

    </div>
</div>

<script type="text/javascript"> Cufon.now(); </script>
</body>
</html>
</#macro>