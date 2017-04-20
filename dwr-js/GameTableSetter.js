function GameTableSetter() { }


GameTableSetter.deleteGameById = function( p0, callback)
{
    DWREngine._execute('dwr', 'GameTableSetter', 'deleteGameById', p0,callback);
}

GameTableSetter.switchGameStateById = function( p0, callback)
{
    DWREngine._execute('dwr', 'GameTableSetter', 'switchGameStateById', p0,callback);
}


GameTableSetter.switchDeleteStateById = function( p0, callback)
{
    DWREngine._execute('dwr', 'GameTableSetter', 'switchDeleteStateById', p0,callback);
}
