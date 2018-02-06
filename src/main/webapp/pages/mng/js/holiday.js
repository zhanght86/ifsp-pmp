function genCalendar(year,div,def) {
	var html = "<table class='calendar-holiday'><tr><td id='一月' align='center'></td><td id='二月'  align='center'></td><td id='三月'  align='center'></td></tr><tr><td id='四月'  align='center'></td><td id='五月'  align='center'></td><td id='六月' align='center'></td></tr><tr><td id='七月' align='center'></td><td id='八月' align='center'></td><td id='九月' align='center'></td></tr><tr><td id='十月' align='center'></td><td id='十一月' align='center'></td><td id='十二月' align='center'></td></tr></table>";
	$(div).html(html);
	var holidayDef = new Array();
	var strHoliday = def;
	if(strHoliday!=null && strHoliday.length>0){
		holidayDef.splice(0,holidayDef.length);
		for(var i=0;i<366;i++){
			if(strHoliday.substr(i,1)=="1")
				holidayDef.push(false);
			else
				holidayDef.push(true);
		}
	}else{
		for(var i=0;i<366;i++){
			holidayDef.push(false);
		}
	}
	var sunDay = 0;
	for ( var i = 0; i < 12; i++) {
		sunDay = genOneMonth(year, i, sunDay,holidayDef,strHoliday.length);
	}
	return holidayDef;
}
function genOneMonth(year, month, startSunDay,holidayDef,defLen) {
	var date = new Date(year, month, 1, 0, 0, 0, 0);
	var monthTable = [ "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月",
			"十月", "十一月", "十二月" ];
	var day = [ "日 ", "一 ", "二 ", "三 ", "四 ", "五 ", "六 " ];
	var container = document.getElementById(monthTable[month]);
	container.vAlign = "top";
	var table = document.createElement("table");
	table.className = "holiday-month";
	var rowHeader = table.insertRow(table.rows.length);
	rowHeader.className = "calendar-monthtr";
	var cell = rowHeader.insertCell(0);
	cell.colSpan = "7";
	cell.innerHTML = monthTable[month];
	cell.align = "center";
	var rowHeader = table.insertRow(table.rows.length);
	for ( var i = 0; i < 7; i++) {
		var cell = rowHeader.insertCell(i);
		cell.innerHTML = day[i];
	}
	rowHeader.className = "calendar-weektr";
	rowHeader = table.insertRow(table.rows.length);
	rowHeader.className = "calendar-day";
	for ( var i = 0; i < date.getDay(); i++) {
		var cell = rowHeader.insertCell(i);
		$(cell).addClass('calendar-day');
	}
	for ( var i = 0; i < 31; i++) {
		if (date.getDay() == 0) {
			rowHeader = table.insertRow(table.rows.length);
			rowHeader.className = "holiday-day";
		}
		var cell = rowHeader.insertCell(date.getDay() % 7);
		$(cell).html(date.getDate());
		$(cell).attr("title",date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());
		$(cell).addClass('calendar-day');
		if(defLen>0){
			if (!holidayDef[startSunDay]) {
				$(cell).removeClass('calendar-selected');
			} else {
				$(cell).addClass('calendar-selected');
			}
		}else{
			if(date.getDay()==0||date.getDay()==6) {
				$(cell).addClass('calendar-selected');
				holidayDef[startSunDay] = true;
			} else {
				holidayDef[startSunDay] = false;
			}
		}
		date.setDate(i + 2);
		cell.sunDay = startSunDay;
		$(cell).click(function() {
			holidayDef[this.sunDay] = !holidayDef[this.sunDay];
			if (holidayDef[this.sunDay]) {
				$(this).addClass('calendar-selected');
			} else {
				$(this).removeClass('calendar-selected');
			}
		});
		cell.id = "holiday_" + startSunDay;
		startSunDay++;
		if (date.getDate() == 1)
			break;
	}
	var childNode = container.firstChild;
	if (childNode != null)
		container.removeChild(container.firstChild);
	container.appendChild(table);
	return startSunDay;
}

function holidayArrayToStr(year,harray){
	var strHoliday = "";
	for(var i=0;i<366;i++){
		if(harray[i])
			strHoliday+="0";
		else
			strHoliday+="1";
	}
	if(!((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)){
		strHoliday=strHoliday.substring(365,0);
	}
	return strHoliday;
}


