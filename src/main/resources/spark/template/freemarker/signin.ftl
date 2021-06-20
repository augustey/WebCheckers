<!DOCTYPE html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
  <title>Web Checkers | ${title}</title>
  <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>
    <div class = "page">
    <h1>Web Checkers | ${title}</h1>
        <#if signedIn>
            <center>
                <#if currentUser??>
                    <p>You have successfully signed in ${currentUser.name}</p>
                </#if>
                <form action="./" method="GET">
                    <button type="submit">Return To Home</button>
                    <br/>
                    <br/>
                </form>
        <#else>
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
        </#if>
    </div>
</body>
</html>
