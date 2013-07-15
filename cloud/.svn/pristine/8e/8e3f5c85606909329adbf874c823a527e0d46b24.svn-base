Ext.onReady(function () {
	//服务器-出风口温度表单
	wantGridePanel('fkTemperaturePanel','fkTemperature',fkTemperatureCm,Ext.data.StoreManager.lookup('fkTemperatureStore'),'服务器-出风口温度表单(摄氏度)');
	//服务器-进风口温度表单
	wantGridePanel('outIntelTemplPanel','outIntelTempl',fkTemperatureCm,Ext.data.StoreManager.lookup('intelTempStore'),'服务器-进风口温度表单(摄氏度)');
	
	//服务器-功耗
	wantGridePanel('servicePowerConsumePanel','servicePowerConsume',servicePowerConsumeCm,Ext.data.StoreManager.lookup('servicePowerConsumeStore'),'服务器-功耗(瓦特)');
	//服务器-功耗
	wantGridePanel('cpuPowerConsumePanel','cpuPowerConsume',servicePowerConsumeCm,Ext.data.StoreManager.lookup('cpuPowerConsumeStore'),'CPU-功耗');
	//服务器-功耗
	wantGridePanel('memeryPowerConsumePanel','memeryPowerConsume',servicePowerConsumeCm,Ext.data.StoreManager.lookup('memeryPowerConsumeStore'),'内存-功耗');
	
	//电源各个要素
	wantGridePanel('batteryElementPanel','batteryElement',batteryElementCm,Ext.data.StoreManager.lookup('batteryElementStore'),'电池--各电池要素');
	
	//风扇冗余
	wantGridePanel('fanRedundancyPanel','fanRedundancy',fanRedundancyCm,Ext.data.StoreManager.lookup('fanRedundancyStore'),'风扇--风扇冗余');
	
	//风扇探测器列表
	wantGridePanel('fanDetectorPanel','fanDetector',fanDetectorCm,Ext.data.StoreManager.lookup('fanDetectorStore'),'风扇--探测器列表');
	
	//CPU
	wantGridePanel('cpuPanel','cpu',cpuCm,Ext.data.StoreManager.lookup('cpuStore'),'CPU');
	
	//电源--当前读数
	wantGridePanel('batteryIndicationPanel','batteryIndication',batteryIndicationCm,Ext.data.StoreManager.lookup('batteryIndicationStore'),'电源--当前读数');
	
//	//电源--温度
//	wantGridePanel('batteryTemperaturePanel','batteryTemperature',batteryTemperatureCm,Ext.data.StoreManager.lookup('batteryTemperatureStore'),'电源--温度');
});