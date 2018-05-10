function addCart(){
    document.getElementById("cartAmount").value = document.getElementById("productAmount").value;
    document.getElementById("addCart").submit();
}

function addOrderProduct(){
    document.getElementById("orderAmount").value = document.getElementById("productAmount").value;
    document.getElementById("addOrderProduct").submit();
}

function updateCart(product){
    document.getElementById(product+'Amount').value = document.getElementById(product).value;
}