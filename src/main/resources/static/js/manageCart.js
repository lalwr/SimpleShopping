function addCart(){
    document.getElementById("cartAmount").value = document.getElementById("amount").value;
    document.getElementById("orderAmount").value = document.getElementById("amount").value;
}

function updateCart(){
    document.getElementById("productAmount").value=document.getElementById("cartAmount").value;
}
