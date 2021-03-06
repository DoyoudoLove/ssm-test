<%--
  Created by IntelliJ IDEA.
  User: SugarMan
  Date: 2018/9/12
  Time: 上午9:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>我的购物车</title>
    <link rel="stylesheet" href="/statics/css/cart.css">
    <link rel="stylesheet" href="/statics/css/common.css">
</head>
<body>
<jsp:include page="top.jsp"/>
<div class="cart" id="container">
    <c:choose>
        <c:when test="${cartList == null && user == null}">
            <div class="w">
                <div class="cart-empty">
                    <div class="message">
                        <ul>
                            <li class="txt">
                                购物车内暂时没有商品，登录后将显示您之前加入的商品
                            </li>
                            <li>
                                <a href="/page/login.action?redirectURL=/cart/cart.action"
                                   class="btn-1 login-btn mr10">登录</a>
                                <a href="/page/index.action" class="ftx-05">
                                    去购物&gt;
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </c:when>
        <c:when test="${cartList == null && user != null}">
            <div class="w">
                <div class="cart-empty">
                    <div class="message">
                        <ul>
                            <li class="txt">
                                购物车内暂时没有商品
                            </li>
                            <li>
                                <a href="/page/index.action" class="ftx-05">
                                    去购物&gt;
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="w">
                <div class="cart-filter-bar">
                    <ul class="switch-cart">
                        <li class="switch-cart-item curr">
                            <a href="#none">
                                <em>全部商品</em>
                                <span class="number"></span>
                            </a>
                        </li>
                    </ul>
                    <div class="cart-store">
                        <input id="hiddenLocationId" type="hidden">
                        <input id="hiddenLocation" type="hidden">
                        <!--<span class="label">配送至：</span>-->
                        <div id="jdarea" class="ui-area-wrap">
                            <div class="ui-area-text-wrap">
                                <div class="ui-area-text">
                                </div>
                                <b>
                                </b>
                            </div>
                            <div class="ui-area-content-wrap">
                                <div class="ui-area-tab">
                                </div>
                                <div class="ui-area-content">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="clr"></div>
                    <div class="w-line">
                        <div class="floater"></div>
                    </div>
                    <div class="tab-con"></div>
                    <div class="tab-con hide"></div>
                </div>
            </div>
            <div class="cart-warp">
                <div class="w">
                    <div id="jd-cart">
                        <div class="cart-main cart-main-new">
                            <div class="cart-thead">
                                <div class="column t-checkbox">
                                    <div class="cart-checkbox">
                                        <input type="checkbox" checked="checked" id="toggle-checkboxes_up"
                                               name="toggle-checkboxes" class="jdcheckbox">
                                        <label class="checked" for="">勾选全部商品</label>
                                    </div>
                                    全选
                                </div>
                                <div class="column t-goods">商品</div>
                                <div class="column t-props"></div>
                                <div class="column t-price">单价</div>
                                <div class="column t-quantity">数量</div>
                                <div class="column t-sum">小计</div>
                                <div class="column t-action">操作</div>
                            </div>
                            <div id="cart-list">
                                <form id="gotoOrder" method='post'
                                      action=''>
                                </form>
                                <div class="cart-item-list" id="cart-item-list-01">
                                    <div class="cart-tbody" id="vender_8888">
                                        <div class="shop">
                                            <div class="cart-checkbox">
                                                <input type="checkbox" checked="checked" name='checkShop'
                                                       class="jdcheckbox">
                                                <label for="">勾选店铺内全部商品</label>
                                            </div>
                                        </div>
                                        <div class="item-list">
                                            <c:forEach items="${cartList}" var="cart">
                                                <c:set var="num" value="${cart.num}"/>
                                                <c:set var="totalPrice" value="${ totalPrice + (cart.price * cart.num)}"/>
                                                <div class="item-give item-full" id="product_promo_${cart.id}">
                                                    <!-- 单品-->
                                                    <div class="item-last item-item item-selected"
                                                         id="product_${cart.id}">
                                                        <div class="item-form">
                                                            <div class="cell p-checkbox">
                                                                <div class="cart-checkbox">
                                                                    <input id="check${cart.id}"
                                                                           type="checkbox" name='checkItem'
                                                                           value="${cart.id}"
                                                                           checked="checked"
                                                                           onclick="check('${cart.id}')">
                                                                    <label for="" class="checked">勾选商品</label>
                                                                </div>
                                                            </div>
                                                            <div class="cell p-goods">
                                                                <div class="goods-item">
                                                                    <div class="p-img">
                                                                        <a href="http://localhost:8103/item/${cart.id}.html"
                                                                           target='_blank'
                                                                           class="J_zyyhq_${cart.id}">
                                                                            <img alt="${cart.title}"
                                                                                 src="${cart.image}" height="80"
                                                                                 width="80"></a>
                                                                    </div>
                                                                    <div class="item-msg">
                                                                        <div class="p-name">
                                                                            <a href="#"
                                                                               target='_blank'>${cart.title}
                                                                            </a>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="cell p-props p-props-new">
                                                                <div class="props-txt" title="">颜色：</div>
                                                                <div class="props-txt" title="">尺码：</div>
                                                            </div>
                                                            <div class="cell p-price">
                                                                <strong id="p-price${cart.id}" value="${cart.price}">
                                                                    ¥<fmt:formatNumber value="${cart.price}"/>
                                                                </strong>
                                                            </div>
                                                            <div class="cell p-quantity">
                                                                <!-- 满赠 -->
                                                                <div class="quantity-form">
                                                                    <a href="javascript:void(0);"
                                                                       class="decrement"id="decrement">-</a>
                                                                    <input autocomplete="off" type="text" class="itxt"
                                                                           itemPrice="${cart.price}" itemId="${cart.id}"
                                                                           value="${cart.num }"
                                                                           id="changeQuantity${cart.id}"
                                                                           minnum="1"/>
                                                                    <a href="javascript:void(0);"
                                                                       class="increment" id="increment">+</a>
                                                                </div>
                                                                <div class="ac ftx-03 quantity-txt"
                                                                     _stock="stock_${cart.id}">有货
                                                                </div>
                                                            </div>
                                                                <%--小计--%>
                                                            <div class="cell p-sum">
                                                                <strong id="p-sum${cart.id}"
                                                                        value="${cart.price * num}">¥${cart.price * num}</strong>
                                                                <span class="weight" id="weight_${cart.id}"
                                                                      num="1"></span>
                                                            </div>
                                                            <div class="cell p-ops">
                                                                <!-- 满赠 -->
                                                                <a id="remove_8888_${cart.id}_13_196031044"
                                                                   data-name="${cart.title}"
                                                                   class="cart-remove"
                                                                   href="/cart/delete/${cart.id}.action">删除</a>
                                                                <a href="javascript:void(0);" class="cart-follow">移到我的关注</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="cart-floatbar">
                        <div class="cart-toolbar">
                            <div class="toolbar-wrap">
                                <div class="selected-item-list hide">
                                </div>
                                <div class="options-box">
                                    <div class="select-all">
                                        <div class="cart-checkbox">
                                            <input type="checkbox" checked="checked"
                                                   id="toggle-checkboxes_down"
                                                   name="toggle-checkboxes" class="jdcheckbox">
                                            <label class="checked" for="">勾选全部商品</label>
                                        </div>
                                        全选
                                    </div>
                                    <div class="operation">
                                        <a href="#none" class="remove-batch">删除选中的商品</a>
                                        <a href="#none" class="follow-batch">移到我的关注</a>
                                        <a class="J_clr_nosale" href="#none">清空购物车</a>
                                    </div>
                                    <div class="clr"></div>
                                    <div class="toolbar-right">
                                        <div class="normal">
                                            <div class="comm-right">
                                                <div class="btn-area">
                                                    <a href="/order/showOrder.action"
                                                       class="submit-btn">
                                                        去结算<b></b></a>
                                                </div>
                                                <div class="price-sum">
                                                    <div>
                                                        <span class="txt txt-new">总价：</span>
                                                        <span class="price sumPrice">
                                                            <em id="totalPrice" value="${totalPrice}">¥<fmt:formatNumber
                                                                    value="${totalPrice}"/></em>
                                                        </span>
                                                        <b class="ml5 price-tips"></b>
                                                        <div class="price-tipsbox"
                                                             style="display: none; left: 159.85px;">
                                                            <div class="ui-tips-main">不含运费及送装服务费</div>
                                                            <span class="price-tipsbox-arrow"></span>
                                                        </div>
                                                        <br>
                                                        <span class="txt">已节省：</span>
                                                        <span class="price totalRePrice">-¥0.00</span>
                                                    </div>
                                                </div>
                                                <div class="amount-sum">
                                                    已选择<em id="amount-sum">${num}</em>件商品<b class="up"></b>
                                                </div>
                                                <div class="clr"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--cart-toolbar-->
                    <div class="cart-removed">
                        <div class="r-tit">已删除商品，您可以重新购买或加关注：</div>
                    </div>
                </div>
            </div>
            <div class="w"></div>
        </c:otherwise>
    </c:choose>
