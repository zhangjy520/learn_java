package cc.gukeer.common.utils;

import cc.gukeer.common.config.KVEntity;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by conn on 2016/9/9.
 */
public class ConstantUtil {
    private static final DateFormat FORMATTER = new SimpleDateFormat("yyyyMMdd");
    // 年级
    public static List<KVEntity> njList = new ArrayList<KVEntity>();
    // 有效证件类型
    public static List<KVEntity> cardTypList = new ArrayList<KVEntity>();
    // 在校状态
    public static List<KVEntity> zxztList = new ArrayList<KVEntity>();
    // 招生类别
    public static List<KVEntity> zslbList = new ArrayList<KVEntity>();
    // 学生类别
    public static List<KVEntity> xslbList = new ArrayList<KVEntity>();
    // 班级类型
    public static List<KVEntity> bjlxList = new ArrayList<KVEntity>();
    // 政治面貌
    public static List<KVEntity> zzmmList = new ArrayList<KVEntity>();
    // 户口性质
    public static List<KVEntity> hkxzList = new ArrayList<KVEntity>();
    //性别
    public static List<KVEntity> xbList = new ArrayList<KVEntity>();
    //学段
    public static List<KVEntity> xdList = new ArrayList<KVEntity>();
    //是或否
    public static List<KVEntity> yornList = new ArrayList<KVEntity>();
    //来源地区
    public static List<KVEntity> lydqList = new ArrayList<KVEntity>();

    public static Map<String, Object> map = new HashMap<String, Object>();

    static {

        KVEntity entry = new KVEntity("1", "一年级");
        njList.add(entry);
        entry = new KVEntity("2", "二年级");
        njList.add(entry);
        entry = new KVEntity("3", "三年级");
        njList.add(entry);
        entry = new KVEntity("4", "四年级");
        njList.add(entry);
        entry = new KVEntity("5", "五年级");
        njList.add(entry);
        entry = new KVEntity("6", "六年级");
        njList.add(entry);
        entry = new KVEntity("7", "七年级");
        njList.add(entry);
        entry = new KVEntity("8", "八年级");
        njList.add(entry);
        entry = new KVEntity("9", "九年级");
        njList.add(entry);

        entry = new KVEntity("1", "身份证");
        cardTypList.add(entry);
        entry = new KVEntity("2", "护照");
        cardTypList.add(entry);

        entry = new KVEntity("1", "男");
        xbList.add(entry);
        entry = new KVEntity("2", "女");
        xbList.add(entry);

        entry = new KVEntity("0", "在籍在校");
        zxztList.add(entry);
        entry = new KVEntity("1", "在籍离校");
        zxztList.add(entry);
        entry = new KVEntity("2", "在校不在籍");
        zxztList.add(entry);
        entry = new KVEntity("3", "不在籍不在校");
        zxztList.add(entry);

        entry = new KVEntity("0", "普通入学");
        zslbList.add(entry);
        entry = new KVEntity("1", "民族班");
        zslbList.add(entry);
        entry = new KVEntity("2", "体育特招");
        zslbList.add(entry);
        entry = new KVEntity("3", "外校转入");
        zslbList.add(entry);
        entry = new KVEntity("4", "恢复入学资格");
        zslbList.add(entry);
        entry = new KVEntity("5", "外籍");
        zslbList.add(entry);
        entry = new KVEntity("6", "其他");
        zslbList.add(entry);

        entry = new KVEntity("0", "普通学生");
        xslbList.add(entry);
        entry = new KVEntity("1", "随班就读生");
        xslbList.add(entry);
        entry = new KVEntity("2", "残障学生");
        xslbList.add(entry);
        entry = new KVEntity("3", "其他");
        xslbList.add(entry);

        entry = new KVEntity("0", "普通班级");
        bjlxList.add(entry);
        entry = new KVEntity("1", "民族班");
        bjlxList.add(entry);
        entry = new KVEntity("2", "体育班级");
        bjlxList.add(entry);
        entry = new KVEntity("3", "外语班级");
        bjlxList.add(entry);
        entry = new KVEntity("4", "其他特殊班");
        bjlxList.add(entry);

        entry = new KVEntity("0", "群众");
        zzmmList.add(entry);
        entry = new KVEntity("1", "团员");
        zzmmList.add(entry);
        entry = new KVEntity("2", "党员");
        zzmmList.add(entry);

        entry = new KVEntity("0", "农村");
        hkxzList.add(entry);
        entry = new KVEntity("1", "城镇");
        hkxzList.add(entry);
        entry = new KVEntity("2", "其他");
        hkxzList.add(entry);

        entry = new KVEntity("1", "小学");
        xdList.add(entry);
        entry = new KVEntity("2", "初中");
        xdList.add(entry);
        entry = new KVEntity("3", "高中");
        xdList.add(entry);

        entry = new KVEntity("1", "是");
        yornList.add(entry);
        entry = new KVEntity("2", "否");
        yornList.add(entry);

        entry = new KVEntity("0", "区县内");
        lydqList.add(entry);
        entry = new KVEntity("1", "省市内");
        lydqList.add(entry);
        entry = new KVEntity("2", "省市外");
        lydqList.add(entry);

        map.put("nj", njList);
        map.put("cardTyp", cardTypList);
        map.put("zxzt", zxztList);
        map.put("zslb", zslbList);
        map.put("xslb", xslbList);
        map.put("bjlx", bjlxList);
        map.put("zzmm", zzmmList);
        map.put("hkxz", hkxzList);
        map.put("xb", xbList);
        map.put("xd", xdList);
        map.put("yorn", yornList);
        map.put("lydq", lydqList);
    }

