<A name="top"></A>
<#if canRead?exists && canRead >

	<#assign gameList = chessGameGroup.gameList />
	
	<#list gameList as currentGame >
	
	<#include "headertemplate.ftl" />
	
	
	<#include "notationtemplate.ftl" />
	
	
	</#list>


<HR>
<blockquote>
URL for this chess game : <B> ${req.requestURL}?id=${stringId} </B> 
	
</blockquote>
<blockquote>
																									<A HREF="showpgn.action?id=${stringId}">
<#if editable> EDIT </#if> PGN FILE FOR this chess game

</A>&nbsp;/&nbsp;<A href="#top">Back to top of the chess game</A>
	
</blockquote>
<#else>
	This chess game is private 
</#if>


