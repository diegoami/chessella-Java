<P>The following is a PGN export of chess games.
Copy and paste into a text file, and save it with the extension "PGN"
</P>

<PRE>
<#list games as game >
<#if game.pgnstring?exists>
${game.encodedPgnstring}
</#if>
</#list>	
</PRE>


