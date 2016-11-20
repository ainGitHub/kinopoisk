<!doctype html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8">
    <title>Blog | It Fits</title>
    <meta name="HandheldFriendly" content="True"/>
    <meta name="MobileOptimized" content="320"/>
    <meta content="minimum-scale=1.0, width=device-width, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <meta name="description" content="">
    <link rel="stylesheet" href="/resources/assets/css/style.css">
    <link href="http://fonts.googleapis.com/css?family=Oswald:regular" rel="stylesheet" type="text/css">
    <link href='http://fonts.googleapis.com/css?family=Junge' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="/resources/assets/fonts/raphaelicons.css">
    <link rel="stylesheet" href="/resources/assets/css/main.css">
    <script src="/resources/assets/js/libs/modernizr-2.5.2.min.js"></script>
</head>
<!--[if lt IE 7]>
<body class="ie6 oldies"> <![endif]-->
<!--[if IE 7]>
<body class="ie7 oldies"> <![endif]-->
<!--[if IE 8]>
<body class="ie8 oldies"> <![endif]-->
<!--[if gt IE 8]><!-->
<body><!--<![endif]-->

<!--[if lt IE 7]><p class=chromeframe>Your browser is <em>ancient!</em> <a href="http://browsehappy.com/">Upgrade to a
    different browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a>
    to experience this site.</p><![endif]-->
<header class="clearfix">
    <div class="container">
        <a id="logo" href="index.html">It Fits</a>
        <ul class="social-icons">
            <li><a href="http://www.facebook.com/blog.cssjuntion" class="icon flip">^</a></li>
            <li><a href="" class="icon">T</a></li>
            <li><a href="http://www.twitter.com/cssjunction" class="icon">^</a></li>
        </ul>
        <nav class="clearfix">
            <ul role="navigation">
                <li>
                    <a href="index.html"><span class="icon">S</span>Home</a>
                </li>
                <li>
                    <a href="portfolio.html"><span class="icon">Û</span>Portfolio</a>
                </li>
                <li>
                    <a href="page.html"><span class="icon">E</span>Page</a>
                </li>
                <li>
                    <a href="blog.html" class="activePage"><span class="icon">E</span>Blog</a>
                </li>
                <li>
                    <a href="contact.html"><span class="icon">M</span>Contact us</a>
                </li>
            </ul>
        </nav>
    </div>
</header>
<section role="banner">
    <hgroup>
        <h1>Pellentesque vestibulum erat eget urna pretium ultricies.</h1>
        <h2>Fusce hendrerit dui eget nisl malesuada vitae malesuada</h2>
    </hgroup>
    <article role="main" class="clearfix">
        <div class="post">
            <h2>Page headline goes here...</h2>
            <p>Cras aliquet accumsan molestie. Morbi purus odio, faucibus sit amet elementum in, interdum eu ipsum.
                Donec cursus pellentesque mauris vitae ultricies. Donec lacinia nunc in nisl hendrerit scelerisque. </p>
            <p></p>
        </div>
    </article>
</section> <!-- // banner ends -->
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
    <#if films??>
        <ul class="post-list">
            <#list films as film>
                <li>
                    <h2><a href="page.html">${film.name}</a></h2>
                    <p class="meta">On April 26 | By: <a href="#">$hekh@r d-Ziner</a></p>
                    <img src="/resources/images/photo/2.jpg" alt="Lorem ipsum dolor...">
                    <p>  <#if film.description??>
                                ${film.description}
                            </#if></p>
                    <p><a href="page.html" class="more-link">Reading continued <span class="icon">:</span></a></p>

                    <div>
                        год: 2017 <br>
                        страна: США <br>
                        режиссер: Пэтти Дженкинс <br>
                        сценарий: Аллан Хейнберг, Джефф Джонс, Уильям М. Марстон <br>
                        жанр: фантастика, фэнтези, боевик, приключения <br>
                        время: - <br>
                        возрастное ограничение:
                    </div>
                </li>
            </#list>
        </ul>
    <#else>
        <h3><span>Данные не найдены</span></h3>
    </#if>
    </article>
</section>
<footer role="contentinfo">
    <p>
        <span class="left">CSS Junction &copy; - 2012 | Released under Creative Common License. <a href="#">Goto Top</a></span>
        <a href="index.html">HOME</a> | <a href="portfolio.html">PORTFOLIO</a> | <a href="page.html">PAGE</a> | <a
            href="blog.html">BLOG</a> | <a href="contact.html">CONTACT US</a>
    </p>
</footer>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="/resources/assets/js/libs/jquery-1.7.1.min.js"><\/script>')</script>
<script src="/resources/assets/js/script.js"></script>
</body>
</html>