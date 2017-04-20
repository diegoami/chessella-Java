<script>
if (!document.layers)
document.write('<div id="divStayTopright" style="position:absolute;">')
</script>
<layer id="divStayTopright">
<script type="text/javascript"><!--
google_ad_client = "pub-1926857519128558";
google_ad_width = 160;
google_ad_height = 600;
google_ad_format = "160x600_as";
google_ad_type = "text_image";
google_ad_channel ="";
google_color_border = "000000";
google_color_bg = "F0F0F0";
google_color_link = "0000FF";
google_color_text = "000000";
google_color_url = "008000";
//--></script>
<script type="text/javascript"
  src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
</script>
<!-- stopspan google_adsenseAG Ads-->



<!-- startspan javascript_right_scrolling-->
</layer>
<script type="text/javascript">
var verticalpos="fromtop"

if (!document.layers)
document.write('</div>')

function JSFX_FloatTopDiv()
{
var startX = 40,
startY = 20;
var ns = (navigator.appName.indexOf("Netscape") != -1);
var d = document;
function ml(id)
{
var el=d.getElementById?d.getElementById(id):d.all?d.all[id]:d.layers[id];
if(d.layers)el.style=el;
el.sP=function(x,y){this.style.right=x;this.style.top=y;};
el.x = startX;
if (verticalpos=="fromtop")
el.y = startY;
else{
el.y = ns ? pageYOffset + innerHeight : document.body.scrollTop + 
document.body.clientHeight;el.y -= startY;
}
return el;
}
window.stayTopright=function()
{
if (verticalpos=="fromtop"){
var pY = ns ? pageYOffset : document.body.scrollTop;
ftlObj.y += (pY + startY - ftlObj.y)/1;
}
else{
var pY = ns ? pageYOffset + innerHeight : document.body.scrollTop + 
document.body.clientHeight;ftlObj.y += (pY - startY - ftlObj.y)/1;
}
ftlObj.sP(ftlObj.x, ftlObj.y);
setTimeout("stayTopright()", 1);
}
ftlObj = ml("divStayTopright");
stayTopright();
}
JSFX_FloatTopDiv();
</script>
<!-- stopspan javascript_right_scrolling-->

