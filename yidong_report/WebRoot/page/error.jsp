<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>DIV+JS点击弹处层代码,可以根据id不同在一个页面多次使用</title>
<meta name="keywords" content="DIV+JS点击弹处层代码,可以根据id不同在一个页面多次使用" />
<meta name="description" content="DIV+JS点击弹处层代码,可以根据id不同在一个页面多次使用" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
.white_content {
 display: none;
 position:absolute;
 top: 25%;
 left: 25%;
 width: 50%;
 z-index:9999;
}
</style>
<script>
function pageX(elem){
			return elem.offsetParent ?
				elem.offsetLeft + pageX(elem.offsetParent) :
				elem.offsetLeft;
		}
		function pageY(elem){
			return elem.offsetParent ?
				elem.offsetTop + pageY(elem.offsetParent) :
				elem.offsetTop;
		}
var currDivIndex=100;
var currTop;
var currLeft;
var currDiv = [false,false,false,false,false];
var currDivTag = [false,false,false,false,false];
var currDiv1;
function showss(tag){
	var SonContent=document.getElementById(tag);
	SonContent.style.display='block';
	var x = pageX(SonContent),
	y = pageY(SonContent);
	currTop = [+y,+y+10,+y+20,+y+30,+y+40];
	currLeft = [+x,+x+10,+x+20,+x+30,+x+40];
	show('SonContent1');
}
function show(tag){
 var SonContent=document.getElementById(tag);
 SonContent.style.display='block';
 currDivIndex++;
 SonContent.style.zIndex=currDivIndex;
	//if(currDiv1 == tag.slice(-1)){
//	 currTop = pageY(SonContent);
//	 currLeft = pageX(SonContent);
// return;
// }
var currDivs = document.getElementsByTagName("div");
 var arr = [];
 var len = arr.length;
 for(var i = 0,j = currDiv.length;i < j;i++){
	// console.log(tag.slice(-1));
	 if(!currDiv[i] && !currDivTag[(+tag.slice(-1))-1]){
		len = i;
		SonContent.style.top = +currTop[len] + "px";
	 SonContent.style.left = +currLeft[len] + "px";
	 currDiv[i] = true;
	 currDivTag[(+tag.slice(-1))-1] = true;
		return;
	 }
 }
currDiv1 = 1;
 }
function hide(tag,e){
console.log("=============!");
 e = e || window.event;
 var SonContent=document.getElementById(tag);
 var top = +pageY(SonContent);
 SonContent.style.display='none';
 for(var i = 0,j = currTop.length;i < j;i++){
	 if(top == currTop[i]){
		 currDiv[i] = false;
	 }
 }
currDivTag[(+tag.slice(-1))-1] = false;
 var currDivs = document.getElementsByTagName("div");
 var arr = [];
 for(var i = 0,j = currDivs.length;i < j;i++){
	 if(currDivs[i].style.display == "block"){
		arr.push(currDivs[i]);
	 }
 }
arr.sort()
var len = arr.length;
	if(len > 0){
	currDiv1 = +arr[len-1].id.slice(-1);
	}
// currDivIndex--;
if(e && e.stopPropagation){  
            //因此它支持W3C的stopPropagation()方法  
	e.stopPropagation();  
}else{  
            //否则我们使用ie的方法来取消事件冒泡  
	e.cancelBubble = true;  
}  
 return false;
}
</script>
</head>
<body onload="showss('SonContent1')">
<a href="javascript:void(0)" onclick="show('SonContent1')">打开DIV1</a>
<div style="width:300px; height:200px; background:#ff92d2;" id="SonContent1" class="white_content" onclick="show('SonContent1')"> <a href="javascript:hide('SonContent1',event)"> 关闭DIV1</a></div>
       
 
 <a href="javascript:void(0)" onclick="show('SonContent2')">打开DIV2</a>
<div style="width:300px; height:200px; background:#92b6ff;" id="SonContent2" class="white_content" onclick="show('SonContent2')"> <a href="javascript:hide('SonContent2',event)"> 关闭DIV2</a></div>

 <a href="javascript:void(0)" onclick="show('SonContent3')">打开DIV3</a>
<div style="width:300px; height:200px; background:#cccccc;" id="SonContent3" class="white_content" onclick="show('SonContent3')"> <a href="javascript:hide('SonContent3',event)"> 关闭DIV3</a></div>

 <a href="javascript:void(0)" onclick="show('SonContent4')">打开DIV4</a>
<div style="width:300px; height:200px; background:#ccccdd;" id="SonContent4" class="white_content" onclick="show('SonContent4')"> <a href="javascript:hide('SonContent4',event)"> 关闭DIV4</a></div>

 <a href="javascript:void(0)" onclick="show('SonContent5')">打开DIV5</a>
<div style="width:300px; height:200px; background:#ccccdd;" id="SonContent5" class="white_content" onclick="show('SonContent5')"> <a href="javascript:hide('SonContent5',event)"> 关闭DIV5</a></div>
</body>
</html>