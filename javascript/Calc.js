function sum()
{
    a = parseInt(document.getElementById("f").value);
    b = parseInt(document.getElementById("s").value);
    c = a+b;
    document.getElementById("result").innerHTML=c;
}
function sub()
{
    a = parseInt(document.getElementById("f").value);
    b = parseInt(document.getElementById("s").value);
    c = a-b;
    document.getElementById("result").innerHTML=c;
}
function multi()
{
    a = parseInt(document.getElementById("f").value);
    b = parseInt(document.getElementById("s").value);
    c = a*b;
    document.getElementById("result").innerHTML=c;
}
function divide()
{
    a = parseInt(document.getElementById("f").value);
    b = parseInt(document.getElementById("s").value);
    c = a/b;
    document.getElementById("result").innerHTML=c;
}