</div>
<script src="/statics/js/jquery-3.3.1.min.js"></script>
<script>
    function check(id) {
        //判断是否选中
        var check = $("#check" + id).is(':checked');
        //商品总价对象
        var totalPrice = $('#totalPrice');
        //商品总数对象
        var amountSum = $('#amount-sum');
        //商品总数
        var amountSumValue = parseInt(amountSum.text());
        //勾线商品总价
        var totalValue = parseInt(totalPrice.attr('value'));
        // console.log("totalValue:" + totalValue);
        //单独商品总价对象
        var sum = $('#p-sum' + id);
        //单独商品总价
        var sumValue = parseInt(sum.attr('value'));
        // console.log("sumValue:" + sumValue);
        var selected = $('#product_' + id);
        //是否被选中
        if (check) {
            //设置商品被选中
            $("#check" + id).attr('checked', 'checked');
            //更改全部商品总价
            totalPrice.attr('value', totalValue + sumValue);
            //更改全部商品总价显示
            totalPrice.text('￥' + (totalValue + sumValue));
            // totalPrice.text('￥' + ((totalValue + sumValue) / 100).toFixed(2));
            //商品选中数量加1
            amountSum.text(amountSumValue + 1);
            selected.addClass('item-selected');
        } else {
            //移除商品被选中
            $("#check" + id).removeAttr('checked');
            //更改全部商品总价
            totalPrice.attr('value', totalValue - sumValue);
            //更改全部商品总价显示
            totalPrice.text('￥' + (totalValue - sumValue));
            // totalPrice.text('￥' + ((totalValue - sumValue) / 100).toFixed(2));
            //商品选中数量减1
            amountSum.text(amountSumValue - 1);
            selected.removeClass('item-selected');
        }
    }


    //减少商品
    $(".decrement").on('click', function () {//-

        var _thisInput = $(this).siblings("input");
        var id = _thisInput.attr("itemId");
        var nub = parseInt(_thisInput.val());
        if (nub == 1) {
            return;
        }
        _thisInput.val(nub - 1);
        $.post("/cart/decreOrIncre/" + _thisInput.attr("itemId") + "/" + _thisInput.val() + ".action", function (data) {
            if (data.status != 200) {
                alert("数据有误");
            }
        });

        var price = parseInt($('#p-price' + id).attr('value'));

        var sum = $('#p-sum' + id);

        var sumPrice = $('#p-sum' + id);
        sumPrice.attr('value', price * (nub - 1));

        if ($("#check" + id).attr('checked')) {
            var totalPrice = $('#totalPrice');
            //总价
            var totalPriceValue = parseInt($('#totalPrice').attr('value'));
            var totalValue = totalPriceValue - price;
            totalPrice.text('￥' + totalValue.toFixed(2));
            totalPrice.attr('value', totalValue);
        }
        var pricesum = price * (nub - 1);
        sum.text('￥' + pricesum.toFixed(2));

        if (nub - 1 == parseInt(_thisInput.attr('minnum'))) {
            $('#decrement' + id).addClass('disabled');
        }
    });
    //增加商品
    $(".increment").on('click', function () {//＋

        var _thisInput = $(this).siblings("input");
        var id = _thisInput.attr("itemId");
        var nub = parseInt(_thisInput.val());
        _thisInput.val(nub + 1);
        $.post("/cart/decreOrIncre/" + _thisInput.attr("itemId") + "/" + _thisInput.val() + ".action", function (data) {
            if (data.status != 200) {
                alert("数据有误");
            }
        });
        $('#decrement' + id).removeClass('disabled');

        var price = parseInt($('#p-price' + id).attr('value'));
        var sum = $('#p-sum' + id);
        if ($("#check" + id).attr('checked')) {
            var totalPrice = $('#totalPrice');
            //总价
            var totalPriceValue = parseInt($('#totalPrice').attr('value'));
            var totalValue = totalPriceValue + price;
            totalPrice.text('￥' + totalValue.toFixed(2));
            totalPrice.attr('value', totalValue);
        }

        var sumPrice = $('#p-sum' + id);
        sumPrice.attr('value', price * (nub + 1));
        var pricesum = price * (nub + 1);
        // alert(pricesum);
        sum.text('￥' + pricesum.toFixed(2));
    });

</script>
</body>
</html>
