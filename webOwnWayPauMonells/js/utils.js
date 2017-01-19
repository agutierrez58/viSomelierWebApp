function trim(texto) {
    while((texto.length>0)&&(texto.charAt(0)==' '))
        texto=texto.substring(1);
    while((texto.length>0)&&(texto.charAt(texto.length-1)==' '))
        texto=texto.substring(0,texto.length-1);
    return texto;
}


function isnumeric(texto) {
    for(var i=0;i<texto.length;i++)
        if((texto.charAt(i)<'0')||(texto.charAt(i)>'9'))
            return false;
    return true;
}


function fieldsok() {
    var ret=true;
    var c=0;
    while(arguments[c]!=undefined) {
        arguments[c].value=trim(arguments[c].value);
        if(arguments[c].value=='')
            ret=false;
        c++;
    }
    if(ret==false)
        alert('hay campos vacios.');
    return ret;
}
