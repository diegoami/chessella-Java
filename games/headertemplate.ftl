
<#assign gameInfo = currentGame.gameInfo />
<#setting number_format="####" />

<h1>
${gameInfo.players[0].name?default("")} 
	<#if gameInfo.whiteRating?exists>
	( ${gameInfo.whiteRating?default("0")} ) 
	</#if>
	- ${gameInfo.players[1].name?default("")} 
  <#if gameInfo.blackRating?exists>

	( ${gameInfo.blackRating?default("0")} ) 
  </#if>	
	[ ${gameInfo.ECO?default("")} ] 
<#if gameInfo["Annotator"]?exists>
<i> - [ ${gameInfo["Annotator"]?default("")} ]</i> 
</#if>

</strong> <br>

 ${gameInfo.event?default("")} - ${gameInfo.site?default("")}
 
 <#if ( gameInfo.round?exists ) >
 	${gameInfo.round}
 </#if>

	,
  ${gameInfo.dateString} - <B> ${gameInfo.result?default("")} </B> 

		</p>

</h1>

