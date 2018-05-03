function roleSave(){
    var roleName = document.getElementById("roleName")

    if( roleName.value == "" ){
        alert("권한명을 입력해주세요.");
        return false;
    }else{
        document.getElementById("formRole").submit();
    }

}