    public static String getGradeNj(int nj) {
        String val = "";
        for (KVEntity entity : njList) {
            if (entity.getKey().equals(String.valueOf(nj))) {
                val = entity.getValue();
                break;
            }
        }
        return val;
    }


    /*
    * nj 年级
    * cardTyp 证件类型
    * zxzt 在校状态
    * zslb 招生类别
    * xslb 学生类别
    * bjlx 班级类别
    * zzmm 政治面貌
    * hkxz 户口性质
    * xb 性别
    * xd 学段
    * yorn 是否判断
    * lydq 来源地区
    * */
    public static String getValueByKeyAndFlag(int key, String which) {
        String val = "";
        List<KVEntity> kvEntityList = (List<KVEntity>) map.get(which);
        for (KVEntity entity : kvEntityList) {
            if (entity.getKey().equals(String.valueOf(key))) {
                val = entity.getValue();
                break;
            }
        }
        return val;
    }

    public static Integer getKeyByValueAndFlag(String value, String which) {
        int val = 0;
        List<KVEntity> kvEntityList = (List<KVEntity>) map.get(which);
        for (KVEntity entity : kvEntityList) {
            if (entity.getValue().equals(value)) {
                val = Integer.parseInt(entity.getKey());
                break;
            }
        }
        return val;
    }

    //自动翻译  初一===初中一年级
    public static String translate(String origin) {
        String[] arry1 = {"初一", "初二", "初三", "小学一年级", "小学二年级", "小学三年级", "小学四年级", "小学五年级", "小学六年级"};
        String[] arry2 = {"初中1", "初中2", "初中3", "小学1", "小学2", "小学3", "小学4", "小学5", "小学6"};
        String[] arry3 = {"初中一年级", "初中二年级", "初中三年级", "小学一年级", "小学二年级", "小学三年级", "小学四年级", "小学五年级", "小学六年级"};
        int index1 = Arrays.asList(arry1).indexOf(origin);
        int index2 = Arrays.asList(arry2).indexOf(origin);
        int index3 = Arrays.asList(arry3).indexOf(origin);
        if (index1 >= 0) {
            return arry2[index1];
        }
        if (index2 >= 0) {
            return arry1[index2];
        }
        if (index3 >= 0) {
            return arry1[index3];
        } else {
            return null;
        }

    }

    public static String translateSeries(String str, Integer seq) {
        String[] array1 = {"优秀", "良好", "及格", "不及格"};
        String[] array2 = {"正常", "超重", "肥胖", "低体重"};

        List<String> list1 = Arrays.asList(array1);
        List<String> list2 = Arrays.asList(array2);

        if (list1.contains(str)) {
            return array1[seq - 1];
        }
        if (list2.contains(str)) {
            return array2[seq - 1];
        } else {
            return null;
        }
    }

    public static Map translateNj(String origin) {
        String[] arry1 = {"初一", "初二", "初三", "小学一年级", "小学二年级", "小学三年级", "小学四年级", "小学五年级", "小学六年级"};
        String[] arry2 = {"初中1", "初中2", "初中3", "小学1", "小学2", "小学3", "小学4", "小学5", "小学6"};
        int index1 = Arrays.asList(arry1).indexOf(origin);
        int index2 = Arrays.asList(arry2).indexOf(origin);

        String param = "";

        if (index1 >= 0) {
            param = arry2[index1];
            String s = param.replaceAll("\\d+", "");
            String n = param.replaceAll("\\D+", "");
            Map res = new HashMap();
            res.put("xdName", s);
            res.put("nj", n);
            return res;
        }
        if (index2 >= 0) {
            param = arry1[index2];
            Map res = new HashMap();
            res.put("njName", param);
            return res;
        }
        return null;
    }

