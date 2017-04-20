
<@ww.actionerror />


	<@ww.form method="post" namespace="/games" action="uploadpgn"  enctype="multipart/form-data"> 
		  <@ww.file name="upload" size="100" label="Upload PGN file with max (Max ${user.maxgames} games)"/>	
          <@ww.textfield name="tag" size="80" label="Tag" />
		<@ww.submit/>
	</@ww.form>
		