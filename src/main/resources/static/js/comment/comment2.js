$(function() {

    $.ajax({
        url: "/comment/deletelist",
        data:{
            "async":true,
        },
        success: function(data){
            $("#commentlistreplace").html(data);
            console.log("异步内容填充成功");
        },
        error : function() {
            alert("error");
            console.log("异步内容填充失败");
        }
    });

});