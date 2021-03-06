/**
 *
 */

$(function(){
    // 传递id说明是修改店铺信息，不传递是注册店铺信息
    var shopId=getQueryString('shopId');
    var isEdit=shopId ? true : false;
    var initUrl='/o2o/shopadmin/getshopinitinfo';
    var registerShopUrl='/o2o/shopadmin/registershop';
    var shopInfoUrl="/o2o/shopadmin/getshopbyid?shopId="+shopId;
    var editShopUrl='/o2o/shopdamin/modifyshop';
    // 通过isEdit来判断是修改店铺信息还是注册店铺，来调用不同的方法
    if(!isEdit){
        getShopInitInfo();
    } else{
        getShopInfo(shopId);
    }
    function getShopInitInfo(){
        $.getJSON(initUrl,function(data){
            if(data.success){
                var tempHtml='';
                var tempAreaHtml='';
                /*
                  * 以map的形式遍历集合
                  */
                data.shopCategoryList.map(function(item,index){
                    tempHtml+='<option data-id="'+item.shopCategoryId+'">'+
                        item.shopCategoryName+'<option>';
                });
                data.areaList.map(function(item,index){
                    tempAreaHtml+='<option data-id="'+item.areaId+'">'+
                        item.areaName+'<option>';
                });
                $('#shop_category').html(tempHtml);
                $('#area').html(tempAreaHtml);
            }
        });
    };
    // 通过传入的shopId，查询shop的信息，然后获取到js页面中，为后面修改shop信息做准备
    function getShopInfo(shopId){
        $.getJSON(shopInfoUrl, function(data){
            if(data.success){
                var shop = data.shop;
                $('#shop_name').val(shop.shopName);
                $('#shop_addr').val(shop.shopAddr);
                $('#shop_phone').val(shop.phone);
                $('#shop_desc').val(shop.shopDesc);
                // 将shopCategory的信息以option的形式保存，然后赋值到下面的shop_category，并且它的attr是disabled的（不可选择的）
                // area是以下拉列表的形式保存，area的attr默认选择的是现在的店铺区域信息
                var shopCategory = '<option data-id="'
                    + shop.shopCategory.shopCategoryId + '"selected>'
                    + shop.shopCategory.shopCategoryName + '</option>';
                var tempAreaHtml = '';
                data.areaList.map(function(item, index){
                    tempAreaHtml += '<option data-id=""' + item.areaId + '"">'
                        + item.areaName + '</option>';
                });
                $('#shop_category').html(shopCategory);
                $('#shop_category').attr('disabled', 'disabled');
                $('#area').html(tempAreaHtml);
                $("#area option[data-id='" + shop.area.areaId + "']").attr("selected", "selected");
            }
        });
    }
    // 提交按钮，将店铺信息提交到后台进行处理 url根据isEdit来分情况选择
    $('#submit').click(function(){
        var shop = {};
        if(isEdit){
            shop.shopId = shopId;
        }
        shop.shopName = $('#shop_name').val();
        shop.shopAddr = $('#shop_addr').val();
        shop.phone = $('#shop_phone').val();
        shop.shopDesc = $('#shop_desc').val();

        /* 使用的下拉菜单来进行选择，获取值的方法 */
        shop.shopCategory = {
            shopCategoryId : $('#shop_category').find('option').not(function(){
                return !this.selected;
            }).data('id')
        };
        shop.area = {
            areaId : $('#area').find('option').not(function(){
                return !this.selected;
            }).data('id')
        };

        /*  获取的是上传图片的输入流 */
        var shopImg = $('#shop_img')[0].files[0];
        //  在ajax中传递的参数
        var formData = new FormData();
        //  参数的内容，分别是上面的shop和shop图片
        formData.append('shopImg', shopImg);
        formData.append('shopStr', JSON.stringify(shop));
        var verifyCodeActual = $('#j_kaptcha').val();
        if(!verifyCodeActual){
            $.toast('请输入验证码！');
            return;
        }
        formData.append('verifyCodeActual', verifyCodeActual);
        /* 使用ajax提交到后台 */
        $.ajax({
            url:(isEdit?editShopUrl:registerShopUrl),
            type:'POST',
            data:formData,
            contentType:false,
            processData:false,
            cache:false,
            success:function(data){
                if(data.success){
                    $.toast('提交成功！');
                } else{
                    $.toast('提交失败！' + data.errMsg);
                }
                /* 更换验证码 */
                $('#kaptcha_img').click();
            }
        });
    });
});