    //单位换算   111.2 to 1′51″
    public static String unitTranslate(String val) {
        int douIndex = val.indexOf(".");
        int xs = 0;//小数位
        if (douIndex >= 0) {
            int xiaoShu = NumberConvertUtil.convertS2I(val.substring(douIndex + 1, val.length()).substring(0, 1));
            if (xiaoShu >= 5) {
                xs = 1;
            }
        }

        Double second = NumberConvertUtil.convertS2D(val);
        if (second.compareTo(60.00) >= 0) {
            int h = 0;
            int d = 0;
            int s = 0;
            int temp = (int) (second % 3600);
            if (second > 3600) {
                h = (int) (second / 3600);
                if (temp != 0) {
                    if (temp > 60) {
                        d = temp / 60;
                        if (temp % 60 != 0) {
                            s = temp % 60;
                        }
                    } else {
                        s = temp;
                    }
                }
            } else {
                d = (int) (second / 60);
                if (second % 60 != 0) {
                    s = (int) (second % 60);
                }
            }

            return d + "′" + (s + xs) + "″";
        } else {
//            return val;
            if (douIndex>=0){
                return   "0′" + val.substring(0,douIndex) + "″";
            }else {
                return "0′" + val + "″";
            }
        }

    }

    //关联单位和换算
    public static String translateUnit(String unit, String num) {
        if (unit.indexOf("分") >= 0) {
            return translateSecondToMi(num);
        } else {
            return num;
        }
    }

    //单位换算   1′51″to 111.2
    public static String translateSecondToMi(String str) {
        int indexS = str.indexOf("′");
        int indexSd = str.indexOf("'");

        indexS = indexS < indexSd ? indexSd : indexS;

        int indexM = str.indexOf("″");
        int indexMd = str.indexOf("\"");
        int indexMd1 = str.indexOf("''");

        indexM = indexM < indexMd ? indexMd : indexM;
        indexM = indexM < indexMd1 ? indexMd1 : indexM;

        if (indexS >= 0 && indexM >= 0) {
            Double aa = NumberConvertUtil.convertS2D(str.substring(0, indexS)) * 60.00 + NumberConvertUtil.convertS2D(str.substring(indexS + 1, indexM));
            return String.valueOf(aa);
        } else {
            if (str.indexOf(":") >= 0) {
                String[] num = str.split(":");
                return String.valueOf(NumberConvertUtil.convertS2D(num[0]) * 60.00 + NumberConvertUtil.convertS2D(num[1]));
            }
            return str;
        }
    }

    public static String getPrimaryKey() {

        String uuid = UUID.randomUUID().toString();
        // StringUtils 引用 org.apache.commons.lang3.StringUtils
        String key = StringUtils.replace(uuid, "-", "");

        return key;
    }

    public static String formatStr(Object str) {
        if (null == str || StringUtils.isBlank(str.toString())) {
            return null;
        } else {
            return str.toString();
        }
    }

    public static String getCurrentTime() {
        DateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return formatter.format(calendar.getTime());
    }

    public static Boolean containsString(List<String> list, String string) {
        if (list == null || string == null)
            return false;
        return list.contains(string);
    }

    public static Integer getHour(Integer time) {
        if (time == null)
            return 0;
        else {
            time = time / 60;
            if (time > 24) time = time - 24;
            return time;
        }
    }

    public static Integer getMin(Integer time) {
        if (time == null)
            return 0;
        else {
            return time % 60;
        }
    }

