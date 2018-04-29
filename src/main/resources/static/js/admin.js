function submitByMethod(formId, method){
    var eMethod = document.createElement('input');
    eMethod.setAttribute('type', 'hidden');
    eMethod.setAttribute('name', '_method');
    eMethod.setAttribute('value', method);

    var form = document.getElementById(formId);
    form.appendChild(eMethod);
    form.submit();
}