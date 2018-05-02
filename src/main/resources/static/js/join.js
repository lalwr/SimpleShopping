var element_layer = "";
$(function() {

    element_layer = document.getElementById('layer');

    //주소 검색
    $("#addressSearch").click(function() {
        $("#fog").show();
        DaumPostcodeLayer();
    });

});

function DaumPostcodeLayer() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullAddr = data.address; // 최종 주소 변수
            var extraAddr = ''; // 조합형 주소 변수

            // 기본 주소가 도로명 타입일때 조합한다.
            if(data.addressType === 'R'){
                //법정동명이 있을 경우 추가한다.
                if(data.bname !== ''){
                    extraAddr += data.bname;
                }
                // 건물명이 있을 경우 추가한다.
                if(data.buildingName !== ''){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('address').value = "(" +data.zonecode + ") " + fullAddr; //5자리 새우편번호 사용
            //document.getElementById('sample2_addressEnglish').value = data.addressEnglish;

            // 커서를 필드로 이동한다.
            document.getElementById('address').focus();

            // iframe을 넣은 element를 안보이게 한다.
            // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
            element_layer.style.display = 'none';
            $("#fog").hide();
        },
        width : '100%',
        height : '100%',
        maxSuggestItems : 5
    }).embed(element_layer);

    // iframe을 넣은 element를 보이게 한다.
    element_layer.style.display = 'block';

    // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
    initLayerPosition();
}

// 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
// resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
// 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
function initLayerPosition(){
    var width = 500; //우편번호서비스가 들어갈 element의 width
    var height = 500; //우편번호서비스가 들어갈 element의 height
    var borderWidth = 5; //샘플에서 사용하는 border의 두께

    // 위에서 선언한 값들을 실제 element에 넣는다.
    element_layer.style.width = width + 'px';
    element_layer.style.height = height + 'px';
    element_layer.style.border = borderWidth + 'px solid';
    // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
    //element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
    //element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
    element_layer.style.marginLeft = '700px';
    element_layer.style.marginTop = '300px';
}
// DAUM 우편 API
function DaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullAddr = ''; // 최종 주소 변수
            var extraAddr = ''; // 조합형 주소 변수

            // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                fullAddr = data.roadAddress;

            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                fullAddr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
            if (data.userSelectedType === 'R') {
                //법정동명이 있을 경우 추가한다.
                if (data.bname !== '') {
                    extraAddr += data.bname;
                }
                // 건물명이 있을 경우 추가한다.
                if (data.buildingName !== '') {
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                fullAddr += (extraAddr !== '' ? ' (' + extraAddr + ')' : '');
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('address').value = "(" +data.zonecode + ") " + fullAddr; //5자리 새우편번호 사용

            // 커서를 필드로 이동한다.
            document.getElementById('address').focus();
        }
    }).open();
}

function closeDaumPostcode() {
    // iframe을 넣은 element를 안보이게 한다.
    element_layer.style.display = 'none';
    $("#fog").hide();
}

function join(){
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
            url : "/users/emailOverlap",
            data : {
                email :$("#email").val()
            },
            success : function (data) {
                if(data == "overlap"){
                    alert("이메일이 중복입니다. 변경해주세요.");
                }else{
                    document.getElementById("formJoin").submit();
                }
            }
        });
    }

}