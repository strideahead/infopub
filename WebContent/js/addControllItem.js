/**
 * 
 */
window.onload = function(){
        var oButtons = document.getElementsByTagName('input');
        for(var i = 0; i < oButtons.length; i++){(function(){
            if(this.type == 'button'){
                this.onclick = function(){
                    document.forms[0].action = this.id + '.jsp';
                    alert(document.forms[0].action);
                    document.forms[0].submit();
                };
            }
        }).call(oButtons[i])}
 };