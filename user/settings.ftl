<@ww.actionerror />
<@ww.actionmessage />

<H3>Settings </H3>

	<@ww.form method="post" namespace="/user" action="settings" validate="false" >
		<@ww.checkbox label="Show chess board before branching " name="showBeforeBranch"  value="${user.showBeforeBranch.toString()}" />
		<@ww.checkbox label="Show chess board before important move" name="showBeforeImportantMove"  value="${user.showBeforeImportantMove.toString()}"  />
		<@ww.checkbox label="Show chess board before a comment" name="showBeforeComment" value="${user.showBeforeComment.toString()}"  />
		<@ww.checkbox label="Show big board" name="showBigBoard" value="${user.showBigBoard.toString()}"  />
		<@ww.textfield name="email" size="60" label="Email address" />	
		<@ww.password name="password" label="Change Password" required="true"/>	
		<@ww.password name="password2" label="Repeat password" required="true"/>	

		<@ww.hidden name="rulesSet" value="true" />
		<@ww.submit/>
	</@ww.form>
		
