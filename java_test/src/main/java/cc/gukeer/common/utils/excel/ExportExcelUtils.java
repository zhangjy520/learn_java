package cc.gukeer.common.utils.excel;

import cc.gukeer.common.ArticleCount;
import cc.gukeer.common.utils.excel.annotation.ExcelField;
import cc.gukeer.smartBoard.modelView.importExport.ImportRing;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ExportExcelUtils {
    /** 
     * @Title: exportExcel 
     * @Description: 导出Excel的方法 
     * @author: evan @ 2014-01-09  
     * @param workbook  
     * @param sheetNum (sheet的位置，0表示第一个表格中的第一个sheet) 
     * @param sheetTitle  （sheet的名称） 
     * @param headers    （表格的标题）
     * @param result   （表格的数据）
     * @param out  （输出流） 
     * @throws Exception 
     */  
    public void exportExcel(HSSFWorkbook workbook, int sheetNum,
            String sheetTitle, List<String> headers, List<List<String>> result,
            OutputStream out) throws Exception {  
        // 生成一个表格  
        HSSFSheet sheet = workbook.createSheet(sheetTitle);
        // 设置表格默认列宽度为20个字节  
        sheet.setDefaultColumnWidth((short) 20);  
        // 生成一个样式  
        HSSFCellStyle style = workbook.createCellStyle();  
        // 设置这些样式  
        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);  
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        // 生成一个字体  
        HSSFFont font = workbook.createFont();  
        font.setColor(HSSFColor.BLACK.index);  
        font.setFontHeightInPoints((short) 12);  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
        // 把字体应用到当前的样式  
        style.setFont(font);  
  
        // 指定当单元格内容显示不下时自动换行  
        style.setWrapText(true);  
  
        // 产生表格标题行  
        HSSFRow row = sheet.createRow(0);  
        for (int i = 0; i < headers.size(); i++) {
            HSSFCell cell = row.createCell((short) i);  

            cell.setCellStyle(style);  
            HSSFRichTextString text = new HSSFRichTextString(headers.get(i));
            cell.setCellValue(text.toString());  
        }  
        // 遍历集合数据，产生数据行  
        if (result != null) {
            int index = 1;  
            for (List<String> m : result) {  
                row = sheet.createRow(index);  
                int cellIndex = 0;  
                for (String str : m) {  
                    HSSFCell cell = row.createCell((short) cellIndex);  
                    cell.setCellValue(str.toString());  
                    cellIndex++;  
                }  
                index++;  
            }  
        }
    }


    public static List getHeaders(Class<?> cls, int type, String anno, int... groups) {
        List<Object[]> annotationList = Lists.newArrayList();
        // Get annotation field
        Field[] fs = cls.getDeclaredFields();
        for (Field f : fs) {
            ExcelField ef = f.getAnnotation(ExcelField.class);
            if (ef != null && (ef.type() == 0 || ef.type() == type)) {
                if (groups != null && groups.length > 0) {
                    boolean inGroup = false;
                    for (int g : groups) {
                        if (inGroup) {
                            break;
                        }
                        for (int efg : ef.groups()) {
                            if (g == efg) {
                                inGroup = true;
                                annotationList.add(new Object[]{ef, f});
                                break;
                            }
                        }
                    }
                } else {
                    annotationList.add(new Object[]{ef, f});
                }
            }
        }
        // Get annotation method
        Method[] ms = cls.getDeclaredMethods();
        for (Method m : ms) {
            ExcelField ef = m.getAnnotation(ExcelField.class);
            if (ef != null && (ef.type() == 0 || ef.type() == type)) {
                if (groups != null && groups.length > 0) {
                    boolean inGroup = false;
                    for (int g : groups) {
                        if (inGroup) {
                            break;
                        }
                        for (int efg : ef.groups()) {
                            if (g == efg) {
                                inGroup = true;
                                annotationList.add(new Object[]{ef, m});
                                break;
                            }
                        }
                    }
                } else {
                    annotationList.add(new Object[]{ef, m});
                }
            }
        }
        // Field sorting
        Collections.sort(annotationList, new Comparator<Object[]>() {
            public int compare(Object[] o1, Object[] o2) {
                return new Integer(((ExcelField) o1[0]).sort()).compareTo(
                        new Integer(((ExcelField) o2[0]).sort()));
            }

            ;
        });
        // Initialize
        List<String> headerList = Lists.newArrayList();
        List<Integer> reds=Lists.newArrayList();
        for (Object[] os : annotationList) {
            String t = ((ExcelField) os[0]).title();
            int red = ((ExcelField) os[0]).isnull();
            // 如果是导出，则去掉注释
            if (type == 1) {
                String[] ss = StringUtils.split(t, "**", 2);
                if (ss.length == 2) {
                    t = ss[0];
                }
            }
            reds.add(red);
            headerList.add(t);
        }
        return headerList;
    }


    public static void main(String[] args) {
        List aa = getHeaders(ArticleCount.class,1,"");
        List aa1 = getHeaders(ImportRing.class,1,"");

        List<ArticleCount> aaList = new ArrayList<>();
        ArticleCount a1 = new ArticleCount();
        a1.setArticle("111");
        a1.setBigArticle("bigAr");

        ArticleCount a2 = new ArticleCount();
        a1.setArticle("222");
        a1.setBigArticle("bigAr");

        aaList.add(a1);aaList.add(a2);

        List<ImportRing> aaList2 = new ArrayList<>();
        ImportRing a12 = new ImportRing();
        a12.setRingMac("mac12");
        a12.setRingNumber(12);

        ImportRing a22 = new ImportRing();
        a12.setRingMac("mac2");
        a12.setRingNumber(12);

        aaList.add(a1);aaList.add(a2);



        try {
            OutputStream out = new FileOutputStream("E:\\result.xlsx");
            List<List<String>> data = new ArrayList<List<String>>();
            for (int i = 1; i < 5; i++) {
                List rowData = new ArrayList();
                rowData.add(String.valueOf(i));
                rowData.add("东霖柏鸿");
                data.add(rowData);
            }
            ExportExcelUtils eeu = new ExportExcelUtils();
            HSSFWorkbook workbook = new HSSFWorkbook();
            eeu.exportExcel(workbook, 0, "上海", aa, data, out);
            eeu.exportExcel(workbook, 1, "深圳", aa1, data, out);
            //原理就是将所有的数据一起写入，然后再关闭输入流。
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}  