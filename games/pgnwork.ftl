<@ww.actionerror />
<h2>Enter chess games (Max ${user.maxgames})</h2>
<P>
	<@ww.form method="post" namespace="/games" action="savepgn" >
		 <@ww.textarea rows=20 cols=80 name="pgnString" label="PGN" />	
         <@ww.textfield name="tag" size="60" label="Tag" />
		 <@ww.checkbox name="publicgame" label="Public ?" value="true" />	
	
		<@ww.submit/>
	</@ww.form>
</P>		