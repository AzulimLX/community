function post()
{
   var questionId = $("#article_id").val();
   var content = $("#comment_content").val();

   if (!content)
   {
      alert("不能回复空白内容");
      return;
   }


   $.ajax({
      type:"POST",
      url:"/comment",
      contentType:'application/json',
      data:JSON.stringify({
         "parentId":questionId,
         "content":content,
         "type":1
      }),
      success:function (response){
         if (response.code == 200)
         {
            window.location.reload();
         } else
         {
            if (response.code == 2003)
            {
              var isAccept = confirm(response.message);
              if (isAccept)
              {
               window.open("https://github.com/login/oauth/authorize?client_id=02b61cfdc58e7a2d0a98&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
               window.localStorage.setItem("closable",true);
              }
            }
            else {
               alert(response.message);
            }
         }
         console.log(response);
      },
      dataType:"json"

   });
}