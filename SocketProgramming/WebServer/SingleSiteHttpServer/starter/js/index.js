function clock()
{
var now = new Date();
var clockDivision = document.getElementById("clock");
clockDivision.innerHTML = now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();
setTimeout(clock, 1000);
}
window.addEventListener("load", clock);