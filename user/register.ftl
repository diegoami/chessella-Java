<@ww.actionerror />
<@ww.actionmessage />

<H3>New user </H3>

	<@ww.form method="post" namespace="/user" action="register"  >
		<@ww.textfield name="username" label="User name" required="true"/>	
		<@ww.password name="password" label="Password" required="true"/>	
		<@ww.password name="password2" label="Repeat password" required="true"/>	
		<@ww.textfield name="email" label="Email" />	

		<@ww.submit/><@ww.reset/>
	</@ww.form>
<P>
<B>Without an email address, I might set your games to private or even delete them without notice!</B>
</P>
<P>
<I>Register if you want to be able to add chess games</I>
</P>
		
