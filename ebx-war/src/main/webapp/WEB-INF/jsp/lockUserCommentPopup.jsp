<div id="userCommentsLockDialog" style="border:0px">
	<input type="hidden" id="variableIdForLockFlow" name="variableIdForLockFlow" value=""/>
	<input type="hidden" id="variableDescForLockFlow" name="variableDescForLockFlow" value=""/>
	<input type="hidden" id="systemForLockFlow" name="systemForLockFlow" value=""/>
	<input type="hidden" id="actionForLockFlow" name="actionForLockFlow" value=""/>
	<input type="hidden" id="userForLockFlow" name="userForLockFlow" value=""/>
	<table id="lockCommentsTable1" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5">          
	  <tr class="">
        <td ><textarea name="lockComments"  id="lockComments" 
		rows="5" cols="77"></textarea></td>
	  </tr> 			
	</table>
	<table border="1" align="center" cellpadding="0" cellspacing="0" class="footerTable">
      <tr>       
        <td >
       		<a href="#" onclick="saveUserCommentsForLock();">
       			<img id="buttonLock" src="${imageDir}/Lock_but.gif" alt="Lock"  title="Lock"/>
       		</a>
		</td>		
      </tr>
	</table>	
</div>
<script type="text/javascript">
	function saveUserCommentsForLock() {
		var userComments = $("#lockComments").val();
		userComments = $.trim(userComments);
		if (userComments.length <= 0) {
			var message = "Please enter user comments to proceed";
			addErrorToNotificationTray(message);
			openTrayNotification();	
		} else if (userComments.length > 250) {
			var message = "Text length cannot be greater than 250 for user comments";			
			addErrorToNotificationTray(message);
			openTrayNotification();
		} else {
			var varId = $("#variableIdForLockFlow").val();
			var varDesc = $("#variableDescForLockFlow").val();
			var system = $("#systemForLockFlow").val();
			var user = $("#userforlockflow").val();
			
			var action = $("#actionForLockFlow").val();
			if (action == 'Lock') {
				unLockAuditLock(varId, system, user,varDesc, userComments);
			} else if (action == 'Unlock') {
				lockAuditLock(varId, system, user,varDesc, userComments);
			} else if (action == 'UnlockLocate') {
				lockAuditLockFromLocate(varId, system, user,varDesc, userComments);
			} else if (action == 'LockLocate') {
				unLockAuditLockFromLocate(varId, system, user,varDesc, userComments);
			} else if (action == 'UnlockView') {
				lockAuditLockView(varId, system, user,varDesc, userComments);
			} else if (action == 'LockView') {
				unLockAuditLockView(varId, system, user,varDesc, userComments);
			}
			$("#lockusercomment").dialog('close');
		}
	}
</script>