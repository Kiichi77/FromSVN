package cn.com.kxcomm.contractmanage.web.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


public class FilUtil2 {
	private static final Logger LOGGER = Logger.getLogger(FileUtil.class);
	private static XSSFWorkbook workbook = null;
	private static XSSFSheet sheet = null;
	private static XSSFRow rows = null;
	private static XSSFCell  cells = null;
	/**
	 * 生成价格Excel总表
	 * @param sheetName 表名
	 * @param firstLineString 第一行填入什么就传什么
	 * @param list<项目名称,名称,单 位,数量,总价,备注>,每一行写入的数据,具体怎么传看我的demo
	 * @param filePath 绝对路径
	 * @param fileName 文件名
	 * 赵宝东
	 * @return 
	 */
	public static void createPriceListExcel(String sheetName,String firstLineString,List<String> list ,String filePath,String fileName){
		FileOutputStream out=null;
		try{
			LOGGER.info(filePath+fileName+" 生成价格总表开始;english( price list gen begining) ");
			File file = new File(filePath);
			if(!file.exists()){
				file.mkdir();
			}
			file = new File(filePath+fileName);
			if(file.exists()){
				file.delete();
				file = new File(filePath+fileName);
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
				Double totalPrice=0.0;//计算总价用
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
						totalPrice+=Double.parseDouble(splitStr[4]);
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
			LOGGER.info(filePath+fileName+" 生成价格总表结束;english( price list gen end) ");
		}catch (Exception e){
			LOGGER.debug(e);
			e.printStackTrace();
		}finally{
			try {
				if(out!=null){
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				LOGGER.debug(e);	
			}
		}

	}
	/**
	 * 根据输入的文件与输出流对文件进行打包
	 * @param File
	 * @param org.apache.tools.zip.ZipOutputStream
	 */
	public static void zipFile(Map map,
			ZipOutputStream ouputStream) {
		FileInputStream in = null;
		BufferedInputStream bins = null;
		try {
			File inputFile = (File) map.get("file"); 
			if(inputFile.exists()) {
				/**如果是目录的话这里是不采取操作的，*/
				if (inputFile.isFile()) {
					in = new FileInputStream(inputFile);
					bins = new BufferedInputStream(in, 512);
					//org.apache.tools.zip.ZipEntry
					ZipEntry entry = new ZipEntry((String) map.get("fileName"));
					System.out.println((String) map.get("fileName"));
					ouputStream.putNextEntry(entry);
					// 向压缩文件中输出数据   
					int nNumber;
					byte[] buffer = new byte[512];
					while ((nNumber = bins.read(buffer)) != -1) {
						ouputStream.write(buffer, 0, nNumber);
					}
				} else {
					try {
						File[] files = inputFile.listFiles();
						Map map2 = null;
						for (int i = 0; i < files.length; i++) {
							map2 = new HashMap();
							map2.put("file",files[i]);
							map2.put("fileName",(String) map.get("fileName"));
							zipFile(map2, ouputStream);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				bins.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * 把多个文件压缩成一个zip文件
	 * @param list文件绝对路径字符串
	 * @param outFileName压缩文件输出绝对路径
	 */
	public static void zipMoreFile(List<Map> list,String outFileName){
		File zipFile = new File(outFileName);
		ZipOutputStream zos = null;
		Map map = null;
		try{
			if (!zipFile.exists()){   
				zipFile.createNewFile();   
			}
			zos = new ZipOutputStream(new FileOutputStream(zipFile)); 
			zos.setEncoding("GBK");
			for (int i = 0; i < list.size(); i++) { 
				map = list.get(i); 
				zipFile(map,zos); 	
			} 
		}catch (IOException e) { 
			e.printStackTrace(); 
		}
		finally {
			try {
				zos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @param fileName 获取后缀的文件名
	 * @param reName 加后缀的文件名
	 * @return
	 */
	public static String getContractRenameStr(String fileName,String reName){
		int docIndex = fileName.indexOf(".");
		String houzhui = fileName.substring(docIndex,fileName.length());
		return reName;
	}

	public static void main(String[] args){
		/**
		 * 生成总价excel demo
		 * 赵宝东
		 */
		//		List<String> list = new ArrayList<String>();
		//		String haha="呵呵,殷娜,台,900000,8123123121,4件";
		//		list.add(haha);
		//		haha="嘻嘻,娜塔亚,台,900001,8123123121,2件";
		//		list.add(haha);
		//		haha="哈哈,不朽之王,台,900002,8123123121,4件";
		//		list.add(haha);
		//		FilUtil2.createPriceListExcel("价格总表", "合同附件2 价格总表", list, "e://", "价格总表.xlsx");

//		List<Map> filelist = new ArrayList();
//		File file1 = new File("E:\\kangxun\\Eclipse使用MAVEN入门.doc");
//		Map<String,Object> map  = new HashMap<String, Object>();
//		map.put("file", file1);
//		map.put("fileName", "正文.doc");
//		filelist.add(map);
//		File file1_1 = new File("E:\\kangxun\\康讯报销单（2013-03-05）.xlsx");
//		map = new HashMap<String, Object>();
//		map.put("file", file1_1);
//		map.put("fileName", "附件1.xlsx");
//		filelist.add(map);
//		File file2 = new File("E:\\kangxun\\Chrysanthemum.jpg");
//		map = new HashMap<String, Object>();
//		map.put("file", file2);
//		map.put("fileName", "合同附件12 卖方营业执照复印件.jpg");
//		filelist.add(map);//filelist.add(file3);filelist.add(file4);filelist.add(file5);filelist.add(file6);
//		FilUtil2.zipMoreFile(filelist, "E:\\哈哈.rar");

		
		FilUtil2.getContractRenameStr("nilaomu.docx","xixi");
	}

}

