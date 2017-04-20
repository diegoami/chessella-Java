
var divstate;
var divarray = new Array();
var fliparray = new Array();
var markedMove;

var deleteid;
var deleteGameProxy = function(resultString) {
  deleteinfo(resultString, deleteid);
};

var deleteMetaData = { callback:deleteGameProxy };
	
var switchid;
var switchGameProxy = function(resultString) {
  switchinfo(resultString, switchid);
};

var switchMetaData = { callback:switchGameProxy };	
	
function deleteGame(id) {
  deleteid = id;
  GameTableSetter.switchDeleteStateById( deleteid, deleteMetaData);
}	

function switchGame(id) {
  switchid = id;
  GameTableSetter.switchGameStateById( switchid, switchMetaData);
}	

	
function showBoard(divString, s1, s2, s3, s4, s5, s6)
{
    
    stateString = s1+"_"+s2+"_"+s3+"_"+s4+"_"+s5+"_"+s6;
    divstate = divString;	
    divarray[divstate] = stateString;
	flipvalue = getFlipvalue(divString);
    markmove(stateString);

    BoardForwarder.getBoardRepresentation(loadinfo, stateString, flipvalue );
	listarray();
}


function saveTags(id) {
	var tags = document.getElementById("tags");
	
	GameSetter.setTagFor(oinfo,id,tags.value);
}

function  getDivvalue(divString) {
	divvalue = divarray[divString];
	if (divvalue == null) {
		divarray[divString] = divString;
		divvalue = divString;
	}
	return divvalue;

}

function  getFlipvalue(divString) {
	flipvalue = fliparray[divString];
	if (flipvalue  == null) {
		fliparray[divString] = "0";
		flipvalue = fliparray[divString];
	} 
	    
	return flipvalue;

}

function showMoveForward(divString) {
    divvalue = getDivvalue(divString);
	divstate = divString;
	flipvalue = getFlipvalue(divString);
    BoardForwarder.getMoveForward(loadarrowinfo, divvalue, flipvalue);
}
	
function flip(divString) {
    flipvalue = getFlipvalue(divString);
	if (flipvalue == "0") {
	  flipvalue = "1";  
	} else {
	  flipvalue = "0";		
	}
	fliparray[divString] = flipvalue;
	showCurrentBoard(divString);
}
	
	
	
function showCurrentBoard(divString) {
    divvalue = getDivvalue(divString);
	divstate = divString;

	flipvalue = getFlipvalue(divString);
    BoardForwarder.getBoardRepresentation(loadinfo, divvalue , flipvalue );
	
}

function showMoveFiveForward(divString) {
    divvalue = getDivvalue(divString);
	divstate = divString;

	flipvalue = getFlipvalue(divString);
    BoardForwarder.getMoveFiveForward(loadarrowinfo, divvalue, flipvalue);
}
	
	
function showMoveAllForward(divString) {
    divvalue = getDivvalue(divString);
	divstate = divString;

	flipvalue = getFlipvalue(divString);
    BoardForwarder.getMoveAllForward(loadarrowinfo, divvalue, flipvalue);
}

function showMovePrevious(divString) {
    divvalue = getDivvalue(divString);
	divstate = divString;
	flipvalue = getFlipvalue(divString);
    BoardForwarder.getMovePrevious(loadarrowinfo, divvalue, flipvalue);
}

function showMoveFivePrevious(divString) {
    divvalue = getDivvalue(divString);
	divstate = divString;
	flipvalue = getFlipvalue(divString);
    markmove(divString);
    BoardForwarder.getMoveFivePrevious(loadarrowinfo, divvalue, flipvalue);
}

function showMoveAllPrevious(divString) {
    divvalue = getDivvalue(divString);
	divstate = divString;
	flipvalue = getFlipvalue(divString);
    markmove(divString);

    BoardForwarder.getMoveAllPrevious(loadarrowinfo, divvalue, flipvalue);
}

function resetBoard(divString)
{
    
    divstate = divString;	
    
    divarray[divString] = divstate;
	flipvalue = getFlipvalue(divString);

    BoardForwarder.getBoardRepresentation(loadinfo, divString, flipvalue);

	listarray();
}

function listarray() {
	listString = "";
	for ( var word in divarray ) 
		listString += word +" : "+divarray[word] + " , ";
}
  
function loadarrowinfo(statestring, flipvalue)
{
	if (statestring != "") {
		fliploc = getFlipvalue(divstate);

	    BoardForwarder.getBoardRepresentation(loadinfo, statestring, fliploc);
	    markmove(statestring);
	    
	    divarray[divstate] = statestring;
	}
}

function markmove(statestring) 
{

  if (markedMove != null) {
	var markedSpan = document.getElementById(markedMove);
	if (markedSpan != null) {
	
    	markedSpan.className="normal";


    }
  }
  
  var spanString = "S"+statestring;

  var currentSpan = document.getElementById(spanString);

  if (currentSpan != null) {
  
	  currentSpan.className="current";
	  
  }
  markedMove = spanString;
  
  
  
}
function oinfo(data) {
}
function deleteinfo(value, deleteid ) {
	DWRUtil.setValue("delete_"+deleteid, value);
	document.getElementById('deletecaption').style.visibility='visible';
}

function switchinfo(value, switchid ) {
	DWRUtil.setValue("public_"+switchid , value);
}
	
function loadinfo(statestring)
{

    DWRUtil.setValue(divstate, statestring);
}

function alertinfo(data)
{

    alert(data);
}

function init()
{
//    DWRUtil.useLoadingMessage();
}
