/**
 * 根据module_set 值勾选moduleset 列表
 */

function CheckOneModule(iModule_Set, checkbox, bit){
	if((iModule_Set & (1 << bit)) != 0){
    	if(bit == 0  || bit==1 || bit == 2 || bit==3 || bit==5 || bit==8){
			checkbox.removeAttr("checked");
			checkbox.next('label').css('color','black');
		}else{
			checkbox.attr("checked", "true");
		    checkbox.next('label').css('color','red');
		}
    }
    else
    {
    	if(bit == 0  || bit==1 || bit == 2 || bit==3 || bit==5 || bit==8){
    		checkbox.attr("checked", "true");
		    checkbox.next('label').css('color','red');
    	}else{
    		checkbox.removeAttr("checked");
    		checkbox.next('label').css('color','black');
    	}
    }
}     
function fill_module_set(iModule_Set)
{
	CheckOneModule(iModule_Set, $("#module0"), 0);
    CheckOneModule(iModule_Set, $("#module1"), 1);
    CheckOneModule(iModule_Set, $("#module2"), 2);
    CheckOneModule(iModule_Set, $("#module3"), 3);
    CheckOneModule(iModule_Set, $("#module4"), 4);
    CheckOneModule(iModule_Set, $("#module5"), 5);
    CheckOneModule(iModule_Set, $("#module6"), 6);
    CheckOneModule(iModule_Set, $("#module7"), 7);
    CheckOneModule(iModule_Set, $("#module8"), 8);
    CheckOneModule(iModule_Set, $("#module9"), 9);
    CheckOneModule(iModule_Set, $("#module10"), 10);
    CheckOneModule(iModule_Set, $("#module11"), 11);
    CheckOneModule(iModule_Set, $("#module12"), 12);
    CheckOneModule(iModule_Set, $("#module13"), 13);
    CheckOneModule(iModule_Set, $("#module14"), 14);
    CheckOneModule(iModule_Set, $("#module15"), 15);
    CheckOneModule(iModule_Set, $("#module16"), 16);
    CheckOneModule(iModule_Set, $("#module17"), 17);	
}
function setchecked(obj)
{
	if(obj.is(':checked')){
	//if(obj.checked){
		obj.next('label').css('color','blue');
	}else{
		obj.next('label').css('color','blue');
	}
}








