package cc.gukeer.support.analysis.service;

import cc.gukeer.common.utils.excel.ImportExcel;
import cc.gukeer.support.analysis.entity.LeleInfoPart;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lx on 2016/12/6.
 */
public  class  AnalysisExcel {

    public static List<LeleInfoPart> GetPojo(File file) throws IOException, InvalidFormatException {

        List<LeleInfoPart> leleList = new ArrayList<>();
        String sectiontemp = "";
        ImportExcel importExcel1 = new ImportExcel(file,1);
        int sheetsbum = importExcel1.getwb().getNumberOfSheets();
        for (int i = 1; i < sheetsbum; i++) {
            ImportExcel importExcel = new ImportExcel(file,1,i);
            Sheet sheet = importExcel.getsheet(i);
            for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                LeleInfoPart leleClass = new LeleInfoPart();
                Row row = importExcel.getRow(j);
                if (null == row) {
                    continue;
                }
                Cell cell = row.getCell(0);
                if (null == cell) {
                    continue;
                }
                Cell cell1 = row.getCell(3);
                if (null == cell1) {
                    continue;
                }
                Cell cell6 = row.getCell(6);
                if (null == cell6) {
                    continue;
                }
                if (null == sheet){
                    continue;
                }
                String term = cell.getStringCellValue();
                System.out.print("@@@行数是"+sheet.getPhysicalNumberOfRows());
                System.out.print("\n");
                System.out.print("@@@页数是"+sheetsbum);
                System.out.print("\n");
                System.out.print("@@@j是"+j);
                System.out.print("\n");
                String section = row.getCell(4).getStringCellValue();
                System.out.print("@@@"+row.getCell(4));
                System.out.print("\n");
                String chip = row.getCell(6).getStringCellValue();
                System.out.print("@@@"+row.getCell(6));
                System.out.print("\n");
                if (StringUtils.isNotBlank(section)) {
                    sectiontemp = section;
                }
                leleClass.setTerm(term);
                leleClass.setSection(sectiontemp);
                leleClass.setChip(chip);
                leleList.add(leleClass);
                System.out.print("#" + term + "#" + "#" + sectiontemp + "#" + "#" + chip + "#");
                System.out.print("\n");
            }
        }
        return leleList;
    }

}
