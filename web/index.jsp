<%--
  Created by IntelliJ IDEA.
  User: ytuln
  Date: 2017/11/17
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>个人税率网页计算器</title>
    <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" media="screen"/>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link rel="stylesheet" type="text/css" href="css/validform.css">
</head>
<body>
<div class="title">个人所得税计算工具</div>
<div class="content">
    <div class="change-area col-md-5 col-md-offset-1">
        <div class="input-area">
            <h3>个人所得税计算工具</h3>
            <form class="input-message" method="post" onsubmit="return false">
                <table class="table input-table">
                    <tr class="select-tr">
                        <td>
                            <span>
                                个人所得税：
                            </span>
                            <select class="select-info-message" name="selectMorY">
                                <option value="none">--请选择--</option>
                                <option value="m">月工资</option>
                                <option value="y">年终奖</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="input-group">
                                <div class="input-group-addon">税前工资：</div>
                                <input type="text" class="form-control" id="month" placeholder="请输入月工资"
                                       datatype="double" name="monthWage">
                                <div class="input-group-addon">元</div>
                            </div>
                        </td>
                    </tr>
                    <tr class="year-tr">
                        <td>
                            <div class="input-group">
                                <div class="input-group-addon">年终奖：</div>
                                <input type="text" class="form-control" id="year" placeholder="请输入年终奖"
                                       datatype="double">
                                <div class="input-group-addon">元</div>
                            </div>
                        </td>
                    </tr>
                    <tr class="radio-tr">
                        <td>
                            <input type="radio" name="split" value="0" checked>不拆分
                            <input type="radio" name="split" value="1">拆分一月
                            <input type="radio" name="split" value="2">拆两月分
                        </td>
                    </tr>
                    <tr class="result-tr">
                        <td>
                            <div class="input-group">
                                <div class="input-group-addon">税后工资：</div>
                                <input type="text" class="form-control" id="after-month" placeholder="这是税后工资">
                                <div class="input-group-addon">元</div>
                            </div>
                        </td>
                    </tr>
                    <tr class="button-tr">
                        <td>
                            <button id="calculator" class="btn btn-primary">计算</button>
                            <input type="reset" class="btn btn-default" value="重置">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <textarea class="result-area">

        </textarea>
    </div>

    <div class="table-area col-md-5 col-md-offset-1">
        <table class="table table-bordered">
            <thead>
            <td>全月应纳税额</td>
            <td>税率</td>
            <td>速算扣除数（元）</td>
            </thead>
            <tbody>
            <tr>
                <td>不超过1500元</td>
                <td>3%</td>
                <td>0</td>
            </tr>
            <tr>
                <td>超过1500至4500元</td>
                <td>10%</td>
                <td>105</td>
            </tr>
            <tr>
                <td>超过4500至9000元</td>
                <td>20%</td>
                <td>555</td>
            </tr>
            <tr>
                <td>超过9000至35000</td>
                <td>25%</td>
                <td>1005</td>
            </tr>
            <tr>
                <td>超过35000至55000</td>
                <td>30%</td>
                <td>2755</td>
            </tr>
            <tr>
                <td>超过55000至80000</td>
                <td>35%</td>
                <td>5505</td>
            </tr>
            <tr>
                <td>超过80000</td>
                <td>45%</td>
                <td>13505</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/Validform.js"></script>
<script type="text/javascript" src="js/index.js"></script>
</html>
