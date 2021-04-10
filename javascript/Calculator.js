function show1(a)
{
    if(a.value.trim() == '0')
        a.value = ""; 
}
function show2(a)
{
    if(a.value.trim() == '')
        a.value = '0';
}
function calculate(a)
{
    tf = document.getElementById('tf');
    tfVal = tf.value;
    b = a.value;
    switch(b)
    {
        case "B":
            tf.value = tfVal.slice(0, -1);
            break;
        case "C":
            tf.value = "";
            break;
        case "1/x":
            tf.value = eval("1/"+tfVal);
            break;
        case "sqrt":
            tf.value = Math.sqrt(parseInt(tfVal));
            break;
        case "=":
            tf.value = eval(tfVal);
            break;
        default:
            tf.value = tfVal+b;
            break;
    }
    if (tf.value.trim() == '')
        tf.value = '0';
}