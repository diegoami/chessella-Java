<blockquote>
<TABLE><TR><TD>
<#if user?exists && user.showBigBoard>
	<#assign squareSize=50 />
	<#assign boardSize=440 />
	<#assign internalSize=400 />
	
		
<#else>
	<#assign squareSize=40 />
	<#assign boardSize=350 />
	<#assign internalSize=4 />
	
	
</#if>	 	
<#if !flipboard?exists>
	<#assign flipboard="" />
</#if>
<table id='ChessWorldChessBoard'  WhiteBlank='${base}/chessworld/${squareSize}blank.gif' BlackBlank='${base}/chessworld/${squareSize}blank.gif' border='0' cellspacing='0' cellpadding='0' width='${boardSize}' height='${boardSize}' background='${base}/boards/${squareSize}brownjboard${flipboard}.gif'><TR><TD align=center valign=middle><#include "chessnetboard.ftl"/></TD></TR></TABLE>
</TD><TD>
<div id="moves">
<table ><tr><td>

<#if move?exists >
	
  &nbsp;
  <B>

	<#if firstboard?exists && firstboard >
	   <#if move.blackMove>Black <#else>White </#if> to move
	<#else>
	  ${move.moveTextWithNumber}
	</#if>
  </B>
</#if>

</td></tr></table>
</div>

</TD></TR>

<TR>


</TR></TABLE>
</P>
</blockquote>

