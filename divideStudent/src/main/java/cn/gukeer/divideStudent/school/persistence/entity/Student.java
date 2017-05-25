/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.gukeer.divideStudent.school.persistence.entity;

import cn.gukeer.common.persistence.DataEntity;
import cn.gukeer.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

/**
 * 学生管理Entity
 * @author xiangfusheng
 * @version 2016-06-29
 */
public class Student extends DataEntity<Student> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 学生姓名
	private String namePinyin;		// 姓名拼音
	private String englishName;		// 学生英文名
	private String presetGradeClass;		// 预设班级
	private String gradeClass;		// 班级
	private String gender;		// 性别
	private String hight;		// 身高
	private String weight;		// 体重
	private String schoolRollNo;		// 学籍号
	private String studentNo;		// 学号
	private String countryShoolRollNo;		// 全国学籍号
	private String educationID;		// 教育ID号
	private String birthday;		// 出生日期
	private String sourceOfStudent;		// 生源地
	private String credentialType;		// 有效证件类型
	private String credentialNo;		// 有效证件号
	private String isClassCadre;		// 是否是班级干部
	private String isMilitaryFamily;		// 是否是军属
	private String isDisability;		// 是否残疾
	private String isTwin;		// 是否是双胞胎
	private String twinNo;		// 双胞胎序号
	private String studentType;		// 学生类别
	private String countryType;		// 国别
	private String nation;		// 民族
	private String birthplace;		// 籍贯
	private String politicalStatus;		// 政治面貌
	private String studyWay;		// 就读方式
	private String currentAddress;		// 现住址
	private String mailAddress;		// 通讯地址
	private String accountType;		// 户口性质
	private String accountAddress;		// 户口所在地
	private String accountAddressDetail;		// 户口所在地-详细地址
	private String isTreatedByCity;		// 是否按本市户口学生对待
	private String isTheCitySchoolRoll;		// 是否本市学籍
	private String recruitType;		// 招生类别
	private String photo;		// 学生照片
	
	public Student() {
		super();
	}

	public Student(String id){
		super(id);
	}

	
	@Length(min=0, max=64, message="姓名拼音长度必须介于 0 和 64 之间")
	@ExcelField(title="姓名拼音", align=2, sort=8)
	public String getNamePinyin() {
		return namePinyin;
	}

	public void setNamePinyin(String namePinyin) {
		this.namePinyin = namePinyin;
	}
	
	@Length(min=0, max=64, message="学生英文名长度必须介于 0 和 64 之间")
	@ExcelField(title="学生英文名", align=2, sort=9)
	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	
	@Length(min=0, max=10, message="预设班级长度必须介于 0 和 10 之间")
	@ExcelField(title="预设班级", align=2, sort=10)
	public String getPresetGradeClass() {
		return presetGradeClass;
	}

	public void setPresetGradeClass(String presetGradeClass) {
		this.presetGradeClass = presetGradeClass;
	}
	
	@Length(min=0, max=64, message="班级长度必须介于 0 和 64 之间")
	@ExcelField(title="班级", align=2, sort=11)
	public String getGradeClass() {
		return gradeClass;
	}

	public void setGradeClass(String gradeClass) {
		this.gradeClass = gradeClass;
	}
	
	@Length(min=0, max=1, message="性别长度必须介于 0 和 1 之间")
	@ExcelField(title="性别", align=2, sort=12)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@ExcelField(title="身高", align=2, sort=13)
	public String getHight() {
		return hight;
	}

	public void setHight(String hight) {
		this.hight = hight;
	}
	
	@ExcelField(title="体重", align=2, sort=14)
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	@Length(min=0, max=64, message="学籍号长度必须介于 0 和 64 之间")
	@ExcelField(title="学籍号", align=2, sort=15)
	public String getSchoolRollNo() {
		return schoolRollNo;
	}

	public void setSchoolRollNo(String schoolRollNo) {
		this.schoolRollNo = schoolRollNo;
	}
	
	@Length(min=0, max=64, message="学号长度必须介于 0 和 64 之间")
	@ExcelField(title="学号", align=2, sort=16)
	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	
	@Length(min=0, max=64, message="全国学籍号长度必须介于 0 和 64 之间")
	@ExcelField(title="全国学籍号", align=2, sort=17)
	public String getCountryShoolRollNo() {
		return countryShoolRollNo;
	}

	public void setCountryShoolRollNo(String countryShoolRollNo) {
		this.countryShoolRollNo = countryShoolRollNo;
	}
	
	@Length(min=0, max=64, message="教育ID号长度必须介于 0 和 64 之间")
	@ExcelField(title="教育ID号", align=2, sort=18)
	public String getEducationID() {
		return educationID;
	}

	public void setEducationID(String educationID) {
		this.educationID= educationID;
	}
	
	@ExcelField(title="出生日期", align=2, sort=19)
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	@Length(min=0, max=64, message="生源地长度必须介于 0 和 64 之间")
	@ExcelField(title="生源地", align=2, sort=20)
	public String getSourceOfStudent() {
		return sourceOfStudent;
	}

	public void setSourceOfStudent(String sourceOfStudent) {
		this.sourceOfStudent = sourceOfStudent;
	}
	
	@Length(min=0, max=64, message="有效证件类型长度必须介于 0 和 64 之间")
	@ExcelField(title="有效证件类型", align=2, sort=21)
	public String getCredentialType() {
		return credentialType;
	}

	public void setCredentialType(String credentialType) {
		this.credentialType = credentialType;
	}
	
	@Length(min=0, max=64, message="有效证件号长度必须介于 0 和 64 之间")
	@ExcelField(title="有效证件号", align=2, sort=22)
	public String getCredentialNo() {
		return credentialNo;
	}

	public void setCredentialNo(String credentialNo) {
		this.credentialNo = credentialNo;
	}
	
	@Length(min=0, max=1, message="是否是班级干部长度必须介于 0 和 1 之间")
	@ExcelField(title="是否是班级干部", align=2, sort=23)
	public String getIsClassCadre() {
		return isClassCadre;
	}

	public void setIsClassCadre(String isClassCadre) {
		this.isClassCadre = isClassCadre;
	}
	
	@Length(min=0, max=1, message="是否是军属长度必须介于 0 和 1 之间")
	@ExcelField(title="是否是军属", align=2, sort=24)
	public String getIsMilitaryFamily() {
		return isMilitaryFamily;
	}

	public void setIsMilitaryFamily(String isMilitaryFamily) {
		this.isMilitaryFamily = isMilitaryFamily;
	}
	
	@Length(min=0, max=1, message="是否残疾长度必须介于 0 和 1 之间")
	@ExcelField(title="是否残疾", align=2, sort=25)
	public String getIsDisability() {
		return isDisability;
	}

	public void setIsDisability(String isDisability) {
		this.isDisability = isDisability;
	}
	
	@Length(min=0, max=1, message="是否是双胞胎长度必须介于 0 和 1 之间")
	@ExcelField(title="是否是双胞胎", align=2, sort=26)
	public String getIsTwin() {
		return isTwin;
	}

	public void setIsTwin(String isTwin) {
		this.isTwin = isTwin;
	}
	
	@Length(min=0, max=64, message="双胞胎序号长度必须介于 0 和 64 之间")
	@ExcelField(title="双胞胎序号", align=2, sort=27)
	public String getTwinNo() {
		return twinNo;
	}

	public void setTwinNo(String twinNo) {
		this.twinNo = twinNo;
	}
	
	@Length(min=0, max=1, message="学生类别长度必须介于 0 和 1 之间")
	@ExcelField(title="学生类别", align=2, sort=28)
	public String getStudentType() {
		return studentType;
	}

	public void setStudentType(String studentType) {
		this.studentType = studentType;
	}
	
	@Length(min=0, max=64, message="国别长度必须介于 0 和 64 之间")
	@ExcelField(title="国别", align=2, sort=29)
	public String getCountryType() {
		return countryType;
	}

	public void setCountryType(String countryType) {
		this.countryType = countryType;
	}
	
	@Length(min=0, max=10, message="民族长度必须介于 0 和 10 之间")
	@ExcelField(title="民族", align=2, sort=30)
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	
	@Length(min=0, max=10, message="籍贯长度必须介于 0 和 10 之间")
	@ExcelField(title="籍贯", align=2, sort=31)
	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}
	
	@Length(min=0, max=1, message="政治面貌长度必须介于 0 和 1 之间")
	@ExcelField(title="政治面貌", align=2, sort=32)
	public String getPoliticalStatus() {
		return politicalStatus;
	}

	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}
	
	@Length(min=0, max=1, message="就读方式长度必须介于 0 和 1 之间")
	@ExcelField(title="就读方式", align=2, sort=33)
	public String getStudyWay() {
		return studyWay;
	}

	public void setStudyWay(String studyWay) {
		this.studyWay = studyWay;
	}
	
	@Length(min=0, max=64, message="现住址长度必须介于 0 和 64 之间")
	@ExcelField(title="现住址", align=2, sort=34)
	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	
	@Length(min=0, max=64, message="通讯地址长度必须介于 0 和 64 之间")
	@ExcelField(title="通讯地址", align=2, sort=35)
	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	
	@Length(min=0, max=64, message="户口性质长度必须介于 0 和 64 之间")
	@ExcelField(title="户口性质", align=2, sort=36)
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	@Length(min=0, max=64, message="户口所在地长度必须介于 0 和 64 之间")
	@ExcelField(title="户口所在地", align=2, sort=37)
	public String getAccountAddress() {
		return accountAddress;
	}

	public void setAccountAddress(String accountAddress) {
		this.accountAddress = accountAddress;
	}
	
	@Length(min=0, max=64, message="户口所在地-详细地址长度必须介于 0 和 64 之间")
	@ExcelField(title="户口所在地-详细地址", align=2, sort=38)
	public String getAccountAddressDetail() {
		return accountAddressDetail;
	}

	public void setAccountAddressDetail(String accountAddressDetail) {
		this.accountAddressDetail = accountAddressDetail;
	}
	
	@Length(min=0, max=1, message="是否按本市户口学生对待长度必须介于 0 和 1 之间")
	@ExcelField(title="是否按本市户口学生对待", align=2, sort=39)
	public String getIsTreatedByCity() {
		return isTreatedByCity;
	}

	public void setIsTreatedByCity(String isTreatedByCity) {
		this.isTreatedByCity = isTreatedByCity;
	}
	
	@Length(min=0, max=1, message="是否本市学籍长度必须介于 0 和 1 之间")
	@ExcelField(title="是否本市学籍", align=2, sort=40)
	public String getIsTheCitySchoolRoll() {
		return isTheCitySchoolRoll;
	}

	public void setIsTheCitySchoolRoll(String isTheCitySchoolRoll) {
		this.isTheCitySchoolRoll = isTheCitySchoolRoll;
	}
	
	@Length(min=0, max=64, message="招生类别长度必须介于 0 和 64 之间")
	@ExcelField(title="招生类别", align=2, sort=41)
	public String getRecruitType() {
		return recruitType;
	}

	public void setRecruitType(String recruitType) {
		this.recruitType = recruitType;
	}
	
	@Length(min=0, max=64, message="学生照片长度必须介于 0 和 64 之间")
	@ExcelField(title="学生照片", align=2, sort=42)
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@Length(min=0, max=64, message="学生姓名长度必须介于 0 和 64 之间")
	@ExcelField(title="学生姓名", align=2, sort=7)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}