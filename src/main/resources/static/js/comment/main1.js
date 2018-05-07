$(function() {

    $.ajax({
        url: "/comment",
        data:{
            "async":true,
        },
        success: function(data){
            $("#rightContainer").html(data);
            console.log("异步内容填充成功");
        },
        error : function() {
            alert("error");
            console.log("异步内容填充失败");
        }
    });

});