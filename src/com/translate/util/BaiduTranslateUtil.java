package com.translate.util;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class BaiduTranslateUtil {

	private static String TRANSLATE_URL = "http://api.fanyi.baidu.com/api/trans/vip/translate";
	private static String APPID = "20180103000111543";
	private static String P = "hKhTn4umupyJxY61Beny";
	private static String FROM = "auto";
	private static String TO = "zh";
	private static Integer LIMIT_CHINESE = 2000;
	private static Integer LIMIT_ENGLISH = 6000;

	// 语言简写 名称
	// auto 自动检测
	// zh 中文
	// en 英语
	// yue 粤语
	// wyw 文言文
	// jp 日语
	// kor 韩语
	// fra 法语
	// spa 西班牙语
	// th 泰语
	// ara 阿拉伯语
	// ru 俄语
	// pt 葡萄牙语
	// de 德语
	// it 意大利语

	// 请将单次请求长度控制在 6000 bytes以内。（汉字约为2000个）

	public static void main(String[] args) throws Exception {

		// String src = "Auto tracking system\r\n" + "自动跟踪系统主要由：传感器，驱动电机，控制器三大部分组成。此跟踪系统由传感器发送各种指令，例如：夜间返航、抗风、跟踪、待机等。本系统经选用进口器件，实用、可靠、精准的原则，经过高低温实验，是一种质量过硬的产品。经过试验证明，工作稳定、可靠、精准。The auto tracking system consists of three parts, that is, a sensor, a driving motor and a controller. The auto tracking system sends various commands through the sensor, such as returning at night, resisting wind, tracking, standby, etc. This tracking system selects and uses imported components. By adhering to the principles of practical, reliable and accurate, and by tested by high and low temperatures experiments, this product has been proven as a best-quality and stable, reliable and accurate-working product.\r\n" + "\r\n" + "跟踪系统配置：Euipment of the tracking system\r\n" + "光电传感器 回转支撑 推杆 限位保护 支架 组成。\r\n" + "Photoelectric sensor Rotary drive electrical stick limit switch solar rack\r\n" + "Component Name \r\n" + " Spec. Quantity/Per set Raw material Surface Treatment\r\n" + "photoelectric\r\n" + "sensor \r\n" + "Photoelectricity optical tracing 1set Stainless steel \r\n" + "rotary drive 1 \r\n" + "electrical stick \r\n" + "limit switch \r\n" + "solar rack \r\n" + "\r\n" + "\r\n" + "Photoelectric sensor\r\n" + "Auto tracking systems are hardware devices usually used on pole mounted solar arrays to allow the positioning of the solar panels to follow the movement of the sun. This helps ensure that there is maximum exposure for the solar cells. A tracking system can increase the output of your PV system by up to 30% -40%over non-tracked systems.\r\n" + "\r\n" + "Sheet I\r\n" + "\r\n" + "SPEC \r\n" + " Item Am-track1KW Am-track2kw Am-track3kw Am-track4kw Am-track5kw\r\n" + "System Power 0-1KW 1kw-2kw 2-3KW 3-4KW 4-5KW\r\n" + "Azimuth angle -180-180 -180-180 -180-180 -180-180 -180-180\r\n" + "Elevation angle -75-75 -75-75 -75-75 -75-75 -75-75\r\n" + "Anti-wind 35m/s\r\n" + "Ambient operating range -20 – 70 ℃\r\n" + "Control method Double axis Photoelectric sensor\r\n" + "Motor power AC:220V DC:24V\r\n" + "Frame area(M2)\r\n" + "approximate 7.5 15 25 35 40\r\n" + "Height (M) 2-2.5 2.8-3.2 3-3.5 4-5 5-5.8\r\n" + "Total weight(KG) 400 600 800 900 1200\r\n" + "Material Hot galvanized zinc steel\r\n" + "\r\n" + "Sheet II\r\n" + "\r\n" + "SPEC \r\n" + " Item Am-track6Kw Am-track7kw Am-track8kw Am-track9kw Am-track10kw\r\n" + "SYSTEM Power 5-6kw 7-8kw 8-9KW 9-10KW 10KW\r\n" + "Azimuth angle -180-180 -180-180 -180-180 -180-180 -180-180\r\n" + "Elevation angle -75-75 -75-75 -75-75 -75-75 -75-75\r\n" + "Anti-wind 35m/s\r\n" + "Ambient operating range -20 – 70 ℃\r\n" + "Control method Double axis Photoelectric sensor\r\n" + "Motor power AC:220V DC:24V\r\n" + "Frame area(M2)\r\n" + "approximate 45 52 60 70 80\r\n" + "Height (M) 5-5.8 5-5.8 5.8-6 6.2-6.8 6.8-7.5\r\n" + "Total weight(KG) 1700 1800 1900 2000 2100\r\n" + "Material Hot galvanized zinc steel\r\n" + "Feature: \r\n" + "Increase 30% -40% powers over non-tracked systems. \r\n" + "Only Photoelectric sensor could drive all the solar system to track the sun, without controller.\r\n" + "Wide search range, high track accuracy, no limit of weather, season and location.\r\n" + "Auto low the tracking rate or sleep in cloudy day.\r\n" + "Autos protect solar system under strong wind.\r\n" + "Auto return to east in the evening, plumb solar panels protect from snow rain and dust.\r\n" + "Anticorrosive aluminum shell protect photoelectric sensor.\r\n" + "Easy control and installation, low cost.\r\n" + "\r\n" + "Rotary drive\r\n" + "回转支承是一种具有高效、高承载能力，能够承受综合载荷的特大型组合轴承。是一般适用于承受巨大倾覆力矩并作相对旋转运动的机械设备，可以同时承受较大的轴向负荷、径向负荷和倾覆力矩。\r\n" + "回转支承一般直径尺寸为300mm-900mm，具有使用寿命长、传动精确平稳、结构紧凑、旋转灵活、安装简便和日常使用维护方便等特点. \r\n" + " \r\n" + "\r\n" + "\r\n" + "\r\n" + "\r\n" + "\r\n" + "\r\n" + "\r\n" + "\r\n" + "The rotary drive is an oversized combined bearing with high-efficiency and high load-carrying capability, which is able to bear combined load. It is a mechanical equipment which generally applies to bearing tremendous upsetting moment and then make relative rotative movements. This rotary drive is able to bear larger axial load, radial load and upsetting moment.\r\n" + "Generally, the diameter of the rotary drive is between 300mm to 900mm. It is characterized by long service lifetime, accurate and smooth transmission, compact conformation, flexible rotation, simple installation and convinent daily use and maintenance, etc.\r\n" + "\r\n" + "\r\n" + "\r\n" + "\r\n" + "\r\n" + "\r\n" + "\r\n" + "序 号number 基本型号basic model 外形尺寸overall dimensions 安装尺寸Installation dimensions 结构尺寸structure dimensions\r\n" + " SWA\r\n" + "SNA 外齿式external gear 内齿式internal gear H 外齿式external gear 内齿式internal gear n 通孔throughhole 螺纹孔threaded hole n1 H1 h\r\n" + " D d D d D1 D2 D1 D2 A型type A B.C.D型type B, C, D \r\n" + " φ φ T \r\n" + "1 2000.25 2190 1818 2182 1810 110 2126 1884 2116 1874 44 33 30 56 6 8 10\r\n" + "2 2240.25 2430 2058 2422 2040 110 2366 2124 2356 2114 48 33 30 56 6 8 10\r\n" + "3 2500.25 2700 2318 2682 2310 110 2626 2384 2616 2374 54 33 30 56 6 8 10\r\n" + "序 号number 齿轮参数gear parameters 外齿参数external gear parameters 内齿参数internal gear parameters 参考重量kg\r\n" + "Approx. Weight (kg)\r\n" + " b x m D e Z D e Z 外齿external gear 内齿internal gear\r\n" + "1 110 +0.5 16 2272 139 1728 109 1211 1196\r\n" + "2 110 +0.5 18 2520 137 1944 109 1373 1396\r\n" + "3 110 +0.5 18 2790 152 2214 124 1561 1532\r\n" + "\r\n" + "\r\n" + "\r\n" + "\r\n" + "\r\n" + "\r\n" + "\r\n" + "\r\n" + "electrical stick\r\n" + "电动推杆是一种电动执行机构，电动推杆主要由电机、推杆和控制装置等机构组成的一种新型直线执行机构，可以实现远距离控制、集中控制。电动推杆在一定范围行程内作往返运动，电动推杆标准行程在， 450，600mm。电动推杆可以根据不同的应用负荷而设计不同推力的电动推杆，一般其最大推力可达6000N，空载运行速度为4mm~35mm/s,电动推杆以12V 直流永磁电机为动力源，把电机的旋转运动转化为直线往复运动。The electrical stick is a new type of electrial and straightline actuator, which is mainly consisting of an electrical motor, a stick, and a control apparatus, etc. It is able to realize remote control and centralized control. The electrical stick makes back-and-forth movements within a defined stroke range and its strandard stroke is 450mm and 600mm. The electrical stick adjusts the magnitude of the thrust forces according to different actual load, and generally, the largest thrust force could reach 6000N. Its no-load running speed is 4mm~35mm/s. The electrical stick is powered by a DC 12V permant magnet motor, which turns the rotative motions of the motor into strainght reciprocating motions.\r\n" + "序号ordial 产品名称product name 规格/材料specification/material\r\n" + "1 G-450 DC12V 行程 stroke :450mm\r\n" + " 使用using:Hall Sensor 讯号signal: 5.7pulse/sec\r\n" + " 动负载dynamic load: 400kgs 静负载static load : 1000kgs\r\n" + " 电流electric current: 3.6A 速度speed: 2≦mm/sec\r\n" + "2 G-600 DC12V 行程stroke: 600mm\r\n" + " 使用using: Hall Sensor 讯号signal: 5.7pulse/sec\r\n" + " 动负载dynamic load: 400kgs 静负载static load :1000kgs\r\n" + " 电流electric current: 3.6A 速度speed: 2≦mm/sec\r\n" + "\r\n" + "\r\n" + "\r\n" + "限位保护：limit switch：\r\n" + "\r\n" + "限位开关主要就是限定位置，当到末端位置，限位开关就触动，停止继续运动，安装上行程开关，与其相对运动的固定点上安装极限位置的挡块，或者是相反安装位置。当行程开关的机械触头碰上挡块时，切断了（或改变了）控制电路，机械就停止运行或改变运行。 The limit switch mainly limits position. When it reaches the terminal, the limit switch will activate and stop there. The travel switch is installed then and also the limit position baffle plate is installed in the relative motion fixed point (or the opposite), When the mechnical contact terminal meets the baffle plate, it will cut (change) control circuit, then the machine will stop or change accordingly. \r\n" + "\r\n" + "头部 head：\r\n" + "在限位开关中，指作为转动机构具有独立机构的部分. In the limit switch, it refers to the independent part of the rotative \r\n" + "Mechanism.\r\n" + "开关外壳switch outcovering \r\n" + "保护开关季候的盒子，也叫外罩。\r\n" + "It is a box to protect the swithc, which is also known as a housing/cover.\r\n" + "\r\n" + "内置开关internal switch\r\n" + "\r\n" + "导管口（导线口）conduit orifice (wire orifice)\r\n" + "在线外开关中特指配线口，在这个部位进行电线密封. In off-line switch, it refers to wiring orifice, where the wires are sealed.\r\n" + "\r\n" + "驱动杆driving spindle\r\n" + "开关的一部分，将受到的外力传导到内部的弹簧机构、 接点进行开关的机构 it is part of the switch, which drives the switch by transfering the external forces to the internal spring mechanism and contact terminal.\r\n" + "\r\n" + "盖子cover\r\n" + "在内部配线结束后安装，确保密封性的部件it is a part that is installed after the internal wiring ,ensuring the sealing quality.\r\n" + "\r\n" + "\r\n" + "端子terminal\r\n" + "对电气输入输出的电路的导电部位进行配线作业的部分It is a part which is to wire the conducting position of the electric in-output circut. \r\n" + "\r\n" + "";
		// System.out.println("执行之前:" + src);
		// String srcFormat = srcFormatToEn(src, "en");
		// System.out.println("执行之后:" + srcFormat);

		// String src = ".,:、、、";
		// String src = ". Its no-load running speed is 4mm~35mm/s";
		// String src = "good bye";
		// List<String> translateFromEnToZn = translateFromEnToZn(src);
		// for (String tmp : translateFromEnToZn)
		// System.out.println(tmp);

		String src = "自动跟踪系统主要由：传感器，驱动电机，控制器三大部分组成。";
		List<String> translateFromZnToEn = translateFromZnToEn(src);
		for (String tmp : translateFromZnToEn)
			System.out.println(tmp);
	}

	private static List<String> translateFromZnToEn(String src) throws Exception {

		return translate(src, "zh", "en");
	}

	public static List<String> translateFromEnToZn(String src) throws Exception {

		System.out.println("参与翻译之前的字符串:" + src);

		String srcFormat = srcFormatToEn(src, "en");

		System.out.println("参与翻译的字符串:" + srcFormat);

		return translate(srcFormat, "en", "zh");
	}

	public static String srcFormatToEn(String src, String language) {

		String srcStr = "";

		// 过滤掉中文
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(src);
		char[] charArray = src.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			String cStr = String.valueOf(charArray[i]);
			m = p.matcher(cStr);
			if (!m.find()) {
				srcStr += cStr;
			}
		}

		// 标点符号转换
		if (srcStr.contains("‘"))
			srcStr = srcStr.replace("‘", "'");
		if (srcStr.contains("’"))
			srcStr = srcStr.replace("’", "'");
		if (srcStr.contains("”"))
			srcStr = srcStr.replace("“", "\"");
		if (srcStr.contains("”"))
			srcStr = srcStr.replace("“", "\"");
		if (srcStr.contains("："))
			srcStr = srcStr.replace("：", ":");
		if (srcStr.contains("，"))
			srcStr = srcStr.replace("，", ",");
		if (srcStr.contains("。"))
			srcStr = srcStr.replace("。", ".");
		if (srcStr.contains("？"))
			srcStr = srcStr.replace("？", "?");
		if (srcStr.contains("！"))
			srcStr = srcStr.replace("！", "!");
		if (srcStr.contains("（"))
			srcStr = srcStr.replace("（", "(");
		if (srcStr.contains("）"))
			srcStr = srcStr.replace("）", ")");
		if (srcStr.contains("《"))
			srcStr = srcStr.replace("《", "<");
		if (srcStr.contains("》"))
			srcStr = srcStr.replace("》", ">");

		return srcStr;
	}

	private static List<String> translate(String src, String from, String to) throws Exception {

		int length = src.getBytes().length;
		if (length > getLimitNum(from)) {
			throw new Exception("字节长度超长");
		}

		String q = src;
		if ("".equals(q)) {
			List<String> resultList = new ArrayList<String>();
			resultList.add(q);
			return resultList;
		}

		q = q.replace("–", "");

		Integer salt_int = (int) ((Math.random() * 100) / 1);
		String salt = String.valueOf(salt_int);

		String sign_source = APPID + q + salt_int + P;
		String sign = DigestUtils.md5Hex(sign_source);

		System.out.println("sign_source:" + sign_source);
		System.out.println("sign:" + sign);

		// get请求
		// q = URLEncoder.encode(q, "UTF-8");
		// String result_url = TRANSLATE_URL + "?" + "q=" + URLEncoder.encode(q) + "&from=" + from + "&to=" + to + "&appid=" + APPID + "&salt=" + salt + "&sign=" + sign;
		System.out.println(q);
		System.out.println(URLEncoder.encode(q));
		String result_url = TRANSLATE_URL + "?" + "q=" + q + "&from=" + from + "&to=" + to + "&appid=" + APPID + "&salt=" + salt + "&sign=" + sign;

		String result = HttpUtil.postWithParams(result_url);

		System.out.println("百度翻译响应结果>>>:" + result);

		List<String> transelated_result = analyzeResult(result, q);
		return transelated_result;
	}

	private static List<String> analyzeResult(String result, String q) throws Exception {

		JSONObject json = JSONObject.parseObject(result);

		String error_code = json.getString("error_code");
		if (null != error_code) {
			switch (error_code) {
			case "54001":
				throw new Exception("签名错误");
			}
		}

		JSONArray jsonArray = json.getJSONArray("trans_result");

		List<String> resultList = new ArrayList<String>();

		Iterator<Object> iterator = jsonArray.iterator();
		while (iterator.hasNext()) {
			JSONObject next = (JSONObject) iterator.next();
			String dst = next.getString("dst");
			resultList.add(dst);
		}

		return resultList;
	}

	public static Integer getLimitNum(String language) throws Exception {

		switch (language) {
		case "en":
			return LIMIT_ENGLISH;
		case "zh":
			return LIMIT_CHINESE;
		}

		throw new Exception("没有匹配类型");
	}
}
