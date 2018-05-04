function roleSave(){
    var roleName = document.getElementById("roleName")

    if( roleName.value == "" ){
        alert("권한명을 입력해주세요.");
        return false;
    }else{
        document.getElementById("formRole").submit();
    }

}
function roleUser(userNo) {
    $.ajax({
       type : "POST",
       url : "/admin/roles",
       data : {
           user : userNo,
           roleName : $("select[name=roleName]").val()
       },
       success : function (data) {
           if(data == "overlap"){
               alert("이미 부여된 권한입니다")
               return false;
           }else if(data == "true"){
               document.location.reload();
           }
       }
    });
}