    public static String getF(String str) {
        if (str == null || str == "")
            return "0.0";
        try {
            Double f = Double.valueOf(str);
            NumberFormat nt = NumberFormat.getNumberInstance();
            nt.setMaximumFractionDigits(1);
            return nt.format(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0.0";
    }

    public static String getAsleepTime(Integer asleep) {
        if (asleep == null) return " ";
        float temp = (float) asleep / 60;
        String time = String.valueOf(temp);
        int flag = time.indexOf(".");
        String hour = "00", min = "00";
        if (flag > 0) {
            hour = time.substring(0, flag);
            Integer h = Integer.valueOf(hour);
            if (h > 24) h = h - 24;
            hour = h.toString();
            String _min = time.substring(flag);
            Float f = Float.valueOf("0" + _min);
            f = f * 60;
            Double rint = Math.rint(f);
            min = String.valueOf(rint.intValue());
            if (min.length() == 1) min = "0" + min;
        }
        return hour + ":" + min;
    }

    public static String formatTime(String time) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = format.parse(time);
            format = new SimpleDateFormat("HH:mm");
            return format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String timeDiffer(String time, String time2) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = format.parse(time);
            Date date2 = format.parse(time2);
            Long differ = date.getTime() - date2.getTime();
            differ = Math.abs(differ);
            return String.valueOf(differ / 1000 / 60);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    //转换（1200为距离0点时间长）   1200min>  20:00
    public static String asleepTime(String str) {
        Integer asleep = 0;
        int dottleIndex = str.indexOf(".");
        if (dottleIndex >= 0) {
            String intDepart = str.substring(0, str.indexOf("."));
            asleep = NumberConvertUtil.convertS2I(intDepart);
        } else {
            asleep = NumberConvertUtil.convertS2I(str);
        }

        if (asleep == null) return "";
        float temp = (float) asleep / 60;
        String time = String.valueOf(temp);
        int flag = time.indexOf(".");
        String hour = "00", min = "00";
        if (flag > 0) {
            hour = time.substring(0, flag);
            Integer h = Integer.valueOf(hour);
            if (h > 24) h = h - 24;
            hour = h.toString();
            String _min = time.substring(flag);
            Float f = Float.valueOf("0" + _min);
            f = f * 60;
            Double rint = Math.rint(f);
            min = String.valueOf(rint.intValue());
            if (min.length() == 1) min = "0" + min;
        }
        return hour + ":" + min;
    }

    //分钟>小时

    /**
     * @param min
     * @return day hour min
     */
    public static String minConvertDayHourMin(Double min) {
        String html = "0分";
        if (min != null) {
            Double m = (Double) min;
            String format;
            Object[] array;
            Integer days = (int) (m / (60 * 24));
            Integer hours = (int) (m / (60) - days * 24);
            Integer minutes = (int) (m - hours * 60 - days * 24 * 60);
            if (days > 0) {
                format = "%1$,d天%2$,d小时%3$,d分";
                array = new Object[]{days, hours, minutes};
            } else if (hours > 0) {
                format = "%1$,d小时%2$,d分";
                array = new Object[]{hours, minutes};
            } else {
                format = "%1$,d分";
                array = new Object[]{minutes};
            }
            html = String.format(format, array);
        }
        String res = html.replace("时0分", "时");
        return res;
    }

    public static List<String> betweenDays(String begin, String end) throws ParseException {
        Calendar startDay = Calendar.getInstance();
        Calendar endDay = Calendar.getInstance();

        startDay.setTime(FORMATTER.parse(begin));
        endDay.setTime(FORMATTER.parse(end));

        List<String> dates = new ArrayList<String>();
        dates.add(begin);
        // 给出的日期开始日比终了日大则不执行打印
        if (startDay.compareTo(endDay) >= 0) {
            return null;
        }
        // 现在打印中的日期
        Calendar currentPrintDay = startDay;
        while (true) {
            // 日期加一
            currentPrintDay.add(Calendar.DATE, 1);
            // 日期加一后判断是否达到终了日，达到则终止打印
            if (currentPrintDay.compareTo(endDay) == 0) {
                break;
            }
            dates.add(FORMATTER.format(currentPrintDay.getTime()));
        }
        dates.add(end);
        return dates;
    }

    public static List<List> createList(List target, int size) {
        List<List> listArr = new ArrayList<List>();
        //获取被拆分的数组个数
        int arrSize = target.size() % size == 0 ? target.size() / size : target.size() / size + 1;
        for (int i = 0; i < arrSize; i++) {
            List sub = new ArrayList();
            //把指定索引数据放入到list中
            for (int j = i * size; j <= size * (i + 1) - 1; j++) {
                if (j <= target.size() - 1) {
                    sub.add(target.get(j));
                }
            }
            listArr.add(sub);
        }
        return listArr;
    }


    public static List<String> splitWithOutNull(String param){
        String[] res = param.split(",");
        List<String> out = new ArrayList<String>();
        for (String v:res) {
            if (!StringUtils.isEmpty(v))
                out.add(v);
        }
        return out;
    }

    public static void main(String[] args) {
//        System.out.println(unitTranslate("2222"));
//        System.out.println(unitTranslate("22.0"));
//        System.out.println(unitTranslate("22"));
//        System.out.println(getValueByKeyAndFlag(5,"nj"));
//        List<String> aa = splitWithOutNull(",aaa,bbb,cc,");
//        System.out.println(aa);
        System.out.println(getF("1.2222"));
//        Map map1 = new HashMap();
//        map1.put("stuname","zhansan");
//        map1.put("stuno","1");
//
//        Map map2 = new HashMap();
//        map2.put("stuname","zhansan");
//        map2.put("stuno","2");
//
//        Map map3 = new HashMap();
//        map3.put("stuname","zhansan");
//        map3.put("stuno","3");
//
//        List<Map> studList= new ArrayList();
//        studList.add(map1);
//        studList.add(map2);
//        studList.add(map3);
//
//
//        List chooseList = new ArrayList();
//        chooseList.add("1");
//        chooseList.add("2");
//
//        for (Map m:studList) {
//            if (chooseList.contains(m.get("stuno"))){
//                System.out.println(m);
//            }
//        }


    }


}
