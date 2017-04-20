<h2>Enter Url to retrieve games from </h2>

<@ww.actionerror />
<h3>Enter URL (Max ${user.maxgames} chess games)</h3>
	<@ww.form method="post" namespace="/games" action="savelink" >
		<@ww.textfield name="urlString" size="70" label="URL" />	
		<@ww.textfield name="tag" size="60" label="Tag" />
		<@ww.checkbox name="publicgame" label="Public ?" value="true" />	
        
		<@ww.submit/>
	</@ww.form>
		