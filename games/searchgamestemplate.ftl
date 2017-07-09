<@ww.actionerror />
<H3>Search in ${gamesCount} ( mostly annotated ) chess games!  </H3>
	<#assign resultTypes = ["1-0", "0-1", "1/2-1/2", "*"]/>

	
	<@ww.form method="post" namespace="/games" action="searchgames"  >
		<@ww.textfield name="gameSearchCriteria.white" label="White"/>	
		<@ww.textfield name="gameSearchCriteria.black" label="Black"/>	
		<@ww.checkbox name="gameSearchCriteria.ignoreColor" label="Ignore color"/>	
		<@ww.datepicker name="gameSearchCriteria.afterDate" label="After"/>	
		<@ww.datepicker name="gameSearchCriteria.beforeDate" label="Before"/>	
		<@ww.textfield  name="gameSearchCriteria.afterECO" label="ECO from"/>	
		<@ww.textfield  name="gameSearchCriteria.beforeECO" label="ECO to "/>	


		<@ww.select label="Result" name="gameSearchCriteria.resultKey" list="gameSearchCriteria.resultMap"/>
		<@ww.select name="gameSearchCriteria.submitter" label="Submitter" list="allUsers"/>
		<@ww.textfield name="gameSearchCriteria.event" label="Event"/>	
		<@ww.textfield name="gameSearchCriteria.site" label="Site"/>	
	    <@ww.textfield name="gameSearchCriteria.tags" label="Tags"/>

		<@ww.hidden name="gameSearchCriteria.searchValid" value="notNull" />	
		<@ww.submit/>
	</@ww.form>
<#if (!user?exists) >
<I>Register to upload your chess games ! </I>
<#else>
<B></B>
</#if>
