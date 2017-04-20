
<#macro showBoard gameInfo move board firstboard> 
	

		<#assign gameId=gameInfo["GameId"] />
	<#if !firstboard>	
		<#assign divString = "${gameId}_${move.moveString}_${move.number}_${move.blackMove?string}_${move.depth}_${move.boardHashCode}" />
	<#else>
		<#assign divString = "${gameId}_X_0_false_0_0" />
	</#if>		
		<BR>
		<P>


		<DIV ID="${divString}" onclick="javascript:resetBoard('${divString}');" >
		<#include "chessnetboardtemplate.ftl" />
		</DIV>
		
		<P>
<#if user?exists && user.showBigBoard>
			<#assign movepanelclass="movepanelbig" />
<#else>
			<#assign movepanelclass="movepanel" />

</#if>			
		<DIV id="${movepanelclass}">
		<TABLE border=0 cellpadding=1 cellspacing=0><TR>
<TD></TD>
<TD></TD>
			
<TD><input type=button value="I&lt;" width=30 style="width:30" id="btnBA" onClick="javascript:showMoveAllPrevious('${divString}');"></TD>
<TD><input type=button value="&lt;&lt;" width=30 style="width:30" id="btnB5" onClick="javascript:showMoveFivePrevious('${divString}');"></TD>
<TD><input type=button value="&lt;" width=30 style="width:30" id="btnB1" onClick="javascript:showMovePrevious('${divString}');"></TD>
<TD><input type=button value="&gt;" width=30 style="width:30" id="btnF1" onClick="javascript:showMoveForward('${divString}');"></TD>
<TD><input type=button value="&gt;&gt;" width=30 style="width:30" id="btnF5" onClick="javascript:showMoveFiveForward('${divString}');"></TD>
<TD><input type=button value="&gt;I" width=30 style="width:30" id="btnFA" onClick="javascript:showMoveAllForward('${divString}');"></TD>
<TD><input type=button value="Flip" width=60 style="width:60" id="btnFlip" onClick="javascript:flip('${divString}');"></TD>

</TR>

</TABLE>
</DIV>
</P>
<P>
	
</#macro>

<#macro showVariations game variationStartMoves> 
	<#list variationStartMoves  as  variationStartMove >
	<#if variationStartMove.depth = 1> [ <#else> ( </#if> 
		<@printMove move=variationStartMove mainFlag=false />
		    <#assign allMovesInVariation = moveTree.getAllMovesInVariation(variationStartMove) />
		    <#list allMovesInVariation  as  moveInVariation >
				<@showMove move=moveInVariation game=game mainFlag=false variationMoves=emptyMoves />
			</#list> 
	<#if variationStartMove.depth = 1> ] <#else> ) </#if> 
	</#list>
</#macro>

<#macro printMove move mainFlag=true afterBoard=false>

	<#if move.preNotation?exists && !move.preNotation.empty >
		${move.preNotation}
	</#if>
	<#if move.prenags?exists >
		<#list move.preNags as nag >
			<IMG SRC="${base}/sym/n${nag}.gif" >
		</#list>
	</#if>
	
	<#if mainFlag > <B> </#if>
	<#if (move.depth > 2) > <I> </#if>
<SPAN class="normal" id='S${gameId}_${move.moveString}_${move.number}_${move.blackMove?string}_${move.depth}_${move.boardHashCode}'>
<A HREF="javascript:showBoard('${divString}','${gameId}','${move.moveString}', '${move.number}','${move.blackMove?string}', '${move.depth}', '${move.boardHashCode}');">
	<#if afterBoard >
		${move.moveTextWithNumber}
	<#else >
		${move.moveText}
	</#if>
</A>
</SPAN>
		<#if mainFlag > </B> </#if>
	<#if (move.depth > 2) > </I> </#if>

	<#if move.comment?exists && !move.comment.empty >
		 ${move.comment} 
	</#if>
	<#if move.nags?exists >
		<#list move.nags as nag > 
		
			<IMG SRC="${base}/sym/n${nag}.gif" >
		</#list>
	</#if>
	
</#macro>

<#macro showOnlyMove game move mainFlag variationMoves>

								
	<#if move.firstInSequence>
    	<@printMove move=move mainFlag=mainFlag afterBoard=true />
	    <#if variationMoves?exists>
		   <@showVariations game=game variationStartMoves=variationMoves />
	    </#if>
		
	</#if>
			

				
    
    <#assign justPrintedBoard=moveTree.willShowBoard(move, showBoardRule) />
    <#if justPrintedBoard >
		<#assign board = velocityBoardFactory.createBoardFromFenString(game, move.move) />
		<@showBoard gameInfo=gameInfo move=move board=board firstboard=false/>
		
    </#if>

    <#if move.nextMove?exists >
      <@printMove move=move.nextMove mainFlag=mainFlag afterBoard=justPrintedBoard/>
    </#if>

</#macro>				
				
<#macro showMove game move mainFlag variationMoves>
	<@showOnlyMove move=move game=game mainFlag=mainFlag variationMoves=variationMoves/>
    <#if move.variationList?exists >
	   <@showVariations game=game variationStartMoves=move.variationList />
    </#if> 
	  
	

</#macro> 	

<P class="notation">

<#assign moveTree = moveTreePool.retrieveMoveTree(currentGame) />
<#assign board = velocityBoardFactory.createBoardFromFenString(currentGame) />
<#if moveTree?exists && moveTree.firstMove?exists >

	<#assign move = moveTree.firstMove />
	<#assign variationMoves = moveTree.variationMoves />
    <#assign emptyMoves = moveTree.emptyMoves />
	
		
	<#if move?exists >
		<@showBoard gameInfo=gameInfo move=move board=board firstboard=true/>
	       	  		
			<#list moveTree.allMoves as move> 
			  <@showMove move=move game=currentGame mainFlag=true variationMoves=variationMoves/>
	        </#list>
	</#if>
</#if>
<B>	${gameInfo.result} </B>



</CENTER>
</P>
				
<#if editable>
				
	<P>		
	<SPAN>
	<INPUT TYPE="text" id="tags" name="tags" size="80" value="${currentGameDataObj.tags}"/> 				
	<BUTTON name="tagsbutton" type="button" onclick="javascript:saveTags('${currentGameDataObj.id}');" value="Save Tag">	
		Save Tag
		</BUTTON> 
			</SPAN>
	</P>
</#if>