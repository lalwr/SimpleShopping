function roleSave(){
    var roleName = document.getElementById("roleName");

    if( roleName.value == "" ){
        alert("권한명을 입력해주세요.");
        return false;
    }else{
        document.getElementById("formRole").submit();
    }

}
function roleAddUser(userNo) {
    var role = $("select[name=role]").val();

    if( role == ""){
        alert("권한을 선택해주세요.");
        return false;
    }else{
        $.ajax({
            type : "POST",
            url : "/admin/roles",
            data : {
                userNo : userNo,
                roleName : role
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
}
function roleDeleteUser(roleNo) {
    if(!confirm("권한을 삭제하시겠습니까?")){
        return false;
    }else{
        $.ajax({
            type: 'POST',
            url : "/admin/roles",
            data : {
                roleNo : roleNo,
                _method: 'DELETE'
            },
            success : function (data) {
                if(data == "noRole"){
                    alert("부여되지 않은 권한입니다")
                    return false;
                }else if(data == "true"){
                    document.location.reload();
                }
            }
        });
    }

}