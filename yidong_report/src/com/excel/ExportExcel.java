package com.excel;

import java.io.File; 
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
 
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExportExcel {

	public static void doUpdate(String filePath,
			
		ArrayList<ArrayList<String>> cloumnList) {
		try {
			// 获得原Excel文件
			Workbook wb = Workbook.getWorkbook(new File(filePath));
			// 打开一个文件的副本，并且指定数据写回到原文件
			WritableWorkbook wwb = Workbook.createWorkbook(new File(filePath),
					wb);
			// 对第一个工作簿的A1 更新
			WritableSheet sheet = wwb.getSheet(0);

			int tempI = 0;
			int tempJ = sheet.getRows();
			for (ArrayList<String> array : cloumnList) {
				tempI = 0;
				for (String value : array) {
					sheet.addCell(new Label(tempI, tempJ, value));
					tempI++;
				}
				tempJ++;
			}

			// 关闭工作薄对象
			wwb.write();
			wwb.close();
			wb.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * 导出为Excel文件操作
	 * 
	 */
	private void doExcelExport(String filePath, ArrayList<String> cloumnList,
			ArrayList<String> dataList) {
		File tmpFile = new File(filePath);
		FileOutputStream fileOut = null;
		try {
			// 准备excel
			WritableWorkbook workbook = null;
			WritableSheet sheet = null;

			fileOut = new FileOutputStream(tmpFile);
			workbook = Workbook.createWorkbook(fileOut);

			// 创建 sheet
			sheet = workbook.createSheet("sheet1", 0);

			// 创建标题单元格
			for (int i = 0; i < cloumnList.size(); i++) {
				sheet.addCell(new Label(i, 0, cloumnList.get(i)));
			}
			// 设置对应的值
			for (int j = 0; j < dataList.size(); j++) {
				sheet.addCell(new Label(j, 1, dataList.get(j)));
			}
			workbook.write();
			workbook.close();
		} catch (Exception e) {
		} finally {
			// 处理完毕，关闭文件流
			try {
				if (fileOut != null) {
					fileOut.flush();
					fileOut.close();
				}
			} catch (IOException e) {
			}
		}
	}

	/**
	 * 将数据导出到excel里面
	 * 
	 * @param tmpFile
	 *            要导出到的excel文件
	 * @param cloumnList
	 *            写入excel文件的值
	 */
	public static void doExcelExport(String filePath,
			ArrayList<ArrayList<String>> cloumnList) {
		FileOutputStream fileOut = null;
		File tmpFile = new File(filePath);
		try {
			// 准备excel
			WritableWorkbook workbook = null;
			WritableSheet sheet = null;

			fileOut = new FileOutputStream(tmpFile);
			workbook = Workbook.createWorkbook(fileOut);

			// 创建 sheet
			sheet = workbook.createSheet("sheet1", 1);

			int tempI = 0;
			int tempJ = 0;
			for (ArrayList<String> array : cloumnList) {
				tempI = 0;
				for (String value : array) {
					sheet.addCell(new Label(tempI, tempJ, value));
					tempI++;
				}
				tempJ++;
			}

			workbook.write();
			workbook.close();
		} catch (Exception e) {
		} finally {
			// 处理完毕，关闭文件流
			try {
				if (fileOut != null) {
					fileOut.flush();
					fileOut.close();
				}
			} catch (IOException e) {
			}
		}
	}

	/**
	 * 将数据导出到excel里面
	 * 
	 * @param tmpFile
	 *            要导出到的excel文件
	 * @param cloumnList
	 *            写入excel文件的值
	 */
	public static void doExcelExport(OutputStream os,
			ArrayList<ArrayList<String>> cloumnList) {
		 
		try {
			// 准备excel
			WritableWorkbook workbook = null;
			WritableSheet sheet = null;
 
			workbook = Workbook.createWorkbook(os);

			// 创建 sheet
			sheet = workbook.createSheet("sheet1", 1);

			int tempI = 0;
			int tempJ = 0;
			for (ArrayList<String> array : cloumnList) {
				tempI = 0;
				for (String value : array) {
					sheet.addCell(new Label(tempI, tempJ, value));
					tempI++;
				}
				tempJ++;
			}

			workbook.write();
			workbook.close();
		} catch (Exception e) {
		} 
	}

	/**
	 * 导出到excel
	 * 
	 * @param tmpFile
	 *            导出到excel的绝对路径（包括excel的文件名）
	 * @param list
	 *            要导出的内容，list里面放对象
	 * @param methodName
	 *            要导出list里面对象的属性
	 */
	public static void doExcelExport(String filePath, List list,
			String... methodName) {
		doExcelExport(filePath, list, null, true, methodName);
	}

	public static void doExcelExport(String filePath, String sheetName,
			List list, String... methodName) {
		doExcelExport(filePath, list, sheetName, true, methodName);
	}

	public static void doExcelExport(String filePath, List list,
			Boolean isAddGet, String... methodName) {
		doExcelExport(filePath, list, null, isAddGet, methodName);
	}

	/**
	 * 导出到excel 韩晓聪
	 * 
	 * @param tmpFile
	 *            导出到excel的绝对路径（包括excel的文件名）
	 * @param list
	 *            要导出的内容，list里面放对象
	 * @param sheetName
	 *            单元表的名称
	 * @param isAddGet
	 *            如果方法名前面没有get是否添加
	 * @param methodName
	 *            要导出list里面对象的属性
	 */
	public static void doExcelExport(String filePath, List list,
			String sheetName, Boolean isAddGet, String... methodName) {
		System.out.println("开始导出excel");
		File tmpFile = new File(filePath);
		FileOutputStream fileOut = null;
		try {
			// 准备excel
			WritableWorkbook workbook = null;
			WritableSheet sheet = null;

			fileOut = new FileOutputStream(tmpFile, true);
			// 以下测试，
			// FileInputStream fileIn = new FileInputStream(tmpFile);
			// Workbook wb = Workbook.getWorkbook(fileIn);
			// Sheet sh = wb.getSheet(0);
			// 测试完毕、
			workbook = Workbook.createWorkbook(fileOut);

			// 创建 sheet
			if (null == sheetName || sheetName.equals("")) {
				sheet = workbook.createSheet("sheet1", 0);
			} else {
				sheet = workbook.createSheet(sheetName, 0);
			}

			int tempI = 0;
			int tempJ = 0;

			for (Object instance : list) {
				Class c = instance.getClass();
				for (String mName : methodName) {

					Method method = isAddGet ? c.getMethod(mName
							.startsWith("get") ? mName : "get" + mName) : c
							.getMethod(mName);
					sheet.addCell(new Label(tempI, tempJ, method.invoke(
							instance).toString()));
					tempI++;
				}
				tempI = 0;
				tempJ++;
			}

			workbook.write();
			workbook.close();
		} catch (Exception e) {
			System.out.println("导出excel出错");
			e.printStackTrace();
		} finally {
			// 处理完毕，关闭文件流
			try {
				if (fileOut != null) {
					fileOut.flush();
					fileOut.close();
				}
			} catch (IOException e) {
				System.out.println("关闭文件输出流出错");
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// ArrayList<String> str1 = new ArrayList<String>();
		// str1.add("a1");
		// str1.add("a2");
		// str1.add("a3");
		// str1.add("a4");
		// str1.add("a5");
		// ArrayList<String> str2 = new ArrayList<String>();
		// str2.add("b1");
		// str2.add("b2");
		// str2.add("b3");
		// str2.add("b4");
		// str2.add("b5");
		// ArrayList<ArrayList<String>> array = new
		// ArrayList<ArrayList<String>>();
		// array.add(str1);
		// array.add(str2);
		//
		// File file = new File("d:\\temp.xls");
		//
		// doExcelExport(file, array);

		ArrayList<ArrayList<String>> all = new ArrayList<ArrayList<String>>();
		ArrayList<String> title = new ArrayList<String>();
		title.add("主机名称");
		title.add("IP");
		title.add("");
		title.add("");
		title.add("");
		all.add(title);
		doUpdate("d:\\temp.xls", all);
	}
}
