<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>게시글 작성 페이지</title>
</head>
<body>
  <h2>게시글 작성</h2>

  <label for="userId">유저 ID </label>
  <input type="text" id="userId" placeholder="유저 이름 작성">

  <br>
  <br>

  <label for="title">제목 </label>
  <input type="text" id="title" placeholder="제목 작성">

  <br>
  <br>

  <label for="content">내용 </label>
  <textarea id="content" placeholder="내용....." rows="5"></textarea>

  <br>
  <br>

  <button>저장하기</button>

<%--<script>--%>
<%--  function submitPost(){--%>
<%--    const userId = document.getElementById("userId").value;--%>
<%--    const title = document.getElementById("title").value;--%>
<%--    const content = document.getElementById("title").value;--%>

<%--    const postData = {--%>
<%--      qboardTitle : title,--%>
<%--      qboardContents : content,--%>
<%--      user : {userId: userId}--%>
<%--    };--%>


<%--      fetch("http://localhost:8080/qboard",{--%>
<%--        method:"POST",--%>
<%--        header:{--%>
<%--          "Content-Type":"application/json"--%>
<%--        },--%>
<%--        body: JSON.stringify(postData)--%>
<%--    }).then(response => {--%>
<%--        if(response.ok){--%>
<%--          alert("게시글이 작성 완료");--%>
<%--          document.getElementById("userId").value = "";--%>
<%--          document.getElementById("title").value = "";--%>
<%--          document.getElementById("content").value = "";--%>
<%--        }else {--%>
<%--          alert("게시글 등록 실패!");--%>
<%--        }--%>
<%--      }).catch(error => {--%>
<%--        console.error("Error:", error);--%>
<%--        alert("서버 오류가 발생했습니다.");--%>
<%--      });--%>

<%--      --%>
<%--  }--%>

<%--</script>--%>




</body>
</html>