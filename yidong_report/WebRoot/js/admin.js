$(function(){
	var $dg=$("#dg");
	
	var $deleteButten=$("#deleteButten");//;鍒犻櫎鎸夐挳
	var $addButten = $("#addButten");//
	var $listTable=$("#listTable");
	var $editButten = $("#editButten");
	
	$addButten.bind("click",function(){
		var url = $(this).attr("url");
		window.location.href = url;
	});
	$editButten.bind("click",function(){
		var url = $(this).attr("url");
		var row = $dg.datagrid('getSelected');
		if(row){
			window.location.href=url+"?id="+row.id;
		}else{
			alert("请选择一行！");
		}
		
	});
	
	//删除
	$deleteButten.bind("click",function(){
		var url=$(this).attr("url");
		var $ids= $(".datagrid-cell-check").children(":checked");
		if($ids.length<=0){
			alert("请选择要删除的数据！")
			return; 
		}
		 var r = confirm('是否确认删除');
	 
		if (r){    
			var dropIds = new Array(); 
			for(var i=0;i<$ids.length;i++){
				dropIds.push($($ids[i]).val());
			}  
			 $.ajax({  
                 type:'post',  
                 traditional :true,  
                 url:url,  
                 data:{'ids':dropIds}, 
                 dataType:"json",
                 success:function(data){
                	 
            	   var messagerHtml='<div id="messager" >';
            		messagerHtml+='<label style="padding:10px; background:#9FC;">'+data.message+'</label>';
            		messagerHtml+='</div>';
            		$('body').append(messagerHtml);
            		var num=0;
            		var t= setInterval(function(){
            			num++;
            			if(num==2){
            			 $("#messager").animate({width:0,opacity:0},"slow");
            			};
            			
            			if(num==3){
            				 $("#messager").remove();
            				 clearInterval(t);
            			}
            		},1000);
            		
            		$dg.datagrid('reload');
                 }  
             });
		} 
	})
	
	
});