<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ユーザー登録</title>
    </head>
<%--     <script type="text/javascript">
    $(document).ready(function(){
    	var inputuMobileObj = $("#uMobile");
    	inputuMobileObj.keyup(function(){
    		var text = inputuMobileObj.val();
    	});
    	$("#uMobile").blur(function(){
    		var text = inputuMobileObj.val();
    		alert("aaa");
    		$.post("post.action?uMobile=" + text,null,function(response){
    			alert("aaa");
    			if(response!=(可以使用)){
    				document.getElementById("txtHint").innerHTML="<font color='red'>" + response +"</font>";
    			}else{
    				document.getElementById("txtHint").innerHTML="<font color='green'>" + response +"</font>";
    			}
    		});
    	});
    });
    </script> --%>

    <script type="text/javascript">
    function checkuMobile(uMobile){
     	var request;
    	var hint;
    	var uName = document.getElementById("uName").value;
       	var uMobile = document.getElementById("uMobile").value;
       	var uAge = document.getElementById("uAge").value;
    	hint = document.getElementById("txtHint");
    	aint = document.getElementById("txtAint");
    	bint = document.getElementById("txtBint");
    	
    	if(window.XMLHttpRequest){
    		request = new XMLHttpRequest();
    	}
    	else if(window.ActiveXObject){
    		request = new ActiveXObject("Microsoft.XMLHTTP");
    	}
    	
    	request.onreadystatechange = function press(){
/*     		if(request.readyState < 4){
    			hint.style.color = "blue";
    			hint.innerHTML="正在检测";
    		} */
    		if(request.readyState == 4){
    			if(request.status == 200){
    				if(request.responseText == "手机号已存在"){
    					hint.style.color = "red";
    					hint.innerHTML = "手机号已存在";
    					document.getElementById("submit").disabled=true;
    				}
    				else{
    					if(uMobile == ""){
    						hint.style.color = "red";
    						hint.innerHTML = "手机号不能为空";
    						document.getElementById("submit").disabled=true;
    					}else{
    						if(uMobile.length<11){
        						hint.style.color = "red";
        						hint.innerHTML = "请输入11位半角数字";
        						document.getElementById("submit").disabled=true;
    						}else{
    							hint.style.color="green";
    							hint.innerHTML="可以使用";
    							document.getElementById("submit").disabled=false;
    							}
    						}
    				}
    			}
    
    		}

    		
    	};
    	request.open("get","post.action?uMobile=" + uMobile, true);
    	request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    	request.send("");
    	
    };

/*        function check(){
     	var request;
       	var uName = document.getElementById("uName").value;
       	var uMobile = document.getElementById("uMobile").value;
       	var uAge = document.getElementById("uAge").value;
    	window.location.href="welcome.jsp"
    	if(window.XMLHttpRequest){
    		request = new XMLHttpRequest();
    	}
    	else if(window.ActiveXObject){
    		request = new ActiveXObject("Microsoft.XMLHTTP");
    	}
    	

    	request.open("post","post.action?NowIsAddAction=true", true);
    	request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    	request.send("&&uName=" + uName + "&&uMobile=" + uMobile + "&&uAge=" + uAge);

  
    };     */
    </script>

<%-- <script type="text/javascript">
$(document).ready(function(){
	var inputUserNameobj = $("#uMobile");
	$("#uMobile").blur(function(){
		var text = inputUserNameobj.val();
		$.post("post.action?uMobile="+text,null,function(response){
			if(response="用户名已存在"){
				doucument.getElementById("uMobile").focus();
				doucument.getElementById("txtHint").innerHTML="<font color='red'>"+response+"</font>";
			}else{
				if(doucument.getElementById("uMobile").value==""){
					doucument.getElementById("txtHint").innerHTML="<font color='red'>"+"用户名不能为空"+</font>;
					doucument.getElementById("uMobile").focus();
				}else{
					doucument.getElementById("txtHint").innerHTML="<font color='blue'>"+response+"</font>";
				}
			}
		});
	});
});</script> --%>

    <body>
		<s:form action="get">
        <s:form action="post">
            名前:<s:textfield name="uName" id="uName" required="required" /> <span id ="txtAint"> </span> <br/>
         携帯:<s:textfield name="uMobile" id="uMobile" onkeyup="checkuMobile(this)" maxlength="11" required="required" oninput="value=value.replace(/[^\d]/g,'')" /> <span id ="txtHint"> </span></br>
           年齢:<s:textfield name="uAge" id="uAge" maxlenth="3" required="required" oninput="value=value.replace(/[^\d]/g,'')" /><span id ="txtBint"> </span><br/>
         <s:submit value="登録" id="submit" onclick="checkuMobile()" disabled="true"/> <s:reset value="リセット"></s:reset><br/> <a href="index.jsp"><input type="button" value="戻る" /></a>
            
   		</s:form>
 <!--   		 onclick="check()"  -->
   		 </s:form>
   		<s:if test="hasActionMessages()"><div class="post"><s:actionmessage/></div></s:if>



    </body>
</html>