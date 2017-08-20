
function ajax(url, async, data, success) {
    $.ajax({
        url : url,
        async : async,
        type : 'POST',
        data : data,
        success : success,
        error : function (xhr, status, error) {
            $.messager.alert('错误',error,'error');
        }
    });
}

function post(url, data, success) {
    $.post(url, data ,success, "json");
}