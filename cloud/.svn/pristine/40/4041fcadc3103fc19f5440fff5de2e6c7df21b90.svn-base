package cn.com.kxcomm.contractmanage.web.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import cn.com.kxcomm.common.util.AppConfig;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.contractmanage.vo.DataRecordVo;

/**
 * 
* 功能描述:文件工具类
* @author chenliang 新增日期：2012-12-14
* @since ContractManage
 */
public class FileUtil {
	
	private static final Logger LOGGER = Logger.getLogger(FileUtil.class);
	private static XSSFWorkbook workbook = null;
	private static XSSFSheet sheet = null;
	private static XSSFRow rows = null;
	private static XSSFCell  cells = null;
	private FileUtil(){
		
	}
	
	/**
	 * 
	* 方法用途和描述: 生成excel文件
	* @param volist
	* @param filePath
	* @author chenliang 新增日期：2012-12-14
	* @since ContractManage
	 */
	public static void createExcelFile(List<List<DataRecordVo>> volist,String filePath,String fileName){
		try {
			LOGGER.info("path:"+filePath);
			File file = new File(filePath);
			if(!file.exists()){
				file.mkdir();
			}
			file = new File(filePath+File.separator+fileName);
			if(file.createNewFile()){
				FileOutputStream out = new FileOutputStream(file);
				workbook = new XSSFWorkbook();
					for (int i = 0; i < volist.size(); i++) {
						List<DataRecordVo> datavolist = volist.get(i);
						if(!BlankUtil.isBlank(volist)&&volist.size()>0){
							sheet = workbook.createSheet();
							rows = sheet.createRow(0);
							setCellTitial(rows);  //设置表头
							setCellValue(datavolist); //设置表单内容
						}else{
							setCellTitial(rows);  //设置表头
						}
					}
				workbook.write(out);
				out.close();
			}
		} catch (FileNotFoundException e) {
			LOGGER.error("createExcelFile fileNotFound error.",e);
		} catch (IOException e) {
			LOGGER.error("createExcelFile io error ",e);
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 设置sheet每列的表头
	* @param rows
	* @author chenliang 新增日期：2012-12-14
	* @since ContractManage
	 */
	private static void setCellTitial(XSSFRow rows){
		cells = rows.createCell(0);
		cells.setCellValue("产品订货号");
		cells = rows.createCell(1);
		cells.setCellValue("产品名称");
		cells = rows.createCell(2);
		cells.setCellValue("产品描述(中文)");
		cells = rows.createCell(3);
		cells.setCellValue("数量a");
		cells = rows.createCell(4);
		cells.setCellValue("目录单价(￥)b");
		cells = rows.createCell(5);
		cells.setCellValue("目录合价(￥)c=a*b");
		cells = rows.createCell(6);
		cells.setCellValue("折扣率(%)d");
		cells = rows.createCell(7);
		cells.setCellValue("折扣后价格(￥)e=c*(1-d)");
		cells = rows.createCell(8);
		cells.setCellValue("运保及其他费率(%)f");
		cells = rows.createCell(9);
		cells.setCellValue("折扣后现场价(￥)g=e*(1+f)");
		cells = rows.createCell(10);
		cells.setCellValue("安装服务费(￥)h");
		cells = rows.createCell(11);
		cells.setCellValue("第一年保修期费用j1");
		cells = rows.createCell(12);
		cells.setCellValue("第二年保修期费用j2");
		cells = rows.createCell(13);
		cells.setCellValue("第三年保修期费用j3");
		cells = rows.createCell(14);
		cells.setCellValue("j=j1+j2+j3");
		cells = rows.createCell(15);
		cells.setCellValue("合计(x=g+h+j)");
		cells = rows.createCell(16);
		cells.setCellValue("备注");
	}
	
	/**
	 * 
	* 方法用途和描述:设置表单内容
	* @param sheet
	* @param datavolist
	* @author chenliang 新增日期：2012-12-14
	* @since ContractManage
	 */
	private static void setCellValue(List<DataRecordVo> datavolist){
		for (int j = 0; j < datavolist.size(); j++) {
			DataRecordVo vo = datavolist.get(j);
			rows = sheet.createRow(j+1);
			cells = rows.createCell(0);
			cells.setCellValue(vo.getProductOrderNo());
			cells = rows.createCell(1);
			cells.setCellValue(vo.getName());
			cells = rows.createCell(2);
			cells.setCellValue(vo.getProductDesc());
			cells = rows.createCell(3);
			cells.setCellValue(vo.getQuantity());
			cells = rows.createCell(4);
			cells.setCellValue(vo.getUnitPrice());
			cells = rows.createCell(5);
			cells.setCellValue(vo.getTotalprice());
			cells = rows.createCell(6);
			cells.setCellValue(vo.getDiscountRate());
			cells = rows.createCell(7);
			cells.setCellValue(vo.getRebateprice());
			cells = rows.createCell(8);
			cells.setCellValue(vo.getOtherRates());
			cells = rows.createCell(9);
			cells.setCellValue(vo.getRebateafter());
			cells = rows.createCell(10);
			cells.setCellValue(vo.getInstallServiceCharge());
			cells = rows.createCell(11);
			cells.setCellValue(vo.getFirstYear());
			cells = rows.createCell(12);
			cells.setCellValue(vo.getSecondYear());
			cells = rows.createCell(13);
			cells.setCellValue(vo.getThirdYear());
			cells = rows.createCell(14);
			cells.setCellValue((vo.getFirstYear()+vo.getSecondYear()+vo.getThirdYear()));
			cells = rows.createCell(15);
			cells.setCellValue(vo.getTotal());
			cells = rows.createCell(16);
			cells.setCellValue(vo.getRemark());
		}
	}
	
	/**
	 * 
	* 方法用途和描述:上传的合同模板文件
	* @param upfile  需要上传的文件
	* @param upPath 上传的地址
	* @param fileName 文件名
	* @param flat 是否备份
	* @param num 当上传的文件名已经存在，则自动在文件名前追加数字
	* @author chenliang 新增日期：2012-12-19
	* @since ContractManage
	 */
	public static void bakTemplatesDoc(File upfile,String upPath,String fileName,boolean flat,int num){
		try {
			//文件上传
			if(num!=0){
				fileName = num+"_"+fileName;
			}
			File file = new File(upPath,fileName);
			if(!file.exists()){
				FileUtils.copyFile(upfile,file);
			}else{
				bakTemplatesDoc(upfile, upPath, fileName, true,num++);
			}
			if(flat){
				bakFile(upfile, fileName);
			}
		} catch (IOException e) {
			LOGGER.error("bakTemplatesDoc error.",e);
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 备份文件
	* @param upfile  需要备份的文件
	* @param bakFileName  备份文件名
	* @author chenliang 新增日期：2012-12-19
	* @since ContractManage
	 */
	public static void bakFile(File upfile,String bakFileName){
		try {
			//文件备份	
			AppConfig config = AppConfig.getInstance();
			String bakPath = config.getString("bakTemplatesPath");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
			String nowData = sdf.format(new Date());
			bakFileName = "bak_"+bakFileName;
			File fs = new File(bakPath);
			if(!fs.exists()){
				fs.mkdir();
			}
			File bakFile = new File(bakPath, bakFileName);
			FileUtils.copyFile(upfile, bakFile);
		} catch (Exception e) {
			LOGGER.error("bakFile error.",e);
		}
	}
	


	/**
	 * 
	* 方法用途和描述: 删除目录下的文件
	* @param path 文件目录，包含文件名
	* @author chenliang 新增日期：2012-12-19
	* @since ContractManage
	 */
	public static void deleteFile(String path) {
		LOGGER.info("path:"+path);
		if(!BlankUtil.isBlank(path)){
			File file = new File(path);
			if(file.exists() && file.isFile()){
				file.delete();
			}else{
				LOGGER.info("file is not found!");
			}
		}
	}
	
	
	
	/**
	 * 生成价格Excel总表
	 * @param sheetName 表名
	 * @param firstLineString 第一行填入什么就传什么
	 * @param list,每一行写入的数据,具体怎么传看我的demo
	 * @param filePath 绝对路径
	 * @param fileName 文件名
	 * 赵宝东
	 * @return 
	 */
	public static void createPriceListExcel(String sheetName,String firstLineString,List<String> list ,String filePath,String fileName){
		FileOutputStream out=null;
		try{
			LOGGER.info(filePath+File.separator+fileName+" 生成价格总表开始;english( price list gen begining) ");
			File file = new File(filePath);
			if(!file.exists()){
				file.mkdir();
			}
			file = new File(filePath+File.separator+fileName);
			if(file.exists()){
				file.delete();
				file = new File(filePath+File.separator+fileName);
			}
			if(file.createNewFile()){
				out = new FileOutputStream(file);
				workbook = new XSSFWorkbook();
				sheet = workbook.createSheet(sheetName);
				//workbook.setSheetName(0, sheetName,XSSFWorkbook.ENCODING_UTF_16);//sheetname中文写法
				sheet.setColumnWidth( 0,  1400);// 设置列宽
				sheet.setColumnWidth( 1,  8000);// 设置列宽
				sheet.setColumnWidth( 2,  6000);// 设置列宽
				sheet.setColumnWidth( 3,  1400);// 设置列宽
				sheet.setColumnWidth( 4,  1800);// 设置列宽
				sheet.setColumnWidth( 5,  3400);// 设置列宽
				sheet.setColumnWidth( 6,  3400);// 设置列宽
				
				//第一行样式与写入内容代码
				XSSFCellStyle firststyle = workbook.createCellStyle();//样式
				//				firststyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);//边框
				//				firststyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
				//				firststyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
				//				firststyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
				firststyle.setAlignment(XSSFCellStyle.ALIGN_LEFT);
				firststyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
				XSSFRow row0 = sheet.createRow(0);
				row0.setHeight((short)800);
				cells=row0.createCell( 0);
				////cells.setEncoding(XSSFCell.ENCODING_UTF_16); //设置中文中文
				cells.setCellValue(firstLineString);
				cells.setCellStyle(firststyle);
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));

				//第二行样式与写入内容代码
				//先是第2行字体加粗
				XSSFFont font = workbook.createFont();  
				font.setFontHeightInPoints(( short ) 10 ); // 字体高度  
				font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);  
				XSSFCellStyle secondstyle = workbook.createCellStyle();
				secondstyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
				secondstyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
				secondstyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
				secondstyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
				secondstyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);//边框
				secondstyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
				secondstyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
				secondstyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
				secondstyle.setFont(font);  
				row0 = sheet.createRow(1);
				row0.setHeight((short)800);
				cells=row0.createCell( 0);
				////cells.setEncoding(XSSFCell.ENCODING_UTF_16); //设置中文中文
				cells.setCellValue("序号");
				cells.setCellStyle(secondstyle);
				cells=row0.createCell( 1);
				////cells.setEncoding(XSSFCell.ENCODING_UTF_16); //设置中文中文
				cells.setCellValue("项目名称");
				cells.setCellStyle(secondstyle);
				cells=row0.createCell( 2);
				////cells.setEncoding(XSSFCell.ENCODING_UTF_16); //设置中文中文
				cells.setCellValue("名称");
				cells.setCellStyle(secondstyle);
				cells=row0.createCell( 3);
				////cells.setEncoding(XSSFCell.ENCODING_UTF_16); //设置中文中文
				cells.setCellValue("单位");
				cells.setCellStyle(secondstyle);
				cells=row0.createCell( 4);
				////cells.setEncoding(XSSFCell.ENCODING_UTF_16); //设置中文中文
				cells.setCellValue("数量");
				cells.setCellStyle(secondstyle);
				cells=row0.createCell( 5);
				////cells.setEncoding(XSSFCell.ENCODING_UTF_16); //设置中文中文
				cells.setCellValue("总价");
				cells.setCellStyle(secondstyle);
				cells=row0.createCell( 6);
				////cells.setEncoding(XSSFCell.ENCODING_UTF_16); //设置中文中文
				cells.setCellValue("备注");
				cells.setCellStyle(secondstyle);


				//循环插入list参数的内容
				short i=2;//用来建行   
				Long totalPrice=0l;//计算总价用
				XSSFCellStyle contentstyle = workbook.createCellStyle();
				contentstyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
				contentstyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
				contentstyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);//边框
				contentstyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
				contentstyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
				contentstyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
				contentstyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
				if(list!=null&&list.size()>0){
					for(String content:list){
						String[] splitStr=content.split(",");
						row0 = sheet.createRow(i);
						row0.setHeight((short)800);
						cells=row0.createCell( 0);
						////cells.setEncoding(XSSFCell.ENCODING_UTF_16); //设置中文中文
						cells.setCellValue(i-1);
						cells.setCellStyle(contentstyle);
						cells=row0.createCell( 1);
						////cells.setEncoding(XSSFCell.ENCODING_UTF_16); //设置中文中文
						cells.setCellValue(splitStr[0]);
						cells.setCellStyle(contentstyle);
						cells=row0.createCell( 2);
						////cells.setEncoding(XSSFCell.ENCODING_UTF_16); //设置中文中文
						cells.setCellValue(splitStr[1]);
						cells.setCellStyle(contentstyle);
						cells=row0.createCell( 3);
						////cells.setEncoding(XSSFCell.ENCODING_UTF_16); //设置中文中文
						cells.setCellValue(splitStr[2]);
						cells.setCellStyle(contentstyle);
						cells=row0.createCell( 4);
						////cells.setEncoding(XSSFCell.ENCODING_UTF_16); //设置中文中文
						cells.setCellValue(splitStr[3]);
						cells.setCellStyle(contentstyle);
						cells=row0.createCell( 5);
						////cells.setEncoding(XSSFCell.ENCODING_UTF_16); //设置中文中文
						cells.setCellValue(splitStr[4]);
						cells.setCellStyle(contentstyle);
						totalPrice+=Long.parseLong(splitStr[4]);
						cells=row0.createCell( 6);
						////cells.setEncoding(XSSFCell.ENCODING_UTF_16); //设置中文中文
						cells.setCellValue(splitStr[5]);
						cells.setCellStyle(contentstyle);
						i++;
					}
				}

				//写入最尾的总价行数据
				row0 = sheet.createRow(i);
				row0.setHeight((short)800);
				cells=row0.createCell( 0);
				////cells.setEncoding(XSSFCell.ENCODING_UTF_16); //设置中文中文
				cells.setCellValue(i-1);
				cells.setCellStyle(contentstyle);
				cells=row0.createCell( 1);
				////cells.setEncoding(XSSFCell.ENCODING_UTF_16); //设置中文中文
				cells.setCellValue("共计");
				cells.setCellStyle(contentstyle);
				cells=row0.createCell( 2);cells.setCellStyle(contentstyle);
				cells=row0.createCell( 3);cells.setCellStyle(contentstyle);
				cells=row0.createCell( 4);cells.setCellStyle(contentstyle);
				cells=row0.createCell( 6);cells.setCellStyle(contentstyle);
				cells=row0.createCell( 5);
				////cells.setEncoding(XSSFCell.ENCODING_UTF_16); //设置中文中文
				cells.setCellValue(totalPrice);
				cells.setCellStyle(contentstyle);
				sheet.addMergedRegion(new CellRangeAddress(i, i, 1, 4));
			}
			workbook.write(out);
			LOGGER.info(filePath+File.separator+fileName+" 生成价格总表结束;english( price list gen end) ");
		}catch (Exception e){
			LOGGER.debug(e);
			System.out.println(e);
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				System.out.println(e);
				LOGGER.debug(e);	
			}
		}

	}
	/**
	 * 根据输入的文件与输出流对文件进行打包
	 * @param File
	 * @param org.apache.tools.zip.ZipOutputStream
	 */
	public static void zipFile(File inputFile,
			ZipOutputStream ouputStream) {
		try {
			if(inputFile.exists()) {
				/**如果是目录的话这里是不采取操作的，*/
				if (inputFile.isFile()) {
					FileInputStream IN = new FileInputStream(inputFile);
					BufferedInputStream bins = new BufferedInputStream(IN, 512);
					//org.apache.tools.zip.ZipEntry
					ZipEntry entry = new ZipEntry(inputFile.getName());
					
					ouputStream.putNextEntry(entry);
					// 向压缩文件中输出数据   
					int nNumber;
					byte[] buffer = new byte[512];
					while ((nNumber = bins.read(buffer)) != -1) {
						ouputStream.write(buffer, 0, nNumber);
					}
					// 关闭创建的流对象   
					bins.close();
					IN.close();
				} else {
					try {
						File[] files = inputFile.listFiles();
						for (int i = 0; i < files.length; i++) {
							zipFile(files[i], ouputStream);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 把多个文件压缩成一个zip文件
	 * @param list文件绝对路径字符串
	 * @param outFileName压缩文件输出绝对路径
	 */
	public static void zipMoreFile(List<File> list,String outFileName){
		File zipFile = new File(outFileName);
		File file = null;
		try{
			if (!zipFile.exists()){   
				zipFile.createNewFile();   
			}
			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile)); 
			zos.setEncoding("GBK");
			for (int i = 0; i < list.size(); i++) { 
				file = list.get(i); 
				zipFile(file,zos); 	
			} 
		}catch (IOException e) { 
			e.printStackTrace(); 
		} 
	}

	public static void main(String[] args){
		/**
		 * 生成总价excel demo
		 * 赵宝东
		 */
		List<String> list = new ArrayList<String>();
		String haha="呵呵,殷娜,台,900000,8123123121,4件";
		list.add(haha);
		haha="嘻嘻,娜塔亚,台,900001,8123123121,2件";
		list.add(haha);
		haha="哈哈,不朽之王,台,900002,8123123121,4件";
		list.add(haha);
		FilUtil2.createPriceListExcel("价格总表", "合同附件2 价格总表", list, "e://", "价格总表.xlsx");
		
		List<File> filelist = new ArrayList();
		File file1 = new File("C:\\Users\\DongGod\\Desktop\\截图\\打包1.xlsx");
		File file2 = new File("C:\\Users\\DongGod\\Desktop\\截图\\打包2.xlsx");
		File file3 = new File("C:\\Users\\DongGod\\Desktop\\截图\\打包1.docx");
		File file4 = new File("C:\\Users\\DongGod\\Desktop\\截图\\打包2.docx");
		File file5 = new File("C:\\Users\\DongGod\\Desktop\\截图\\打包3.docx");
		File file6 = new File("C:\\Users\\DongGod\\Desktop\\截图\\打包4.docx");
		filelist.add(file1);filelist.add(file2);filelist.add(file3);filelist.add(file4);filelist.add(file5);filelist.add(file6);
//		FilUtil2.zipMoreFile(filelist, "C:\\Users\\DongGod\\Desktop\\截图\\哈哈.rar");

	}
	
}
