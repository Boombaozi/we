$(function() {

    var _pageSize; // 存储用于搜索
    console.log("main执行了");
    // 根据用户名、页面索引、页面大小获取用户列表
    function getCommentList(pageIndex, pageSize) {
        $.ajax({
            url: "/image",
            // contentType : 'application/json',
            data:{
                "async":true,
                "pageIndex":pageIndex,
                "pageSize":pageSize
            },
            success: function(data){
                $("#imagelistreplace").html(data);
                console.log("异步请求成功");
            },
            error : function() {
                toastr.error("获取数据错误");
            }
        });
    }

    // 分页
    $.tbpage("#imagelistreplace", function (pageIndex, pageSize) {
        console.log("tbpage执行了");
        getCommentList(pageIndex, pageSize);
        _pageSize = pageSize;
    });

});