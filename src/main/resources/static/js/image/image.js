$(function() {
    $.ajax({
        url: "/image",
        data:{
            "async":true,
        },
        success: function(data){
            $("#imagelistreplace").html(data);
            console.log("异步内容填充成功");
        },
        error : function() {
            alert("error");
            console.log("异步内容填充失败");
        }
    });

});