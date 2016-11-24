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
    <link  href="http://fonts.googleapis.com/css?family=Oswald:regular" rel="stylesheet" type="text/css" >
    <link rel="stylesheet" href="/resources/css/style.css">
    <link href='http://fonts.googleapis.com/css?family=Junge' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="/resources/fonts/raphaelicons.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/myStyle.css">
    <#--<script src="/resources/js/libs/modernizr-2.5.2.min.js"></script>-->
    <#--<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>-->
    <#--<script>window.jQuery || document.write('<script src="/resources/js/libs/jquery-1.7.1.min.js"><\/script>')</script>-->
    <#--<script src="/resources/js/script.js"></script>-->
</head>
<body>

    <#include "header.ftl" />
<section role="banner">
    <hgroup></hgroup>
    <@banner/>
</section>
    <@container/>
</body>
</html>
</#macro>