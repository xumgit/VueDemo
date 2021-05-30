
$(document).ready(function() {

    $("#startNettyClient").click(function() {
        $.ajax({
            type: 'POST',
            dataType: "JSON",
            url: '/test/startNettyClient',
            success: function (data) {
                alert(JSON.stringify(data));
            }
        });
    });

});
