 <div class="navigation">
  <#if currentUser??>
    <a href="/">my home</a> |
    <form id="signout" action="/signout" method="post">
      <a href="/signout" onclick="event.preventDefault(); signout.submit();">sign out [${currentUser.name}] | </a>
    </form>
    <a href="/replay">replay</a>
  <#else>
    <a href="/signin">sign in</a>
  </#if>
 </div>
