<!DOCTYPE html>

 <head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
   <meta http-equiv="refresh" content="10">
   <title>Web Checkers | ${title}</title>
   <link rel="stylesheet" type="text/css" href="/css/style.css">
 </head>

 <body>
 <div class="page">

   <h1>Web Checkers | ${title}</h1>

   <!-- Provide a navigation bar -->
   <#include "nav-bar.ftl" />

   <div class="body">
    <h3>Played Games</h3>
        <ul>
            <#list gameList as game>
                 <li><a href="/replay/game?game=${game}">${game}</a></li>
            </#list>
        </ul>

   </div>

 </div>
 </body>

 </html>