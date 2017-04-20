<#if canRead?exists && canRead>

<PRE>
  <#if editable >
	<@ww.form method="post" namespace="/games" action="modifyPgnStringById" >
		<@ww.textarea rows=20 cols=80 name="pgnString" label="Modify Game"/>
		<@ww.hidden name="id" />	
		<@ww.checkbox name="publicgame" label="Public ?"/>	
	

		<@ww.submit/>
	</@ww.form>

	
  <#else>
${encodedPgnString}	
  </#if>
<#else>
  This chess game is private	
</#if>
</PRE>
<HR>
<blockquote>
URL for this game : <B> ${req.requestURL}?${req.queryString} </B> 
</blockquote>
 <blockquote>
<A HREF="showgame.action?${req.queryString}">Back to Game </A>
	
</blockquote>	

