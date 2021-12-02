<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<img src="imgs/concert_hueihuei.jpg" id=mainImage alt="YsjImage" style="width:80%%;height:780px;">

<script>
var myImage=document.getElementById("mainImage");
var imageArray=["imgs/exhibition.jpg","imgs/concert_11month.png","imgs/orchestra.jpg","imgs/img.jpg"];
var imageIndex=0;

function changeImage(){
	myImage.setAttribute("src",imageArray[imageIndex]);
	imageIndex++;
	if(imageIndex>=imageArray.length){
		imageIndex=0;
	}
}
setInterval(changeImage,5000);
</script>

</html>

