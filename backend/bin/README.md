# 小额贷

## Project Git

* Backend:  
    * https://bitbucket.org/JNingCMS/smallloanplatform/src/master/
* Frontend:
    * https://bitbucket.org/JNingCMS/smallloanfrontend/src/master/

## Compile

* Linux
    * `mvn clean install`
* Win
    * `mvnw clean package`

## Run

* Linux
    * `java -jar ./target/smallloanplatform-1.1.0.jar`
* Win
    * `java -jar .\target\smallloanplatform-1.1.0.jar`

## Web Portal

* Local
    * http://localhost:8081/login?username=admin&passwd=admin
    * http://localhost:8081/login?username=Roger&password=Roger
* Aliyun
    * http://47.105.81.242:8081/login?username=admin&passwd=admin
    * https://47.105.81.242:8443/login?username=admin&passwd=admin
* Front Demo
    * http://119.28.140.101:9529

## Other

* https key
    * `keytool -genkey -alias tomcat -dname "CN=Andy,OU=kfit,O=kfit,L=HaiDian,ST=BeiJing,C=CN" -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 365`

## 项目设计

### 系统的用户主要就是两类人：

* 担保中心员工
    * 管理员
    * 调查员
* 银行人员

## Team

* Slack Channel: https://xiaoedaiweb.slack.com/messages

## Doc

