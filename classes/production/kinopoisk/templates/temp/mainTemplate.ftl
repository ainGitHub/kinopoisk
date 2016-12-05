<#macro main_template title="Кинопоиск">
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8">
    <title>${title}</title>
    <meta name="HandheldFriendly" content="True"/>
    <meta name="MobileOptimized" content="320"/>
    <meta content="minimum-scale=1.0, width=device-width, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <meta name="description" content="">
    <script type="text/javascript" src="/resources/js/libs/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="/resources/js/libs/bootstrap.min.js"></script>
    <script type="text/javascript" src="/resources/js/validation.js"></script>
    <script type="text/javascript" src="/resources/js/rating.js"></script>

    <link rel="stylesheet" type="text/css" href="/resources/css/bootstrap.min.css"/>
    <link href="http://fonts.googleapis.com/css?family=Oswald:regular" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/resources/css/style.css">
    <link href='http://fonts.googleapis.com/css?family=Junge' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="/resources/fonts/raphaelicons.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/myStyle.css">
</head>
<body>
<#--<div class="wrapper flex-container">-->
<div class="wrapper">
    <#include "header.ftl" />
    <section role="banner">
        <hgroup>
            <h1>Кинопоиск - всегда только лучшие фильмы!</h1>
        </hgroup>
        <@banner/>
    </section>
    <@container/>
    <#include "footer.ftl"/>
</div>
</body>
</html>
</#macro>