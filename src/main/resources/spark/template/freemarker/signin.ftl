<!DOCTYPE html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
  <meta http-equiv="refresh" content="10">
  <title>Web Checkers | ${title}</title>
  <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>
    <div class = "page">
    <h1>Web Checkers | ${title}</h1>
        <center>
            <p>Enter a Valid Name!</p>
            <form action="./signin" method = "POST">
                <input type="text" name="username" />
                <br/>
                <#if notValid>
                    <p>${invalidMessage}</p>
                <#else>
                    <br/>
                </#if>
                <button type="submit">Enter</button>
                <br/>
                <br/>
            </form>
        </center>
    </div>
</body>
</html>
