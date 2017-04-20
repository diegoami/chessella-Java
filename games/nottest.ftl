<#assign shownBoard = false />
<#macro showVariations game variationStartMoves> 
	<#list variationStartMoves  as  variationStartMove >
		    <#assign allMovesInVariation = moveTree.getAllMovesInVariation(variationStartMove) />
		(	<#list allMovesInVariation  as  moveInVariation >
				<@showMove move=moveInVariation game=game mainFlag=false />
			</#list> ) 
	</#list>
</#macro>



<#macro showMove game move mainFlag=true >

	<#if move.preNotation?exists && !move.preNotation.empty >
		${move.preNotation.text}
	</#if>
	<#if move.prenags?exists >
		<#list move.preNags as nag >
			<IMG SRC="sym/n${nag}${gifsuf}" >
		</#list>
	</#if>
	
	<#if mainFlag > <B> </#if>

<A HREF="javascript:showBoard('${divString}','${gameId}','${move.moveString}', '${move.number}','${move.blackMove?string}', '${move.depth}');">
	<#if shownBoard >
		${move.moveTextWithNumber}
	<#else>
		${move.moveText}
	</#if>
</A>&nbsp;
	<#if mainFlag > </B> </#if>

	<#if move.comment?exists && !move.comment.empty >
		 ${move.comment.text} 
	</#if>
	
	
		
	<#if move.nags?exists >
		<#list move.nags as nag > 
		
			<IMG SRC="sym/n${nag}${gifsuf}" >
		</#list>
	</#if>
	<#if move.prevMove?exists >
	   <#if move.prevMove.variationList?exists >
		  <@showVariations game=game variationStartMoves=move.prevMove.variationList />
	   </#if> 
	</#if> 
	  
<#if moveTree.willShowBoard(move, showBoardRule) >
<#assign board = velocityBoardFactory.createBoardFromFenString(game, move.move) />
<#include "chessnetboardtemplate.ftl" />
<#assign shownBoard = true />

</#if>

</#macro> 	
<BR>
<BR>
<P class="notation">

<#assign moveTree = moveTreeFiller.retrieveMoveTree(currentGame) />
<#assign board = velocityBoardFactory.createBoardFromFenString(currentGame, moveTree.firstMove.move) />
<#assign move = moveTree.firstMove />
<#include "chessnetboardtemplate.ftl" />

<#list moveTree.allMoves as move> 
  <@showMove move=move game=currentGame />
</#list>


</CENTER>
</P>
