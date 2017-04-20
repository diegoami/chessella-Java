function BoardForwarder() { }


BoardForwarder.getBoardRepresentation = function( p0, p1,callback)
{
    DWREngine._execute('dwr', 'BoardForwarder', 'getBoardRepresentation', p0, p1,callback);
}

BoardForwarder.getMoveForward = function( p0, p1,callback)
{
    DWREngine._execute('dwr', 'BoardForwarder', 'getMoveForward', p0, p1,callback);
}

BoardForwarder.getMoveAllForward = function( p0,p1, callback)
{
    DWREngine._execute('dwr', 'BoardForwarder', 'getMoveAllForward', p0, p1,callback);
}

BoardForwarder.getMoveFiveForward = function( p0, p1,callback)
{
    DWREngine._execute('dwr', 'BoardForwarder', 'getMoveFiveForward', p0, p1,callback);
}

BoardForwarder.getMovePrevious = function( p0, p1,callback)
{
    DWREngine._execute('dwr', 'BoardForwarder', 'getMovePrevious', p0, p1,callback);
}

BoardForwarder.getMoveAllPrevious = function( p0, p1,callback)
{
    DWREngine._execute('dwr', 'BoardForwarder', 'getMoveAllPrevious', p0, p1,callback);
}
BoardForwarder.getMoveFivePrevious = function( p0, p1,callback)
{
    DWREngine._execute('dwr', 'BoardForwarder', 'getMoveFivePrevious', p0, p1,callback);
}
