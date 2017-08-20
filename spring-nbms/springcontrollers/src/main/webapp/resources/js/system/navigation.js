

// $(document).ready(function(){
//
//     // $("#layout-center").panel({
//     //
//     //     onResize:function(width, height) {
//     //         var newWidth = width + "px";
//     //         var newHeight = height + "px";
//     //         var tabs = $('#tabs').tabs('tabs');
//     //         for(var i = 0; i < tabs.length; i++) {
//     //             /* tabs[i].panel('resize',{
//     //              width : newWidth,
//     //              height : newHeight
//     //              }); */
//     //             //alert("newHeight"+newHeight);
//     //         }
//     //     }
//     // });
// });

function createTab(menuId, menuName, url, opCode) {
    var tabs = $('#tabs');
    url = url + "?opCode=" + opCode;
    if(!tabs.tabs('exists', menuName)) {
        var tab = addTab(menuId, menuName, url);
        tabs.tabs("add", tab);
    }
    return ;
}

function addTab(id, title, url) {
    var content = "<iframe src='" + url + "' frameborder='0' style='width: 100%; height: 95%' ></iframe>"
    var tab = {
        "id" : id,
        "title" : title,
        "content" : content,
        "closable" : true
   };
    return tab;
}