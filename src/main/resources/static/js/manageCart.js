function addCart(){
    document.getElementById("cartAmount").value = document.getElementById("amount").value;
    document.getElementById("orderAmount").value = document.getElementById("amount").value;
}

function updateCart(product){
    document.getElementById(product+'Amount').value = document.getElementById(product).value;
}