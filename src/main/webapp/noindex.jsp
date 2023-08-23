<%@page import="com.Helper.DBMethods"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>NDotoStream</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

 <!--Include Latest compiled and minified CSS -->
    <link href=
"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" 
        rel="stylesheet" />
    <script src=
"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js">
    </script>
    <script src=
"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js">
    </script>
    <script src=
"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js">
    </script>

</head>
<body style="background-image: url('images/form-v9.jpg'); ">

<div class="progress progress-striped active mt-4">
        <div class="progress-bar progress-bar-success"
            style="width: 0%">
        </div>
    </div>
    
<!--     <div class="container"> -->
<!--     	<h1>Please Wait While We checking your Details</h1> -->
    
<!--     </div> -->
<%

String ani= (String) session.getAttribute("ani");

System.out.println("ani in noindex.jsp is "+ani);


%>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
  
        // Set the width to animate the progress bar
        // Along with time duration in milliseconds
        $(".progress-bar").animate({
                width: "70%",
        }, 7500);
    </script>

<script>

	setTimeout(check,10000);

var data="";

	function check()
	{
		let input=
		{
			"ani":<%=ani%>
		}
		
		console.log("input is ",input);
		
		$.ajax({
			type:"POST",
			url:"check",
			data:input,
			success:function(result)
			{
				data=JSON.parse(result);
				console.log(data);

				if(data=="yes")
				{
					window.location.href='index.jsp';
				}
				else if (data=="no")
				{
				   	setTimeout(checkAgain,20000);	
				}
			}			
		});	
	};
	
	
// 	 function sleep(ms) 
// 	    {
// 	      return new Promise(resolve => setTimeout(resolve, ms));
// 	    };
	
	    
	    function checkAgain()
		{
			let input=
			{
				"ani":<%=ani%>
			}
			
			console.log("input again is ",input);
			
			$.ajax({
				type:"POST",
				url:"check",
				data:input,
				success:function(result)
				{
					data=JSON.parse(result);
					console.log(data);

					if(data=="yes")
					{
						window.location.href='index.jsp';
					}
					else if(data=="no")
					{
						window.location.href='index.jsp';
// 						window.location.href='http://wap.zero9.co.za/ndoto/join.php?s=stream_r5';
					}
				}			
			});	
		};

	    
	    
	    
// 	 async function go()
// 	{
// // 				check();
				
// //                 await sleep(8000);
                
// //                 check();
                
// //                 await sleep(6000);
                
// //                 check();
                
// //                 await sleep(6000);
                
// 				if(data=="no")
// 				{
// 				window.location.href='http://wap.zero9.co.za/ndoto/join.php?s=stream_r5';				
// 				}
// 				else if(data=="yes")
// 				{
// 					window.location.href='index.jsp';
// 				}
// //                 await sleep(5000);
// 	}
	 
// 	 async function to()
// 	 {
// 		 check();
			
//          await sleep(8000);
         
//          check();
         
//          await sleep(6000);
         
//          check();
         
//          await sleep(6000);
         
//           if(data=="recharged")
// 			{
// 				window.location.href='index.jsp';
// 			}
//          else if(data=="notexist")
// 			{
// 			window.location.href='http://wap.zero9.co.za/ndoto/join.php?s=stream_r5';				
// 			}	
// 	 }
	
// 	 function again()
// 	{
// 		if(data=="notrecharge")
// 		{
// 				setTimeout(go, 8000);

// 			window.location.href='http://wap.zero9.co.za/ndoto/join.php?s=stream_r5';
// 		}
// 		else if(data=="recharged")
// 		{
// 			window.location.href='index.jsp';
// 		}
// 	}
</script>
</body>
</html>