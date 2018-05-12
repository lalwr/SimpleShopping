function addCart(productStock){
    document.getElementById("cartAmount").value = document.getElementById("productAmount").value;
    if(productStock < document.getElementById("cartAmount").value){
        alert("상품 재고 : " + productStock + '개');
    }else {
        document.getElementById("addCart").submit();
    }
}

function addOrderProduct(productStock){
    document.getElementById("orderAmount").value = document.getElementById("productAmount").value;
    if(productStock < document.getElementById("orderAmount").value){
        alert("상품 재고 : " + productStock + '개');
    }else {
        document.getElementById("addOrderProduct").submit();
    }
}

var productStock;

function loadProductStock(product){
    $.ajax({
        type: "GET",
        url: "/cart/productStock/" + product,
        success: function (data) {
            productStock = data;
            updateCart(product);
        }
    });
}

function updateCart(product) {
    document.getElementById(product + 'Amount').value = document.getElementById(product).value;
    if (productStock < document.getElementById(product).value) {
        alert("상품 재고 : " + productStock + '개');
    }else{
        document.getElementById(product+"putCart").submit();
    }
}
var checkParam = [];

function finalCheckStock(product){
    $.ajax({
        type: "GET",
        url: "/cart/productStock/" + product,
        success: function (data) {
            productStock = data;
            if(document.getElementById(product).value <= productStock){
                checkParam.push(true);
            }else{
                alert(product+"번 상품 재고 수량 : " + productStock + "개 / 주문 수량 : "+document.getElementById(product).value +"개");
                checkParam.push(false);
            }

            if(checkParam.length === document.querySelectorAll("input[name=buttontest]").length) {
                document.getElementById("ordercheck").value = finalCheck(checkParam);
                document.getElementById("orderProduct").submit();
            }
        }
    });
}
function finalCheck(checkParam) {
    for(i=0; i< document.querySelectorAll("input[name=buttontest]").length; i++){
        if(checkParam[i] === false)
            return false
    }
    return true;
}

function check(){
    for(i = 0; i<document.querySelectorAll("input[name=buttontest]").length; i++) {
        document.querySelectorAll("input[name=buttontest]")[i].click();
    }
}