$(function () {
    selectJudge();
    // vform();
    $("#calculator").click(function () {
        hightshow();
        sublimtForm();
    });
});

// 下拉框判断显示隐藏
function selectJudge() {
    $(".select-info-message").change(function () {
        var select = $(".select-info-message option:selected").val();
        if (select == "m") {
            $(".result-tr").show();
            $(".radio-tr").hide();
            $(".year-tr").hide();
        } else if (select == "y") {
            $(".result-tr").hide();
            $(".radio-tr").show();
            $(".year-tr").show();
        } else if (select == "none") {
            $(".result-tr").hide();
            $(".radio-tr").hide();
            $(".year-tr").hide();
        }
    })
}


function vform() {
    $(".input-message").Validform({
        btnSubmit: "#create-btn",
        tiptype: 3,
        showAllError: true,
        datatype: {
            "double": function (gets, obj, curform, regxp) {
                //参数gets是获取到的表单元素值，obj为当前表单元素，curform为当前验证的表单，regxp为内置的一些正则表达式的引用;
                var reg = /^\d+(?:\.\d{1,2})?$/;
                if (reg.test(gets)) {
                    return true;
                }
                return false;
            }
        },
        callback:function(data){

        }
    });
}

//右侧表格高亮显示
function hightshow() {
    //这是当点击计算时，右侧表格高亮
    var rateInterval = $("#month").val() - 3500;
    var interval = [0, 1500, 4500, 9000, 35000, 55000, 80000];
    if (rateInterval <= 0) {

    } else if (rateInterval > 80000) {
        $(".table-area table tbody tr").removeClass("selected");
        $(".table-area table tbody tr:last").addClass("selected");

    } else {
        for (var i = 0; i < interval.length; i++) {
            for (var j = 1; j <= interval.length; j++) {
                if (rateInterval > interval[i] && rateInterval <= interval[j]) {
                    $(".table-area table tbody tr").removeClass("selected");
                    $(".table-area table tbody tr").eq(i).addClass("selected");
                    break;
                }
            }
        }
    }

}

//表单提交
function sublimtForm() {
    //获取下拉框值
    var op_se=$(".select-info-message").children("option:selected").val();
    var monthWage=$("#month").val();
    var yearAwards=$("#year").val();
    var radio_se=$(".radio-tr td input:checked").val();
    //月用get提交 年用post提交
    //月返回信息给输入框
    //年返还信息给文本框
    if ("m"==op_se){
        var data={"monthWage":monthWage};
        $.ajax({
            type:"GET",
            url:"/Servlet",
            data:data,

            success:function(back){
                $("#after-month").val(back);
            },
            error:function(){
                alert("未完成");
            }
        });
    }else if("y"==op_se){
        var data={"monthWage":monthWage,"yearAwards":yearAwards,"splitWay":radio_se};
        $.ajax({
            type:"POST",
            url:"/Servlet",
            cache:false,
            data:data,
            beforeSend:function(){
                $("#calculator").addClass("disabled");
                $(".result-area").empty().val("正在计算中，请等待...");
            },
            complete:function(){
                $("#calculator").removeClass("disabled");
            },
            success:function(back){
                $(".result-area").empty().val(back);
            },
            error:function(){
                alert("未完成");
            }
        });
    }
}

