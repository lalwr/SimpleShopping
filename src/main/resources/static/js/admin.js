function submitByMethod(formId, method){
    var eMethod = document.createElement('input');
    eMethod.setAttribute('type', 'hidden');
    eMethod.setAttribute('name', '_method');
    eMethod.setAttribute('value', method);

    var form = document.getElementById(formId);
    form.appendChild(eMethod);
    form.submit();
}

function submitWithJson(method, checkName, attr, paramName, items){
    var categoryInfo = new Object();
    var categoryList = new Array();
    var checks = document.getElementsByName(checkName);
    var checkedOne = false;

    for(var i=0, size = checks.length ; i < size ; i++){
        var check = checks[i];

        if(check.checked == true){
            checkedOne = true;
            var idx = check.getAttribute(attr);
            var category = new Object();
            category[checkName] = check.value;

            for(var j=0, itemSize = items.length ; j < itemSize ; j++ ){
                var item = document.getElementById(items[j]+'.'+idx);
                category[items[j]] = item.value;
            }
            categoryList.push(category);
        }
    }
    if(checkedOne==false){return;}
    categoryInfo[paramName] = categoryList;


    $.ajax({
        type : method,
        url : context+'admin/category/list',
        contentType : 'application/json; charset=UTF-8',
        data : JSON.stringify(categoryInfo),
        dataType : 'text',
        success: function(data){
            document.location.reload();
        }
    });

}