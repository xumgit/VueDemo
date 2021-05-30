const Mock = require('mockjs');
const getUrlParamater = function(req) {
	let paginateObj = {
		current: 1,
		rowCount: 5
	};
	if (!req.hasOwnProperty('url')) { return;}
	var url = req.url;
	if (url.split("?").length  < 2) {return;}
	var params = url.split("?")[1];
	var para = params.split("&");
	if (para.length == 0) {return;}
	for (var i = 0; i < para.length; i++) {
		var paraObj = para[i].split("=");
		if (paraObj.length < 2) {continue;}
		paginateObj[paraObj[0]] = paraObj[1];
	}
	return paginateObj;
}

const paginatePage = function(paginateObj, data) {
	let current = paginateObj.current; //当前第几页
	let rowCount = paginateObj.rowCount; //每页显示大小
	data.current = current;
	data.rowCount = rowCount;
	let srcRows = data.rows;
	let srcRowLength = srcRows.length;
	let start = (parseInt(current) - 1) * rowCount;
	let end = parseInt(current) * rowCount;
	if (srcRowLength <= end) {
		end = srcRowLength;
	}
	let destRowsObj = [];
	//console.log("start:" + start + ",end:" + end + ",srcRowLength:" + srcRowLength);
	for (let i = start; i < end; i++) {
		destRowsObj.push(srcRows[i]);
	}
	data.rows = destRowsObj;
	return data;
}

const deviceData = {"current":1,"rowCount":10,"total":3,"rows":[
				{"TVRoomID":"10001000","tv_firmware_Identifier":"TPM1531HE.5.1000.5.065","TVIPAddress":"192.168.0.1040","upload_progress":"ST","Lastonline":"20200415202158","TVSerialNumber":"10006463g092105","TVUniqueID":"10006463g092105542aa2c5a21e","TVModelNumber":"49HFl7011T/12","Type":"2016 SS","clone_color":"black","TVMACAddress":"54:2a:a2:c5:a2:1e","LastCloneRename":"{\"old\":\"Unknown\",\"new\":\"Unknown\"}","tv_clone_Identifiers":"27/03/2018:10:47","fw_color":"black","PowerStatus":"offline","CreatedDate":"2018/03/28-10:18:05","clone_mode":"Upgrade","TVName":"TV 28 Mar 2018 10:18:05","VSecureTVID":"800000000000000f6921","Id":"10006463g092105542aa2c5a21e"},
				{"TVRoomID":"10011001","tv_firmware_Identifier":"TPM1531HE.5.1001.5.065","TVIPAddress":"192.168.0.1041","upload_progress":"ST","Lastonline":"20200415202158","TVSerialNumber":"10016463g092105","TVUniqueID":"10016463g092105542aa2c5a21e","TVModelNumber":"49HFl7011T/12","Type":"2016 SS","clone_color":"black","TVMACAddress":"54:2a:a2:c5:a2:1e","LastCloneRename":"{\"old\":\"Unknown\",\"new\":\"Unknown\"}","tv_clone_Identifiers":"27/03/2018:10:47","fw_color":"black","PowerStatus":"offline","CreatedDate":"2018/03/28-10:18:05","clone_mode":"Upgrade","TVName":"TV 28 Mar 2018 10:18:05","VSecureTVID":"800000000000000f6921","Id":"10016463g092105542aa2c5a21e"},
				{"TVRoomID":"10021002","tv_firmware_Identifier":"TPM1531HE.5.1002.5.065","TVIPAddress":"192.168.0.1042","upload_progress":"ST","Lastonline":"20200415202158","TVSerialNumber":"10026463g092105","TVUniqueID":"10026463g092105542aa2c5a21e","TVModelNumber":"49HFl7011T/12","Type":"2016 SS","clone_color":"black","TVMACAddress":"54:2a:a2:c5:a2:1e","LastCloneRename":"{\"old\":\"Unknown\",\"new\":\"Unknown\"}","tv_clone_Identifiers":"27/03/2018:10:47","fw_color":"black","PowerStatus":"offline","CreatedDate":"2018/03/28-10:18:05","clone_mode":"Upgrade","TVName":"TV 28 Mar 2018 10:18:05","VSecureTVID":"800000000000000f6921","Id":"10026463g092105542aa2c5a21e"}
			 ]};
Mock.mock('/index/getDevices', /post|get/i, deviceData);


const userData = {"current":1,"rowCount":10,"total":8,"rows":[
{"address":"北京安徽","province":"北京","sex":"male","name":"admin","id":"7","age":"10","email":"admin@163.com"},
{"address":"安徽北京","province":"上海","sex":"male","name":"admin","id":"8","age":"30","email":"admin@163.com"},
{"address":"安徽北京","province":"安徽","sex":"male","name":"admin","id":"9","age":"20","email":"admin@163.com"},
{"address":"安徽省蚌埠市","province":"上海","sex":"female","name":"admin","id":"10","age":"20","email":"admin1@163.com"},
{"address":"Room 102, No. 78, Lane 1395, Yunshan Road","province":"上海","sex":"female","name":"xum","id":"12","age":"20","email":"xum@163.com"},
{"address":"福建省厦门市翔安区","province":"安徽","sex":"male","name":"meng xu","id":"13","age":"10","email":"123@163.com"},
{"address":"福建省厦门市翔安区","province":"安徽","sex":"male","name":"meng xu","id":"14","age":"10","email":"135@567.com"},
{"address":"福建省厦门市翔安区","province":"上海","sex":"female","name":"meng xu","id":"15","age":"10","email":"456@123.com"}]};
const userDataFun = function(req) {
	let destData = Object.assign({}, userData);
	//console.log("abc:" + JSON.stringify(req));
	let paginateObj = getUrlParamater(req);
	return paginatePage(paginateObj, destData);
};
Mock.mock(new RegExp('/user/getUsers/*'), /post|get/i, userDataFun);


const seckillData = {"current":1,"rowCount":10,"total":10,"rows":[
{"create_time":"2020-05-27 19:45:31.0","seckill_id":"1000","user_id":"1","state":"1"},
{"create_time":"2020-05-27 19:45:31.0","seckill_id":"1000","user_id":"2","state":"1"},
{"create_time":"2020-05-27 19:45:31.0","seckill_id":"1000","user_id":"3","state":"1"},
{"create_time":"2020-05-27 19:45:31.0","seckill_id":"1000","user_id":"4","state":"1"},
{"create_time":"2020-05-27 19:45:31.0","seckill_id":"1000","user_id":"5","state":"1"},
{"create_time":"2020-05-27 19:45:31.0","seckill_id":"1000","user_id":"6","state":"1"},
{"create_time":"2020-05-27 19:45:31.0","seckill_id":"1000","user_id":"7","state":"1"},
{"create_time":"2020-05-27 19:45:31.0","seckill_id":"1000","user_id":"8","state":"1"},
{"create_time":"2020-05-27 19:45:31.0","seckill_id":"1000","user_id":"9","state":"1"},
{"create_time":"2020-05-27 19:45:31.0","seckill_id":"1000","user_id":"10","state":"1"}]};
Mock.mock(new RegExp('/seckill/getSuccessKilledData/*'), /post|get/i, seckillData);