* [可行性报告](https://docs.google.com/document/d/1-c2mqZJMeBhutmKGNgc4MTls8VWbncBKIw9mtqd_Q10/edit)
* [流程图](https://miro.com/welcomeonboard/EvNDxcYPhkJWMapzwRNJdtSkfhF3gwvOTzAkGJAYcckdmVchjB7J4AtfScmQE85j)
* Ref
    * [SpringBoot+Shiro+Vue前后端分离项目通过JWT实现自动登录](http://asing1elife.com/java/springboot/shiro/vue/2018/08/09/SpringBoot+Shiro%E5%89%8D%E5%90%8E%E7%AB%AF%E5%88%86%E7%A6%BB%E9%A1%B9%E7%9B%AE%E9%80%9A%E8%BF%87JWT%E5%AE%9E%E7%8E%B0%E8%87%AA%E5%8A%A8%E7%99%BB%E5%BD%95/)

## Login and authentication and authorization

### Mysql tables

* User Table

```sql
create table users(
  username VARCHAR(30), 
  password VARCHAR(30), 
  PHONE VARCHAR(30), 
  EMAIL VARCHAR(30), 
  REG_DATE DATETIME, 
  UPDATE_DATE DATETIME
);
```

* User Role Table

```sql
create table user_role(
  username VARCHAR(30), 
  password VARCHAR(30), 
  role VARCHAR(30)
);
```

角色(role):

```bash
#前台受理人员(银行): bank_reception_user
#后台复核人员: super_check_user
#担保中心主任: super_admin_user
#调查人员（银行/社保): super_or_bank_investi_user
#银行后台人员: bank_admin_user
```

* Token Cache Table

```sql
create table token_cache(
  username VARCHAR(30), 
  token VARCHAR(100), 
  updatetime DATETIME
);
```

以上三个表主键均为username

* Loan Transcation Table

```sql
create table loan_transaction(
  tid INT NOT NULL, 
  apply_person_id VARCHAR(30),
  apply_name VARCHAR(30),
  apply_gender VARCHAR(10),
  apply_person_type VARCHAR(30),
  apply_education VARCHAR(30),
  apply_census_type VARCHAR(30),
  apply_marriage VARCHAR(30),
  apply_phone VARCHAR(30),
  apply_home_address VARCHAR(50),
  apply_person_photo VARCHAR(50),

  company_person_name VARCHAR(30),
  company_person_id VARCHAR(30),
  company_name VARCHAR(50),
  company_region VARCHAR(30),
  company_reg_id VARCHAR(50),
  company_job_num INT,
  company_business_scope VARCHAR(30),
  company_employee_num INT,
  company_entity_type VARCHAR(30),

  loan_plan_period TINYINT,
  loan_plan_amount INT,
  loan_qualification VARCHAR(30),
  loan_interest_subsidy VARCHAR(30),
  loan_bank VARCHAR(30),

  apply_date DATETIME,

  audit_fail_reason VARCHAR(200), 
  join_audit_fail_reason VARCHAR(200), 

  check_people_list VARCHAR(50),

  audit_amount INT,
  audit_period TINYINT,
  audit_interest_rate FLOAT,

  bank_bondsman VARCHAR(200),
  bank_store VARCHAR(50),
  bank_amount INT,
  bank_period TINYINT,
  bank_interest_rate FLOAT,
  bank_refund_date DATETIME,

  bank_post_real_refund INT,
  bank_post_real_refund_date DATETIME,
  bank_post_is_refound TINYINT,

  subsidy_amount INT,
  subsidy_account VARCHAR(200),

  overdue_amount INT,
  overdue_refound_amount INT,
  overdue_refound_date DATETIME,

  blacklist_reason VARCHAR(200),
  blacklist_date DATETIME,
  
  compensation_set TINYINT,
  compensation_amount INT,
  compensation_date DATETIME,

  legis_set TINYINT,
  legis_amount INT,
  legis_date DATETIME,

  PRIMARY KEY (tid)
);
```

* Fund Table

```sql
CREATE TABLE compensation_fund(
  fund_id INT,
  total INT,
  remain INT,
  
  PRIMARY KEY(fund_id)
);
```

* Investigation Table

```sql
CREATE TABLE investigation(
  tid INT NOT NULL,
  loan_id INT,
  investigation_result VARCHAR(200),
  investigation_date DATETIME,
  revenue INT,
  stock_value INT,
  back_deposit INT,
  registration VARCHAR(200),
  business_prospect VARCHAR(50),
  fixed_funds INT,
  fund_demand INT,
  fund_usage VARCHAR(50),
  labor_charge INT,
  vehicle_value INT,
  employee_num INT,
  regis_date DATE,
  lease_period DATE,
  self_funds INT,
  liquidity_funds INT,
  other_charge INT,
  equip_value INT,
  place_source VARCHAR(50),
  place_area INT,
  annual_profit INT,
  annual_sales INT,
  
  PRIMARY KEY(tid)
);
```

## Interfaces

Use following func in Chrome console to test interface:

```javascript
function httpPost(URL, PARAMS) {
  var temp = document.createElement("form");
  temp.action = URL;
  temp.method = "post";
  temp.style.display = "none";
  for (var x in PARAMS) {
    var opt = document.createElement("textarea");
    opt.name = x;
    opt.value = PARAMS[x];
    temp.appendChild(opt);
  }
  document.body.appendChild(temp);
  temp.submit();
  return temp;
}

var params = {"username":"Roger2","password":"Roger2"};

var httpUrl = "https://localhost/user/login";
//or use aliyun address (https://47.105.81.242/user/login)

httpPost(httpUrl,params);
```

### 1 Login use post

url: `https://localhost/user/login`

```javascript
var params = {"username":"Roger2","password":"Roger2"};

response:

{"responseCode":0, "token":"4b3a4063-6a50-4cd0-8ed2-7fc2302da718_Um9nZXIy", "userRole":"ROLE_bank_reception_user", "message":"success"}
```

### 2 Other operations

```bash
Use the token in step1

GET:

https://localhost/user/welcomepage?resttoken=421a39c0-310a-4fb8-8517-f2946c67e307_Um9nZXIy

response:

{"responseCode":0,"token":"4b3a4063-6a50-4cd0-8ed2-7fc2302da718_Um9nZXIy","userRole":"ROLE_bank_reception_user","message":"success"}


Post:

var params = {"resttoken":"421a39c0-310a-4fb8-8517-f2946c67e307_Um9nZXIy"};

var httpUrl = "https://localhost/user/welcomepage";

httpPost(httpUrl,params);

response:

{"responseCode":0,"token":"4b3a4063-6a50-4cd0-8ed2-7fc2302da718_Um9nZXIy","userRole":"ROLE_bank_reception_user","message":"success"}
```

### 3 other page that have no access

```bash
https://localhost/user/super_admin_user?resttoken=421a39c0-310a-4fb8-8517-f2946c67e307_Um9nZXIy
403 Forbidden
```

### 4 Wrong url

```bash
https://localhost//user/notexist?resttoken=421a39c0-310a-4fb8-8517-f2946c67e307_Um9nZXIy
404 not found
```

### 5 commit application form interface (personal loan)

```bash
https://localhost/user/commitapplication
var params = {"resttoken":"6344f21a-18d1-4f8f-a36b-ecc10de412bd_Um9nZXIy"};
var httpUrl = "https://localhost/user/commitapplication";
httpPost(httpUrl,params);
{"code":200,"total":0,"msg":"test msg","data":"test data"}
```

### 6 Query personal application info (personal loan)

```bash
https://localhost/user/getapplicationinfo
var params = {"resttoken":"6344f21a-18d1-4f8f-a36b-ecc10de412bd_Um9nZXIy"};
var httpUrl = "https://localhost/user/getapplicationinfo";
httpPost(httpUrl,params);
{"code":200,"total":0,"msg":"test msg","data":"test data"}
```

### 7 User management

```bash
https://localhost/user/adduser
var params = {"resttoken":"ceea574b-e32d-4a28-a645-2dab9b64bdfe_Um9nZXI=","username":"dada","password":"123456","confirmedpassword":"123456","role":"bank_reception_user","phone":"13976542113","email":"dfasdfas@gmail.com"};
var httpUrl = "https://localhost/user/adduser";
httpPost(httpUrl,params);

if not admin : 403

if admin:
{"code":200,"total":2,"msg":"user created success!","data":"user created success!"}

delete user:

https://localhost/user/deleteuser

var params = {"resttoken":"f644bb3f-cd05-41d2-a52d-1d92e0eae34b_Um9nZXI=","username":"dada","confirmusername":"dada"};
var httpUrl = "https://localhost/user/deleteuser";
httpPost(httpUrl,params);

{"code":200,"total":2,"msg":"user created success!","data":"user created success!"}
```

### 8 Personal loan interfaces
```bash
https://localhost/user/getapplicationinfo
var params = {"resttoken":"ceea574b-e32d-4a28-a645-2dab9b64bdfe_Um9nZXI="};
var httpUrl = "https://localhost/user/getapplicationinfo";
httpPost(httpUrl,params);

response:
{"code":200,"total":0,"msg":"test msg","data":[{"tid":10000001,"applyPersonId":"test1","applyName":"test1","applyGender":"test1","applyPersonType":"test1","applyEducation":"test1","applyCensusType":null,"applyMarriage":null,"applyPhone":null,"applyHomeAddress":null,"applyPersonPhoto":null,"companyPersonName":null,"companyPersonId":null,"companyName":null,"companyRegion":null,"companyRegId":null,"companyJobNum":null,"companyBusinessScope":null,"companyEmployeeNum":null,"companyEntityType":null,"loanPlanPeriod":null,"loanPlanAmount":null,"loanQualification":null,"loanInterestSubsidy":null,"loanBank":null,"applyDate":null,"auditFailReason":null,"joinAuditFailReason":null,"checkPeopleList":null,"auditAmount":null,"auditPeriod":null,"auditInterestRate":null,"bankBondsman":null,"bankStore":null,"bankAmount":null,"bankPeriod":null,"bankInterestRate":null,"bankRefundDate":null,"bankPostRealRefund":null,"bankPostRealRefundDate":null,"bankPostIsRefound":null,"subsidyAmount":null,"subsidyAccount":null,"overdueAmount":null,"overdueRefoundAmount":null,"overdueRefoundDate":null,"blacklistReason":null,"blacklistDate":null,"compensationSet":null,"compensationAmount":null,"compensationDate":null,"legisSet":null,"legisAmount":null,"legisDate":null},{"tid":10000002,"applyPersonId":"test2","applyName":"test2","applyGender":"test2","applyPersonType":"test2","applyEducation":"test2","applyCensusType":null,"applyMarriage":null,"applyPhone":null,"applyHomeAddress":null,"applyPersonPhoto":null,"companyPersonName":null,"companyPersonId":null,"companyName":null,"companyRegion":null,"companyRegId":null,"companyJobNum":null,"companyBusinessScope":null,"companyEmployeeNum":null,"companyEntityType":null,"loanPlanPeriod":null,"loanPlanAmount":null,"loanQualification":null,"loanInterestSubsidy":null,"loanBank":null,"applyDate":null,"auditFailReason":null,"joinAuditFailReason":null,"checkPeopleList":null,"auditAmount":null,"auditPeriod":null,"auditInterestRate":null,"bankBondsman":null,"bankStore":null,"bankAmount":null,"bankPeriod":null,"bankInterestRate":null,"bankRefundDate":null,"bankPostRealRefund":null,"bankPostRealRefundDate":null,"bankPostIsRefound":null,"subsidyAmount":null,"subsidyAccount":null,"overdueAmount":null,"overdueRefoundAmount":null,"overdueRefoundDate":null,"blacklistReason":null,"blacklistDate":null,"compensationSet":null,"compensationAmount":null,"compensationDate":null,"legisSet":null,"legisAmount":null,"legisDate":null}]}


```