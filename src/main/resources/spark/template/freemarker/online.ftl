<h3>Players Online</h3>
    <#if currentUser??>
        <ul>
            <#list playerSet as player>
                <#if player != currentUser>
                    <li><a href="/game?opponent=${player}">${player}</a></li>
                </#if>
            </#list>
        </ul>
        <#if count == 1>
            <p>You are the only player.</p>
        </#if>
    <#else>
        <p>There <#if (count > 1)>are<#elseif count == 0>are<#else>is</#if> currently ${count} player<#if (count > 1)>s<#elseif count == 0>s</#if></p>
    </#if>