<div class = "navigation">
    <form action="./signin" method = "POST">
        <input name="username" />
        <br/>
        <#if notValid>
            ${invalidMessage}
        </#if>
        <br/>
        <button type="submit">Enter</button>
    </form>
</div>
