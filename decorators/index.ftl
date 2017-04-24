
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />


<link rel="stylesheet" type="text/css" href="${base}/css/sinorca-screen.css" type="text/css">

<link rel="stylesheet" type="text/css" href="${base}/css/styles.css" type="text/css">
<link rel="stylesheet" type="text/css" href="${base}/css/tablegamelist.css" type="text/css">

<@ww.head theme="ajax"/>

<script type="text/javascript" src="${base}/dwr-js/BoardForwarder.js"> </script>
<script type="text/javascript" src="${base}/dwr-js/GameSetter.js"> </script>
<script type="text/javascript" src="${base}/dwr-js/GameTableSetter.js"> </script>
<script type="text/javascript" src="${base}/dwr-js/engine.js"> </script>
<script type="text/javascript" src="${base}/dwr-js/util.js"> </script>
<script type="text/javascript" src="${base}/dwr-js/generic.js"> </script>
<script type="text/javascript" src="${base}/dwr-js/ChessPublisher.js"> </script>
<script type="text/javascript">
//function emptyHandler(message) {
//}	

callOnLoad(init);
</script>

    <title>Chessella - Share and learn from chess games</title>
  </head>

  <body>

    <div id="header">
      <div class="superHeader">
        <div class="left">
          <span class="doNotDisplay"></span>
        </div>
        <div class="right">
          <span class="doNotDisplay"></span>
        </div>
      </div>

      <div class="midHeader">

        <h1 class="headerTitle">Chess-ella</h1>
		<div class="left">
			 <h2>Share and learn from chess games</h2>
		</div>
	  </div>
      <div class="subHeader">
        <span class="doNotDisplay">Navigation:</span>
		  
        <#if prevGameId?exists >	
		  &nbsp;
		  |
	      <A HREF="${base}/games/showgame.action?id=${prevGameId}">Prev Game</A>&nbsp;
        </#if>
		  
        <#if nextGameId?exists >	
		   |
	      <A HREF="${base}/games/showgame.action?id=${nextGameId}">Next Game</A>
        </#if>
		  
       
	    <#if (id?exists && id >= 1) >
		  |
		  <#if editable?exists && editable >
		 <a href="${base}/games/showpgn.action?id=${stringId}" title="PGN">Edit Game</a> 

		  <#else>
          <a href="${base}/games/showpgn.action?id=${stringId}" title="PGN">Show PGN</a> 
		  </#if>
		  |
          <a href="${base}/games/showgame.action?id=${stringId}" title="View Game">View Game</a>
	    </#if>
		 <#if games?exists >	
		  |
          <A HREF="${base}/games/export.action">Export</A>
		  <SPAN ID="deletecaption"
		  
			     <#if user?exists && existsGamesDeleted?exists && existsGamesDeleted >
			         style='visibility:visible'
			     <#else>
					  style='visibility:hidden'
			     </#if>
			  >
						|
			  <A HREF="${base}/games/deletegames.action">Remove deleted games</A>
	      </SPAN>
        </#if>
		  

      </div>
    </div>
    <div id="side-bar">
      <div>
        <ul>
	      <li><a href="${base}/main.action">&rsaquo; Home</a></li>
	      <li><a href="${base}/links.action">&rsaquo; Links</a></li>
	        <#if user?exists >
		      <li><a href="${base}/user/logout.action">&rsaquo; Logout ${user.username}</a></li>
              <li><a href="${base}/user/settings.action">&rsaquo; Settings</a></li>
 	        <#else>
              <li><a href="${base}/user/loginform.action">&rsaquo; Login </a></li>
              <li><a href="${base}/user/registerform.action">&rsaquo; Register</a></li>
        	</#if>

        	<#if user?exists >
              <li><a href="${base}/games/mygames.action" title="My games">&rsaquo; My games</a></li>
              <li><a href="${base}/games/savepgn.action" title="Add games">&rsaquo; Add games</a></li>
              <li><a href="${base}/games/savelink.action" title="Get PGN games from URL">&rsaquo; Get games from URL</a></li>
			
        	</#if> 

		   <li><a href="${base}/games/searchgamesform.action" title="Search games">&rsaquo; Search games</a></li>
              <li><a href="${base}/games/lastgames.action" title="Latest games">&rsaquo; Latest games</a></li>

	    </ul>
		  
      </div>

    

      <div class="lighterBackground">
        <p class="sideBarTitle">Welcome !</p>
        <span class="sideBarText">
          Welcome to Chessella !
        </span>
        <span class="sideBarText">
			A place to share, browse and learn from chess games 
		</span>
      </div>
    
 
    </div>

    <div id="main-copy"> 
      ${body}
    </div> 
    
    <div id="footer">
      <div class="left">
        E-mail:&nbsp;<a href="mailto:support@chessella.com" title="Email me">support@chessella.com</a><br />
        <a href="${base}/main.action" class="doNotPrint">Contact Us</a>
      </div>

      <br class="doNotDisplay doNotPrint" />

      <div class="right">
        This design is from Sinorca.<br />
		<a href="http://www.amicabile.com">Diego Amicabile </a>
      </div>
    </div>
  </body>
</html>