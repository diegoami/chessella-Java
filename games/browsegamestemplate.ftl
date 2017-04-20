<#setting number_format="####" />

<#assign display=JspTaglibs["/WEB-INF/displaytag.tld"] />
<#if gameList?exists>
	<@ww.set name="games" value="gameList" scope="session"/>
	<@ww.set name="gameIds" value="gameIdList" scope="session"/>
	
	<#if user?exists> 
		<#if onlyMine?exists >
			<h2>These are your chess games, ${user.username} !  </h2>
			<#assign loggedusername = user.username />
		<#else>
			<h2>Search Result   </h2>
		</#if>
		 
	<#else>
			<h2>Search Result   </h2>
							
	</#if>
	
	<#if gameList.size() = 500 > 
	  <h3>More results were returned , please refine your search criteria</h3>
	  <BR>
	</#if>
	<#if games.empty > 
		  
	  <h3>No chess games found</h3>
	<#else>
		<div id="itsthetable">
		
    <@display.table name=games pagesize=20 sort="list" id="game" >
	<@ww.set name="currGame" value="game" scope="page"/>
		
		<@display.column property="id" sortable=true href="showgame.action" paramId="id" />
		  <@display.column property="white" sortable=true href="showgame.action" paramId="id" paramProperty="id" />
		  <@display.column property="black" sortable=true href="showgame.action" paramId="id" paramProperty="id" />
		  <@display.column property="date"  sortable=true href="showgame.action" paramId="id"  paramProperty="id" />
		  <@display.column property="result"  sortable=true href="showgame.action" paramId="id"  paramProperty="id" />
		  <@display.column property="submitter" sortable=true title="Submitter" href="showgame.action" paramId="id" paramProperty="id" />
		  <@display.column property="annotator" sortable=true href="showgame.action" paramId="id" paramProperty="id" />
		  <@display.column property="event"  sortable=true href="showgame.action" paramId="id"  paramProperty="id" />
		  <@display.column property="site" sortable=true href="showgame.action" paramId="id"  paramProperty="id" />
		  <@display.column property="eco"  sortable=true href="showgame.action" paramId="id"  paramProperty="id" />
		  <@display.column property="tags"  sortable=true href="showgame.action" paramId="id"  paramProperty="id" />
		  <#if user?exists >
			<@display.column  title="Public" paramId="id"  paramProperty="id" >
			  <#if game?exists>	
				<#if game.submitter = loggedusername >
				  <A HREF="javascript:switchGame(${game.id});">
			  	    <DIV id="public_${game.id}">			
					  <#if game.publicgame >
					    <IMG SRC='public_co.gif' BORDER=0>
					  <#else>
					    <IMG SRC='private_co.gif' BORDER=0 >
					  </#if>
				    </DIV>	  
				  </A>	  
		        </#if>
			  </#if> 				
       
	  
			</@display.column> 
	      </#if> 		  

			
          
		  <#if user?exists >
			<@display.column title="Delete" paramId="id"  paramProperty="id" >
		
				<#if game?exists>	
				  <#if game.submitter = loggedusername >
					<A HREF="javascript:deleteGame(${game.id});">
   				     <DIV id="delete_${game.id}">		
					  <#if game.deleted >
					    <IMG SRC='deleted.gif' BORDER=0>
					  <#else>
					    <IMG SRC='delete.gif' BORDER=0 >
						  
					  </#if>
				     </DIV>
			
					</A>
				  </#if>
				</#if>	  
		
			</@display.column>
		</#if>
		
		</@display.table>
	
	  </div>
				
	</#if>
</#if>
