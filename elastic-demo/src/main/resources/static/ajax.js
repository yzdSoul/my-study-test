$(function(){
    $ajax({
        url: "[[@{/thymeleaf/find}]]",
        type: "get",
        dataType: "json",
        data: params,
        success: function (data) {
            location.href = "/hello/"
        }
    });
})