function GameSetter() { }


GameSetter.setTagFor= function( p0, p1,callback)
{
    DWREngine._execute('dwr', 'GameSetter', 'setTagFor', p0, p1,callback);
}
