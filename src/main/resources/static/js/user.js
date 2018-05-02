function user(){
    var name = document.getElementById("name");
    var email = document.getElementById("email");
    var regExp_email = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    var password = document.getElementById("password");
    var rePassword = document.getElementById("rePassword");
    var phone = document.getElementById("phone");
    var regExp_phone= /^[0-9]+$/;
    var address = document.getElementById("address");

    //빈값 검사
    if( !name.value  ){
        alert("name을 입력해주세요.");
        name.focus();
        return false;
    }else if( !email.value  ){
        alert("email을 입력해주세요.");
        email.focus();
        return false;
    }else if( !email.value.match(regExp_email)  ){
        alert("email 양식을 확인해주세요.");
        email.focus();
        return false;
    }else if( !password.value  ){
        alert("password를 입력해주세요.");
        password.focus();
        return false;
    }else if( !rePassword.value  ){
        alert("Re password를 입력해주세요.");
        rePassword.focus();
        return false;
    }else if( password.value.length <= 3  && rePassword.value.length <= 3 ){
        alert("password는 4자이상 가능합니다.");
        password.focus();
        return false;
    }else if( password.value != rePassword.value ){
        alert("비밀번호가 일치하지 않습니다.");
        password.focus();
        return false;
    }else if( !phone.value  ){
        alert("phone을 입력해주세요.");
        phone.focus();
        return false;
    }else if( !phone.value.match(regExp_phone)  ){
        alert("phone은 숫자만 입력가능합니다.");
        phone.focus();
        return false;
    }else if( phone.value.length > 11){
        alert("phone는 11자 이하만 가능합니다.");
        phone.focus();
        return false;
    }else if( !address.value  ){
        alert("address를 입력해주세요.");
        address.focus();
        return false;
    }else{
        $.ajax({
            type : "POST",
            url : "/users/passwordCheck",
            data : {
                email :$("#email").val(),
                password :$("#password").val()
            },
            success : function (data) {
                if(data == "false"){
                    alert("비밀번호가 틀렸습니다.");
                }else{
                    $("#formMethod").val("PUT");
                    $("#formUser").attr("action", "/users/update");
                    document.getElementById("formUser").submit();
                }
            }
        });
    }

}
function leave() {
    var password = document.getElementById("password");
    var rePassword = document.getElementById("rePassword");

    if( !password.value  ){
        alert("password를 입력해주세요.");
        password.focus();
        return false;
    }else if( !rePassword.value  ){
        alert("Re password를 입력해주세요.");
        rePassword.focus();
        return false;
    }else if( password.value != rePassword.value ){
        alert("비밀번호가 일치하지 않습니다.");
        password.focus();
        return false;
    }

    $.ajax({
        type : "POST",
        url : "/users/passwordCheck",
        data : {
            email :$("#email").val(),
            password :$("#password").val()
        },
        success : function (data) {
            if(data == "false"){
                alert("비밀번호가 틀렸습니다.");
            }else{
                if (confirm("탈퇴 하시겠습니까") == true){
                    $("#formMethod").val("DELETE")
                    $("#formUser").attr("action", "/users/delete");
                    document.getElementById("formUser").submit();
                }else{
                    alert("탈퇴 실패하였습니다");
                }
            }
        }
    });

}