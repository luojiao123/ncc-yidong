package com.excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList; 
import java.util.List; 

import com.entity.help.NodeKey;

import jxl.BooleanCell;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.LabelCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * 读取Excel
 * @author Administrator
 *
 */
public class ImportExcel {

	public List<NodeKey> readExcel(InputStream inputStream, int sheetId)
			throws BiffException, IOException {
		Workbook book = Workbook.getWorkbook(inputStream);
		Sheet sheet = book.getSheet(sheetId);
		List<NodeKey> list = new ArrayList<NodeKey>();
		for (int j = 0; j < sheet.getRows(); j++) {
			NodeKey nk = new NodeKey(); 
			Cell[] cell = sheet.getRow(j); 
			// 读取当前单元格的值
			if (cell != null && cell.length > 0) {
				// 对每个单元格进行循环
				for (int k = 0; k < cell.length; k++) {
					// 记录列号
					int columeNumber = k + 1;
					
					// 读取当前单元格的值  
					nk.setProvince(cell[0].getContents());
					nk.setCity(cell[1].getContents());
					nk.setCodeName(cell[2].getContents());
					nk.setCode(cell[3].getContents());
					nk.setTradezonename_rsc(cell[4].getContents());
					System.out.println(cell[k].getContents());
				}
				list.add(nk);
			} 
		}
		System.out.println("list.size()="+list.size());
		book.close();
		return list;
	}

	public Object getExcelDate(Cell cell) {
		if (cell.getType() == CellType.NUMBER) {
			NumberCell number = (NumberCell) cell;
			return number.getValue();
		} else if (cell.getType() == CellType.LABEL) {
			LabelCell label = (LabelCell) cell;
			return label.getString();
		} else if (cell.getType() == CellType.BOOLEAN) {
			BooleanCell bool = (BooleanCell) cell;
			return bool.getValue();
		} else if (cell.getType() == CellType.DATE) {
			DateCell d = (DateCell) cell;
			return d.getDate();
		} else {
			return cell.getContents();
		}
	}

	public static void main(String[] args) throws BiffException, IOException {
		ImportExcel read = new ImportExcel();
		// read.readExcel("f:\\lxbllist.xls", 0);

	}
}
