//出风口温度
var fkTempStore=Ext.create('Ext.data.Store', {
    storeId:'fkTemperatureStore',
    fields:['sensorId', 'entityId','sensorType','sensorReading','stat','lowerNonRecoverable','lowerCritical','lowerNonCritical',
            'upperNonCritical','upperCritical','upperNonRecoverable','assertionEvents','assertionsEnabled','deassertionsEnabled']
});
//进风口温度
var outIntelTempStore=Ext.create('Ext.data.Store', {
    storeId:'intelTempStore',
    fields:['sensorId', 'entityId','sensorType','sensorReading','stat','lowerNonRecoverable','lowerCritical','lowerNonCritical',
            'upperNonCritical','upperCritical','upperNonRecoverable','assertionEvents','assertionsEnabled','deassertionsEnabled']
});
//服务器功耗
var sysPowerStore = Ext.create('Ext.data.Store', {
    storeId:'servicePowerConsumeStore',
    fields:['id', 'stats', 'now_PowerWaste','max_PowerWaste','min_PowerWaste','avg_PowerWaste']
});
//cpu功耗
var cpuPowerStore = Ext.create('Ext.data.Store', {
    storeId:'cpuPowerConsumeStore',
    fields:['id', 'stats', 'now_PowerWaste','max_PowerWaste','min_PowerWaste','avg_PowerWaste']
});
//内存功耗
var memeryPowerStore = Ext.create('Ext.data.Store', {
    storeId:'memeryPowerConsumeStore',
    fields:['id', 'stats', 'now_PowerWaste','max_PowerWaste','min_PowerWaste','avg_PowerWaste']
});

//电池各个要素	
var batteryStore = Ext.create('Ext.data.Store', {
    storeId:'batteryElementStore',
    fields:['sensorId', 'entityId','sensorType','stat']
});

//风扇冗余	
var fanReduancyStore = Ext.create('Ext.data.Store', {
    storeId:'fanRedundancyStore',
    fields:['sensorId', 'entityId','sensorType','stat']
});
//风扇探测器列表
var detectorStore = Ext.create('Ext.data.Store', {
    storeId:'fanDetectorStore',
    fields:['sensorId', 'entityId','sensorType','sensorReading','stat','lowerNonRecoverable','lowerCritical','lowerNonCritical',
            'upperNonCritical','upperCritical','upperNonRecoverable','assertionEvents','assertionsEnabled','deassertionsEnabled']
});

//CPU
var cpuStore = Ext.create('Ext.data.Store', {
    storeId:'cpuStore',
    fields:['deviceId', 'entityId','deviceAccessAddress','slaveAddress','channelNumber','lunbus','deviceTypeModifier','oem']
});

//电源--当前读数
var powerCurrtenStore = Ext.create('Ext.data.Store', {
    storeId:'batteryIndicationStore',
    fields:['sensorId', 'entityId','sensorType','sensorReading','stat','lowerNonRecoverable','lowerCritical','lowerNonCritical',
            'upperNonCritical','upperCritical','upperNonRecoverable','assertionEvents','assertionsEnabled','deassertionsEnabled']
});

////电源--温度
//var powerTemperStore = Ext.create('Ext.data.Store', {
//    storeId:'batteryTemperatureStore',
//    fields:['detectorName','readValue','alarm_min_thresholdValue','alarm_max_thresholdValue','minBugThresholdValue','maxBugThresholdValue'],
//});
var timeLoad = doloadingFunc(Ext.getBody());
timeLoad.show();
Ext.Ajax.request({   
	url: ctx+'/serviceinfo/serviceinfo/serviceinfo!getServerRealTimeData.action',
	method: 'post', 
	params:{'model.id':serverId},
	loadMask:true,
	success:function(form,action){   
		var obj = Ext.decode(form.responseText);
		var fk_temperature = obj.resultList.sys_temper_list;  //出风口温度
		var outIntel_temper = obj.resultList.inletTemp_list;  //进风口温度
		var sys_powerWaste = obj.resultList.sys_powerWaste_list; //系统功耗
		var cpu_powerWaste = obj.resultList.cpu_powerWaste_list;//cpu功耗
		var memory_powerWaste = obj.resultList.memory_powerWaste_list;  //内存功耗
		var battery = obj.resultList.battery_list; //电池各要素
		var fan_reduancy = obj.resultList.fan_redundancy_list; //风扇冗余
		var detector = obj.resultList.fan_detector_list; //风扇探测器列表
		var cpu = obj.resultList.cpu_state_list; //cpu
		var powerTemper = obj.resultList.power_reading_list;	//电源温度
		fkTempStore.loadData(fk_temperature);
		outIntelTempStore.loadData(outIntel_temper);
		sysPowerStore.loadData(sys_powerWaste);
		cpuPowerStore.loadData(cpu_powerWaste);
		memeryPowerStore.loadData(memory_powerWaste);
		batteryStore.loadData(battery);
		fanReduancyStore.loadData(fan_reduancy);
		detectorStore.loadData(detector);
		cpuStore.loadData(cpu);
		powerCurrtenStore.loadData(powerTemper);
		timeLoad.hide();
	}
});