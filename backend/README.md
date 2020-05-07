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

## 打包
mvn clean install -Dmaven.test.skip -U


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
system_admin
super_system_admin


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
var params = {"resttoken":"ceea574b-e32d-4a28-a645-2dab9b64bdfe_Um9nZXI=","username":"dada","password":"123456","confirmedpassword":"123456","role":"bank_reception_user","phone":"13976542113","email":"dfasdfas@gmail.com","region":"region1"};
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

#################################################################################################################################################################################################

### 8 Personal loan interfaces  1--8 steps:

``` bash
<1> commit application(添加申贷信息)
Post:
https://localhost/user/addpersonalloaninfo?resttoken=99c2e03f-fe43-446f-9bdb-566bf6cfd221_Um9nZXI=
{
            "applyPersonId": "test3",
            "applyName": "test3",
            "applyGender": "test3",
            "applyPersonType": "test3",
            "applyEducation": "test3",
            "applyCensusType": "test3",
            "applyMarriage": null,
            "applyPhone": null,
            "applyHomeAddress": null,
            "applyPersonPhoto": null,
            "companyPersonName": null,
            "companyPersonId": null,
            "companyName": null,
            "companyRegion": null,
            "companyRegId": null,
            "companyJobNum": null,
            "companyBusinessScope": null,
            "companyEmployeeNum": null,
            "companyEntityType": null,
            "loanPlanPeriod": null,
            "loanPlanAmount": null,
            "loanQualification": null,
            "loanInterestSubsidy": null,
            "loanBank": null,
            "applyDate": null,
            "auditFailReason": null,
            "joinAuditFailReason": null,
            "checkPeopleList": null,
            "auditAmount": null,
            "auditPeriod": null,
            "auditInterestRate": null,
            "bankBondsman": null,
            "bankStore": null,
            "bankAmount": null,
            "bankPeriod": null,
            "bankInterestRate": null,
            "bankRefundDate": null,
            "bankPostRealRefund": null,
            "bankPostRealRefundDate": null,
            "bankPostIsRefound": null,
            "subsidyAmount": null,
            "subsidyAccount": null,
            "overdueAmount": null,
            "overdueRefoundAmount": null,
            "overdueRefoundDate": null,
            "blacklistReason": null,
            "blacklistDate": null,
            "compensationSet": null,
            "compensationAmount": null,
            "compensationDate": null,
            "legisSet": null,
            "legisAmount": null,
            "legisDate": null,
            "adminRegion": null,
            "gmtCreated": null,
            "gmtModified": null,
            "currentStep": null,
            "loanType": null
        }
response:
{
    "code": 200,
    "total": 0,
    "msg": "create success",
    "data": "create success"
}


<2> query application(查询所有申贷信息列表)
Get:
https://localhost/user/listpersonalloaninfo?resttoken=99c2e03f-fe43-446f-9bdb-566bf6cfd221_Um9nZXI=

response:
{
    "code": 200,
    "total": 0,
    "msg": "test msg",
    "data": [..............]
}
<3> query single application info(查询单个申贷信息)
Post:
https://localhost/user/singlepersonalloaninfo?resttoken=1fb305b7-7e2b-4442-9b61-47e739c74019_Um9nZXI=
{"tid": "10000003"}

response:
{
    "code": 200,
    "total": 0,
    "msg": "test msg",
    "data": [........]
}

<4> Checking pass(审核通过)
https://localhost/user/checkingpassorrefuse?resttoken=1fb305b7-7e2b-4442-9b61-47e739c74019_Um9nZXI=
Post:
{"tid": "10000001","currentStep": "3"}
response:
{
    "code": 200,
    "total": 0,
    "msg": "check pass",
    "data": "check pass"
}

<5> Checking not pass(审核失败)
https://localhost/user/checkingpassorrefuse?resttoken=1fb305b7-7e2b-4442-9b61-47e739c74019_Um9nZXI=
{"tid": "10000001","currentStep":"4"}
response:
{
    "code": 200,
    "total": 0,
    "msg": "check pass",
    "data": "check pass"
}

<5> Allocate investigation task(分配任务)
https://localhost/user/allocateinvestigatetask?resttoken=1fb305b7-7e2b-4442-9b61-47e739c74019_Um9nZXI=
{"tid": "10000003","checkPeopleList":"张三,李四,John"}

response:
{
    "code": 200,
    "total": 0,
    "msg": "allocate success",
    "data": "allocate success"
}

<6> Commit investigation info(提交考察信息接口)
https://localhost/user/commitinvestigateinfo?resttoken=6606da8c-957c-4a56-8072-02df232fbbbc_Um9nZXI=
{"loanId": "10000003","investigationResult":"实体考察合格。。。。经营场地500平。。"}

response:
{
    "code": 200,
    "total": 0,
    "msg": "commit success!",
    "data": "commit success!"
}


<7> Commit Joint review info(提交联合会审信息)
https://localhost/user/commitjointreviewinfo?resttoken=22ac2386-ee00-4697-8ba9-d1cede5b1296_Um9nZXI=
{"tid": "10000003","auditAmount":"150000","auditPeriod":"2","auditInterestRate":"0.255"}

response:
{
    "code": 200,
    "total": 0,
    "msg": "commit success!",
    "data": "commit success!"
}


<8> Joint review pass
https://localhost/user/commitjointreviewresult?resttoken=22ac2386-ee00-4697-8ba9-d1cede5b1296_Um9nZXI=
{"tid": "10000003","currentStep": "7"}

response:
{
    "code": 200,
    "total": 0,
    "msg": "commit success!",
    "data": "commit success!"
}

<9> Joint review not pass
https://localhost/user/commitjointreviewresult?resttoken=22ac2386-ee00-4697-8ba9-d1cede5b1296_Um9nZXI=
{"tid": "10000003","currentStep": "8"}

response:
{
    "code": 200,
    "total": 0,
    "msg": "commit success!",
    "data": "commit success!"
}


#################################################################################################################################################################################################



https://localhost/user/getapplicationinfo
var params = {"resttoken":"ceea574b-e32d-4a28-a645-2dab9b64bdfe_Um9nZXI="};
var httpUrl = "https://localhost/user/getapplicationinfo";
httpPost(httpUrl,params);

response:
{"code":200,"total":0,"msg":"test msg","data":[{"tid":10000001,"applyPersonId":"test1","applyName":"test1","applyGender":"test1","applyPersonType":"test1","applyEducation":"test1","applyCensusType":null,"applyMarriage":null,"applyPhone":null,"applyHomeAddress":null,"applyPersonPhoto":null,"companyPersonName":null,"companyPersonId":null,"companyName":null,"companyRegion":null,"companyRegId":null,"companyJobNum":null,"companyBusinessScope":null,"companyEmployeeNum":null,"companyEntityType":null,"loanPlanPeriod":null,"loanPlanAmount":null,"loanQualification":null,"loanInterestSubsidy":null,"loanBank":null,"applyDate":null,"auditFailReason":null,"joinAuditFailReason":null,"checkPeopleList":null,"auditAmount":null,"auditPeriod":null,"auditInterestRate":null,"bankBondsman":null,"bankStore":null,"bankAmount":null,"bankPeriod":null,"bankInterestRate":null,"bankRefundDate":null,"bankPostRealRefund":null,"bankPostRealRefundDate":null,"bankPostIsRefound":null,"subsidyAmount":null,"subsidyAccount":null,"overdueAmount":null,"overdueRefoundAmount":null,"overdueRefoundDate":null,"blacklistReason":null,"blacklistDate":null,"compensationSet":null,"compensationAmount":null,"compensationDate":null,"legisSet":null,"legisAmount":null,"legisDate":null},{"tid":10000002,"applyPersonId":"test2","applyName":"test2","applyGender":"test2","applyPersonType":"test2","applyEducation":"test2","applyCensusType":null,"applyMarriage":null,"applyPhone":null,"applyHomeAddress":null,"applyPersonPhoto":null,"companyPersonName":null,"companyPersonId":null,"companyName":null,"companyRegion":null,"companyRegId":null,"companyJobNum":null,"companyBusinessScope":null,"companyEmployeeNum":null,"companyEntityType":null,"loanPlanPeriod":null,"loanPlanAmount":null,"loanQualification":null,"loanInterestSubsidy":null,"loanBank":null,"applyDate":null,"auditFailReason":null,"joinAuditFailReason":null,"checkPeopleList":null,"auditAmount":null,"auditPeriod":null,"auditInterestRate":null,"bankBondsman":null,"bankStore":null,"bankAmount":null,"bankPeriod":null,"bankInterestRate":null,"bankRefundDate":null,"bankPostRealRefund":null,"bankPostRealRefundDate":null,"bankPostIsRefound":null,"subsidyAmount":null,"subsidyAccount":null,"overdueAmount":null,"overdueRefoundAmount":null,"overdueRefoundDate":null,"blacklistReason":null,"blacklistDate":null,"compensationSet":null,"compensationAmount":null,"compensationDate":null,"legisSet":null,"legisAmount":null,"legisDate":null}]}

```

### 9 dev usage

```bash
1. checkout this branch
2. maven --> update project
3. add plugin myBatis generator in IDE
3. add new mysql tables and interfaces:
   (1)Config the generatorConfig.xml (all the configrations have been completed), only need to config the table you want to add, eg:
   
   <table schema="RestServiceDB" tableName="loan_transaction" domainObjectName="PersonalLoanTransactionBean"></table>
   schema is the database name, tableName is the table name, domainObjectName will be the Pojo name produced automatically by myBatis generator;
	
   (2)Run as --> Run configrations -->MyBatis Generator -->(run the generatorConfig.xml)
      after this, there will be a xml file produced in src/main/resources/mapper/PersonalLoanTransactionBeanMapper.xml; and 2 pojo produced under src/main/java/smallloanplatform/beans/daobeans/ PersonalLoanTransactionBean.java and PersonalLoanTransactionBeanExample.java; and 1 mapper produced under src/main/java/smallloanplatform/dao/mybatismapper/PersonalLoanTransactionBeanMapper.java;
	  
	  all the generator work finished.
	  
   (3)then write the logic code:
      eg:create a new package under controller: personalloancontroller, and add a new class PersonalLoanController.java (extends AbstractController), mark it as @RestController;
	     then autowire these three beans:
		 @Autowired
         private TokenService tokenService;
         @Autowired
         private PersonalLoanTransactionBeanMapper personalLoanTransactionBeanMapper; (produced automatically by the myBatis generator)
         @Autowired
         private PersonalLoanTransactionBeanExample personalLoanTransactionBeanExample; (produced automatically by the myBatis generator)
		 
		 use the function under mapper to do the database operations, eg: personalLoanTransactionBeanMapper.selectByExample(personalLoanTransactionBeanExample);
```

### 10 Personal loan interfaces  9-16 steps:

```bash
9 Commit Bank Result  
POST
https://localhost/user/commitbankresult?resttoken=129c621c-bb71-4924-ad53-dc6b4fee7eb6_WWFuZw==
json:
{"tid": "10000003", 
"bankBondsman": "担保人田七", 
"bankAmount": 150000, 
"bankPeriod": 1, 
"bankInterestRate": 0.35}

response:
{
    "code": 200,
    "total": 0,
    "msg": "commit bank result success!",
    "data": "commit bank result success!"
}

10 Commit Normal Refund
POST
https://localhost/user/commitnormalrefund?resttoken=129c621c-bb71-4924-ad53-dc6b4fee7eb6_WWFuZw==
json:
{"tid": "10000003", 
"bankPostRealRefund": 100000}
response:
{
    "code": 200,
    "total": 0,
    "msg": "commit normal refund success!",
    "data": "commit normal refund success!"
}

11 Commit Subsidy
POST
https://localhost/user/commitsubsidy?resttoken=129c621c-bb71-4924-ad53-dc6b4fee7eb6_WWFuZw==
json:
{"tid": "10000003", 
"subsidyAmount": 3500
}

response:
{
    "code": 200,
    "total": 0,
    "msg": "commit subsidy success!",
    "data": "commit subsidy success!"
}

12 Commit Overdue
POST
https://localhost/user/commitoverdue?resttoken=129c621c-bb71-4924-ad53-dc6b4fee7eb6_WWFuZw==
json:
{"tid": "10000003", 
"overdueAmount": 50000}
response:
{
    "code": 200,
    "total": 0,
    "msg": "commit overdue success!",
    "data": "commit overdue success!"
}

12_1 list all overdue info:
Get
https://localhost/user/listalloverdueinfo?resttoken=129c621c-bb71-4924-ad53-dc6b4fee7eb6_WWFuZw==




13 Commit Overdue Refund
POST
https://localhost/user/commitoverduerefund?resttoken=129c621c-bb71-4924-ad53-dc6b4fee7eb6_WWFuZw==
json
{"tid": "10000003", 
"overdueRefoundAmount": 40000}
response:
{
    "code": 200,
    "total": 0,
    "msg": "commit overdue refund success!",
    "data": "commit overdue refund success!"
}

14 Commit Blacklist
POST
https://localhost/user/commitblacklist?resttoken=129c621c-bb71-4924-ad53-dc6b4fee7eb6_WWFuZw==
json
{"tid": "10000003", 
"blacklistReason": "拒绝还钱！！"}
response:
{
    "code": 200,
    "total": 0,
    "msg": "commit blacklist success!",
    "data": "commit blacklist success!"
}

15 Commit Compensation
POST
https://localhost/user/commitcompensate?resttoken=129c621c-bb71-4924-ad53-dc6b4fee7eb6_WWFuZw==
json
{"tid": "10000003", 
"compensationAmount": 45000}
response:
{
    "code": 200,
    "total": 0,
    "msg": "commit compensate success!",
    "data": "commit compensate success!"
}



15_2 Init Compensation
POST
http://localhost:8081/user/initcompensate?resttoken=7a667e80-8df5-4abb-8a19-045b40f8681e_WWFuZw==
json
{
    "total": 1000000, 
    "remain": 35000,
    "adminRegion": "区域1",
    "fundName": "基金1",
    "fundBank": "银行A"
}
response:
{
    "code": 20000,
    "total": 0,
    "msg": "init compensation fund success",
    "data": "init compensation fund success"
}


16 Commit Legis Result
POST
https://localhost/user/commitlegis?resttoken=129c621c-bb71-4924-ad53-dc6b4fee7eb6_WWFuZw==
json
{"tid": "10000003", 
"legisAmount": 35000}
response:
{
    "code": 200,
    "total": 0,
    "msg": "commit legis success!",
    "data": "commit legis success!"
}



17 查询调查信息
https://localhost/user/getinvestigationinfo?resttoken=85738480-a3c0-473c-9c2c-b91ff62fb787_Um9nZXI=
Post
{"tid": "10000003"}
response:
{
    "code": 200,
    "total": 0,
    "msg": "query success",
    "data": [
        {
            "tid": 1,
            "loanId": 10000003,
            "investigationResult": "实体考察合格。。。。经营场地500平。。",
            "investigationDate": null,
            "revenue": null,
            "stockValue": null,
            "bankDeposit": null,
            "registration": null,
            "businessProspect": null,
            "fixedFunds": null,
            "fundDemand": null,
            "fundUsage": null,
            "laborCharge": null,
            "vehicleValue": null,
            "employeeNum": null,
            "regisDate": null,
            "leasePeriod": null,
            "selfFunds": null,
            "liquidityFunds": null,
            "otherCharge": null,
            "equipValue": null,
            "placeSource": null,
            "placeArea": null,
            "annualProfit": null,
            "annualSales": null,
            "adminRegion": null,
            "gmtModified": null,
            "gmtCreated": null
        }
    ]
}

18 查询基金信息
https://localhost/user/getcompensationfundbean?resttoken=85738480-a3c0-473c-9c2c-b91ff62fb787_Um9nZXI=
Post
{"fundId": "10000003"}
response
{
    "code": 200,
    "total": 0,
    "msg": "query success",
    "data": []
}



```



alter table user_role modify role varchar(40);

update user_role set role=concat('ROLE_',role);




20190615 会议记录：
1.受理登记加一下图片上传功能  扫描上传

2.受理登记时确定好银行

3.审核未通过的退回前台继续
  前台只能查看自己提交的审核信息，不能看其他人受理的
  
4.分配调查任务的人员录入，分别录入 ？ 一方录入？

5.用户管理 属于哪家银行？ 属于哪个县市区？ 12个县市区
   区域 和 机构

6.考察情况管理，核实情况 放最下面，框放大一点

7.联合待审，加个字段：与会参加人员

8. 银行审批管理中，前面已经确定银行，这里只填银行网点
    按钮 ： 分配-->提交

9.展期功能， 第一年正常还完后，增加一年
  可以在正常还款后录入是否需要展期

10.还款管理中 正常还款框里加1个图片上传框，上传银行流水

11.贴息管理 填写信息框中加一个展示上面10的照片

12.基金 分县市区， 总额 余额 银行，每个区县都有自己的基金库

13.司法追缴 追缴金额要返回到基金池里（分县市区）

14.统计

大表：增加字段：录入人的用户id，录入人的用户名
增加：银行网点 (edited)
增加：调查人员id，调查人员名称
增加：审核人员id，审核人员名称
增加：银行审批人员id，名称
各个步骤都增加操作用户的id和名称
增加页面：申请状态查询，只能前台人员看到 (edited)
申请台账审核：详情下拉菜单增加更多字段
申请台账审核：不用列出全部状态，只是当前状态
调查任务管理：分配人员，担保中心和银行各一个，银行只到区县一级，不用到网点
调查任务管理：由社保中心的人录入
考察情况管理：银行人员可以修改，担保人中心人员只能查看
增加组织目录管理页面：用于管理人员，增加行政区域（一级），机构（二级）
贴息：增加公式
贴息管理和基金管理：优先级降低，参考省里系统
逾期：3个月自动加入黑名单
统计信息：周一发送一个例子，两个纬度，统计聚合粒度和统计范围
一个季度回访一次：归到考察情况管理
用户管理：增加姓名和身份证号





角色和之前一样，不需要改变：
#前台受理人员(银行): bank_reception_user
#后台复核人员: super_check_user
#担保中心主任: super_admin_user
#调查人员（银行/社保): super_or_bank_investi_user
#银行后台人员: bank_admin_user
#管理员：super_admin_admin




task 20190617-0621:
Will:
数据库更新，新增字段；
审核未通过的退回前台，受理登记菜单中加一个查询自己处理过的记录的页面
联合待审，加个字段：与会参加人员
银行审批管理中，前面已经确定银行，这里只填银行网点 按钮 ： 分配-->提交
调查任务管理：分配人员，担保中心和银行各一个，银行只到区县一级，不用到网点
基金 分县市区， 总额 余额 银行，每个区县都有自己的基金库（贴息管理和基金管理：优先级降低，参考省里系统）
司法追缴 追缴金额要返回到基金池里（分县市区）
逾期：3个月自动加入黑名单，借款人、担保人要同时入黑名单 （再加一个黑名单查询页面，可以查看/删除黑名单）
贷款申请时做校验： 贷款期间不能给别人做担保，担保期间不能自己申请贷款，是否在黑名单里，名字身份证号码校验申请次数不能超过3次


Dada:
图片上传/展示功能完善（贴息管理 填写信息框中加一个展示上面10的照片） 
受理登记时确定好银行 (后续步骤置灰不允许改)
考察情况管理，核实情况 放最下面，框放大一点
还款管理中 正常还款框里加1个图片上传框，上传银行流水
各个步骤都增加操作用户的id和名称，每一步都要留痕迹要只能是操作的（可能要修改一下数据库来记录一下每一步谁操作的）
一个季度回访一次（银行审批管理与还款管理直接加一个页面：贷后管理，调查人员可以随时新加或更新回访的调查报告： 每一次更新贷后调查报告时加个下拉列表选项，是否走到下一步，不走下一步的表示后面还会继续有调查报告）
组织机构管理，树形结构展示。 要动态增减，前面所有流程用到的组织机构包括银行网点，都要从这里更新的信息中读取然后以下列列表形式给用户选；


Roger:
用户管理完善 属于哪家银行？ 属于哪个县市区？ 12个县市区
  区域 和 机构  树形结构展示（增加组织目录管理页面：用于管理人员，增加行政区域（一级），机构（二级））
用户管理：增加姓名和身份证号
菜单的可见性权限控制（当前的各种角色是系统的，比如一个县区 或者 市里的都有一套用户，分别具有我们定义的5中角色。 有个超级管理员用户可以查看所有的）
super_or_bank_investi_user 拆分成两个，社保中心调查人员和银行调查人员，并更新系统中已有逻辑-----不用拆分了，还是一个角色，但是分配时只能分配社保中心的人，社保中心的只作为一个挂名，银行的去更新，只要到了这一步所有的银行调查人员都可以看到；
展期功能， 第一年正常还完后，增加一年，可以在正常还款后录入是否需要展期
----分配调查任务的人员录入, 银行人员来提交报告，社保中心的只查看（考察情况管理那一页，银行的人是"填写考察报告"，社保中心的人是"查看考察报告"）
申请台账审核：不用列出全部状态，只是当前状态
一个季度回访一次（银行审批管理与还款管理直接加一个页面：贷后管理，调查人员可以随时新加或更新回访的调查报告： 每一次更新贷后调查报告时加个下拉列表选项，是否走到下一步，不走下一步的表示后面还会继续有调查报告） @Roger  这个要不你有空也看下？我还不太清楚什么意思


先不做：
申请台账审核：详情下拉菜单增加更多字段
贴息：增加公式
统计
统计信息：周一发送一个例子，两个纬度，统计聚合粒度和统计范围






展示之前要修改的： 
2. 统一图标； Will done
3. 目录层级，一个自类别的也放到二级（看下耗时的话先放后）；   达达
4. 银行放款，担保人信息，身份证，姓名，工作单位，联系方式，提交时交验下担保人；  Will done
5. 是否正在贷款，是否已经有担保，是否在黑名单 （与申请登记查询一样） Will done
6. 核实日期改成日期选择；   Will done
7. 回访管理，查看页面，去掉审贷意见；  Roger   (done)
8. 确认展期后的申请日期，得是新的申请日期               Roger (done)
9. 银行发放管理，增加是否展期列    Roger (done)
10.贷款贴息页面，还款证明图片没有展示出来；  Dada
11.如果是展期，银行放款情况里，内容与原始记录一样，只有日期变更   Roger
12.组织机构，只有银行，再加网点一级 （每一级要增加删除）新增也要加上行政代码；   Dada
13.用户管理增加删除； Roger(done)





机构管理
 1.页面跳转的完善+删除(done)
 2.市值添加后出现了机构类型(done)
 3.页面左侧当前机构列表默认只展示第一级的内容，子菜单默认收起(done)
 4.右侧列表上方加个横线，上面也写个标题："机构列表" (done)
 5.左边栏不能动态刷新

用户管理
 1.取机构的数据 (done)
 2.先选行政区域，再接着选所属机构 (done)
 alter table user_role modify role VARCHAR(200);

联合会审管理
 1.取不到数据。。。 (done)

个体申请登记
 1.人员类型做成下拉列表(done-mock)
 2.所属行政区域也是取列表(done)
 3.行业分类也是数据字典(done-mock)
 4.
受理登记查询
 1.修改时，不能出现审核意见  （done）
 2.图片没展示出来(done)
 3.图片能增删改(done)
 4.修改图标展示，但disable(done)
贷款初审管理
 1.图片被切割了，要看全或放大(done)
 2.
任务分配管理
 1.人员只能看到细分的部分人 （应该是只能社保中心的人）

回访管理
 1.不能出现考察的情况 (done)
 2.改成回访图片(done)
 3.考察和回访的图片混掉了(done)
 4.回访列表里，不要出现考察的记录 (done)
 5.只保留上传图片和核实情况 (done)
 6.申请贷款编号，37080020190619001，区县+日期
 7.改成填写回访情况 (done)

还款管理
 1.还款金额，自动去放贷金额（银行放款的放贷金额）
 2.点了展期 之后，贴息管理里面没有图片了(done)

贴息管理
 1.删除贴息搜款人账户 (done)

逾期
 1.逾期金额自动获取,金额不能修改 （done）

基金代偿
 1.列表页增加逾期金额，已还金额，剩余逾期金额 will done

担保基金管理
 1.银行也是下拉列表 done
 2.基金代码更新 ??
 3.列表页增加更多列 ??

黑名单管理
 1.加因为哪个贷款加入黑名单 will done

逾期三个月自动加入黑名单 done will


放贷逾期管理 done will
* 增加逾期还款按钮，可以用于在加入黑名单前，进行部分还款 done will
* 增加当前逾期金额列 done will


其他
 1.上传的头像，现在还没展示(done)
 2.每一步的不通过测试
 3.跳转问题： 如果重新登录后进入的是一个需要tid的子页面，会提示tid不能为空，这时要自动跳到它的上一级菜单
 4.增加多角色支持 (done)
 
 
tasks:
dada:
图片和机构管理相关的问题以及页面加类型校验或者提示

Roger:
用户管理、回访管理、展期、贴息、逾期 相关的问题

Will:
个体申请登记、基金、黑名单、还款管理、任务分配相关的问题

# 黑名单自动更新所需的mysql存储过程

```sql
SET GLOBAL event_scheduler = 1;

CREATE PROCEDURE updateblacklist ()
BEGIN
    UPDATE loan_transaction SET blacklist_reason = '逾期3个月',
                                blacklist_date = NOW(),
                                current_step = 14
    WHERE DATEDIFF(NOW(), bank_refund_date) >= 90 and current_step = 12;
END;

CREATE EVENT IF NOT EXISTS check_blacklist_by_day
    ON SCHEDULE EVERY 86400 SECOND
    ON COMPLETION PRESERVE
    DO CALL updateblacklist();


ALTER TABLE apply_user
    ADD COLUMN blacklist_loan_id BIGINT;
```


# 20190630 update

Will:

贷款还款管理->还款金额里面自动写出来, 且不让改 done

展期后银行放贷管理，放款信息需要加载原始贷款的记录，且担保人校验要做特殊处理 done

所有流程结束的，状态改成已完成 done

存储过程加入readme done

贷款逾期管理—》逾期结算，自动列出金额，不能修改 done

基金代偿管理，自动列出金额，不能修改，基金编号改成列出所在行政区域的基金银行

司法追缴时，返还到之前扣除的基金里面，自动显示，不要手选


Dada:

个人申请登记: 如果提交失败，提交按钮就不能再点 (done)

考察情况管理 提交后 提交到考察情况管理列表页 (done)

银行发放管理->填写放款信息的银行网点要变成下拉框，根据所在行政区域和机构，从institute里找到网点列表 (已经改过了)

贴息管理页面，点击图片后，图片会跑到后面去 (done)

loan表增加一个用于显示的id列

申请页面的行政区域和机构要对应起来（其他的检查下。。） (加了个mock的后端)


Roger:

页面提交完后，跳转目的地确认

必填项确认

某些列的单位确认

展期的记录，在填写回访，申请人信息，头像图片，申请登记的图片，没有显示 (done)

展期的记录，填写回访，提交时会失败 (done)

loan 表里只存行政区域id，机构id，网点id (done)

放款时“填写放款情况”， 银行网点 要从下拉列表选（机构管理中加的那些里选），放款利息校验（提示输入的格式） (done)

回访情况混乱  （done）

页面跳转整改 （done）



其他：

dada:

1.个人申请登记，只显示用户的行政区域-done(但是用名称取得)

2.增加基本搜索功能 - 部分完成

3.列表页增加分页 - 部分完成

4.机构操作用户体验更新 - 暂不调整了

Will:

基金代偿管理，自动列出金额，不能修改，基金编号改成列出所在行政区域所创建的所有基金done

担保基金管理增加删除 done

基金名称改为自动生成: 行政区域+基金银行 done

基金管理的行政区域和基金银行也要从后台拉取 done

个人申请登记时，增加所在行政区域 done

司法追缴时，返还到之前扣除的基金里面，自动显示，不要手选 done


Roger:

loan表增加一个用于显示的id列   （done）

任务分配只能分配社保中心的调查人员，不能把所有用户都列出来(只展示本条记录所在区域的 社保中心的 角色包含调查人员的)  （done）

展期的记录不能继续展期 （done）

用户管理存储加一个地区ID (done)
alter table users add column admin_region_id bigint(11);
alter table users add column institution_id bigint(11);




角色(role):

```bash
#前台受理人员(银行): bank_reception_user
#后台复核人员: super_check_user
#担保中心主任: super_admin_user
#调查人员（银行/社保): super_or_bank_investi_user
#银行后台人员: bank_admin_user
#管理员: system_admin
#超级管理员: super_system_admin
```





把list统一掉
1.搜索增加更多条件，搜索+分页剩余页面完成
2.listRegion更新为根据id查询3
3.菜单权限增加
4.每个role建立一个用户，模拟实际场景进行测试





07-07遗留问题：
13.investigateTaskList  list user有权限问题，拿不到用户。即使admin看到的用户，也不是当前区县的
9.新加用户后，未出现“提交成功”的提示(done)
14.待确认-考察情况管理中，鱼台县邮政银行的，可以看到和操作工商银行的记录，是否细化到银行？ (done)
19.编辑"da_houtai用户时，还是出现"“For input string: "鱼台县"，应该用ID？(done)
4.添加用户的页面，没有校验

20.用户后端查询接口，支持更多条件，以支持分页和搜索(done)


1.个人遗留问题检查及修改+统计查询部分；  dada
2.企业流程  Will
3.小微流程  Roger

//0: 个人；  1：企业； 2：小微
alter table loan_transaction add column record_type int(11);




增加企业；（done）
增加小微；（done）
字段用的个人的，字段需调整；

问题：
1. 创建了鱼台县的管理员，管理员登录后仍可以看到其他县市区的基金
2. 黑名单中鱼台县的管理员也可以看到其他县市区的黑名单
3. 县市区管理员在添加用户时行政区域应默认显示本县市区，不需要再在下拉列表选县市区 （done）
4. 受理登记页面，区域默认当前受理人的区域没问题了，但是银行是不是也要默认自己银行？待确认。。。
5. 申请页面提交时没有反应，但实际已经写入数据库（我这边的网络问题？？ 待确认。。。）
6. 调查人员看不到列表页内容（done）
7. 联合会审页面，填利率的地方还是没有校验，也没有格式提示
8. 贷款回访，用调查人员角色登录后提示没有权限 （done）
9. 点了正常还款后，默认会显示上一次的日期和图片 
10.展期后的回访时的调查列表，字段显示缺失 (done)
11.展期后的回访时的图片会把第一次（非展期的）的图片也一块显示出来(done)
12.ID改成日期格式




Will:
创建了鱼台县的管理员，管理员登录后仍可以看到其他县市区的基金
黑名单中鱼台县的管理员也可以看到其他县市区的黑名单
逾期还款中，如果只用部分还款，则状态不能进入到下一步。即逾期金额变为0时，点击“逾期还款”会报错；点“部分逾期还款”，状态也不能进入到下一步。且overdueRefoundAmount是null
企业流程登记页面参照省系统加一下字段（小微和企业先保持一致）

Roger:
用户管理未记录adminRegionId？
编辑用户时，已有角色的复选框不能自动勾选
编辑用户时，行政区域是使用名称关联的？还有所属机构不能自动展示
联合会审页面，填利率的地方还是没有校验，也没有格式提示
点了正常还款后，默认会显示上一次的日期和图片 
点了正常还款后，默认会显示上一次的日期和图片


待确认：
受理登记页面，区域默认当前受理人的区域没问题了，但是银行是不是也要默认自己银行？待确认。。。
申请页面提交时没有反应，但实际已经写入数据库（我这边的网络问题？？ 待确认。。。）
ID改成日期格式




周末要完成的：
dada:
小微以企业为申请人,小微没有申请人，参考省里的系统,根据社会统一信用贷号码来判断申请了几次,小微，企业，按照省系统的来就行，删掉申请人
上传pdf,pdf用于企业上传合同,只有申请时传pdf（只在申请页面上传就可以了，其他的再有权限需要查看的地方有个链接可以点开查看上传的pdf）
填完申请后需要又打印功能(加个打印按钮应该就可以了，格式宋健说提供一个模板？？)----时间来不及的话这个也可以先不做，可能需要导出的模板？

Roger:
增加担保类型，是个人担保，还是资产担保，2-3种类型,下拉列表选哪种然后填对应的担保信息
在银行放款管理里面,增加分隔符，分成2个区域：担保信息，银行信息,为了方便看
贷款编号，根据日期，自动生成编号,编号：行政区号+日期+xxxx

Will:
显示出上一步的受理人,谁填了回访记录，谁填了调查记录，都是一定要记录的
逾期部分还款不能多还,做个小于逾期金额
司法追缴也做个小于基金代偿,如果没有达到基金代偿总额，就一直留在司法追缴里面


先不做的：
跟其他系统对接的接口，养老，社保信息，只是看
身份证读卡功能
调用其他系统的校验
还需要拍照


20190730：
1.个人 6年3次 校验？
2.企业 3次校验

问题：
1. 用户管理中，编辑用户时角色要默认选中已有的角色；
4. 是否劳动密集型小企业  1--> 是否
5. 还款管理中点“逾期”--》逾期金额默认是上一次操作的
6. ”大于0，且小于最大逾期金额“  输入正常也会显示红色警告， 逾期管理
8. 司法追缴 提交的时候报错：
### Error updating database. Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry '1' for key 'PRIMARY'
 ### The error may involve smallloanplatform.dao.mybatismapper.CompensationFundBeanMapper.updateByExampleSelective-Inline
 ### The error occurred while setting parameters
 ### SQL: update compensation_fund SET fund_id = ?, total = ?, remain = ?, admin_region = ?, fund_name = ?, fund_bank = ? WHERE ( admin_region = ? )
 ### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry '1' for key 'PRIMARY' ; ]; Duplicate entry '1' for key 'PRIMARY'; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry '1' for key 'PRIMARY'
 



1.客户端 首页 背景图片替换个漂亮的；
客户端进到里面的某个按钮后 左上角的链接不要显示，加个返回按钮（类似那种银行自助机一样的，不能有网页元素的链接在页面上）
查询的信息用web中列表页的，现在展示的列太少；
2.web系统中 “流程信息查询”， 当前到哪一步了就展示到哪一步；
3.首页的图等宋健选一个然后我们首页只放他选的那个； 其他的挪到后面的统计信息里去
4.身份证读卡器、高拍仪接入
5.APP（进度查询、政策展示、申请）
6.系统详细测试，不要有bug






********************************************************************************************************************************************
原有表字段：
mysql> desc loan_transaction;
+-----------------------------------+--------------+------+-----+---------+----------------+
| Field                             | Type         | Null | Key | Default | Extra          |
+-----------------------------------+--------------+------+-----+---------+----------------+
| tid                               | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| apply_person_id                   | varchar(30)  | YES  |     | NULL    |                |
| apply_name                        | varchar(30)  | YES  |     | NULL    |                |
| apply_gender                      | varchar(10)  | YES  |     | NULL    |                |
| apply_person_type                 | varchar(500) | YES  |     | NULL    |                |
| apply_education                   | varchar(30)  | YES  |     | NULL    |                |
| apply_census_type                 | varchar(30)  | YES  |     | NULL    |                |
| apply_marriage                    | varchar(30)  | YES  |     | NULL    |                |
| apply_phone                       | varchar(30)  | YES  |     | NULL    |                |
| apply_home_address                | varchar(50)  | YES  |     | NULL    |                |
| apply_person_photo                | varchar(50)  | YES  |     | NULL    |                |
| company_person_name               | varchar(30)  | YES  |     | NULL    |                |
| company_person_id                 | varchar(30)  | YES  |     | NULL    |                |
| company_name                      | varchar(50)  | YES  |     | NULL    |                |
| company_region                    | varchar(30)  | YES  |     | NULL    |                |
| company_reg_id                    | varchar(50)  | YES  |     | NULL    |                |
| company_job_num                   | int(11)      | YES  |     | NULL    |                |
| company_business_scope            | varchar(150) | YES  |     | NULL    |                |
| company_employee_num              | int(11)      | YES  |     | NULL    |                |
| company_entity_type               | varchar(30)  | YES  |     | NULL    |                |
| loan_plan_period                  | int(11)      | YES  |     | NULL    |                |
| loan_plan_amount                  | int(11)      | YES  |     | NULL    |                |
| loan_qualification                | varchar(30)  | YES  |     | NULL    |                |
| loan_interest_subsidy             | varchar(30)  | YES  |     | NULL    |                |
| loan_bank                         | varchar(30)  | YES  |     | NULL    |                |
| apply_date                        | datetime     | YES  |     | NULL    |                |
| audit_fail_reason                 | varchar(200) | YES  |     | NULL    |                |
| join_audit_fail_reason            | varchar(200) | YES  |     | NULL    |                |
| check_people_list                 | varchar(50)  | YES  |     | NULL    |                |
| audit_amount                      | int(11)      | YES  |     | NULL    |                |
| audit_period                      | int(11)      | YES  |     | NULL    |                |
| audit_interest_rate               | float        | YES  |     | NULL    |                |
| bank_bondsman                     | varchar(200) | YES  |     | NULL    |                |
| bank_store                        | varchar(50)  | YES  |     | NULL    |                |
| bank_amount                       | int(11)      | YES  |     | NULL    |                |
| bank_period                       | int(11)      | YES  |     | NULL    |                |
| bank_interest_rate                | float        | YES  |     | NULL    |                |
| bank_refund_date                  | datetime     | YES  |     | NULL    |                |
| bank_post_real_refund             | int(11)      | YES  |     | NULL    |                |
| bank_post_real_refund_date        | datetime     | YES  |     | NULL    |                |
| bank_post_is_refound              | int(11)      | YES  |     | NULL    |                |
| subsidy_amount                    | int(11)      | YES  |     | NULL    |                |
| subsidy_account                   | varchar(200) | YES  |     | NULL    |                |
| overdue_amount                    | int(11)      | YES  |     | NULL    |                |
| overdue_refound_amount            | int(11)      | YES  |     | NULL    |                |
| overdue_refound_date              | datetime     | YES  |     | NULL    |                |
| blacklist_reason                  | varchar(200) | YES  |     | NULL    |                |
| blacklist_date                    | datetime     | YES  |     | NULL    |                |
| compensation_set                  | int(11)      | YES  |     | NULL    |                |
| compensation_amount               | int(11)      | YES  |     | NULL    |                |
| compensation_date                 | datetime     | YES  |     | NULL    |                |
| legis_set                         | int(11)      | YES  |     | NULL    |                |
| legis_amount                      | int(11)      | YES  |     | NULL    |                |
| legis_date                        | datetime     | YES  |     | NULL    |                |
| admin_region                      | varchar(30)  | YES  |     | NULL    |                |
| gmt_created                       | datetime     | YES  |     | NULL    |                |
| gmt_modified                      | datetime     | YES  |     | NULL    |                |
| current_step                      | varchar(30)  | YES  |     | NULL    |                |
| loan_type                         | varchar(30)  | YES  |     | NULL    |                |
| trace_apply_person                | varchar(30)  | YES  |     | NULL    |                |
| trace_review_person               | varchar(30)  | YES  |     | NULL    |                |
| trace_schedule_person             | varchar(30)  | YES  |     | NULL    |                |
| trace_input_audit_person          | varchar(30)  | YES  |     | NULL    |                |
| trace_join_review_person          | varchar(30)  | YES  |     | NULL    |                |
| trace_bank_review_person          | varchar(30)  | YES  |     | NULL    |                |
| trace_refund_person               | varchar(30)  | YES  |     | NULL    |                |
| trace_overdue_person              | varchar(30)  | YES  |     | NULL    |                |
| trace_compensate_person           | varchar(30)  | YES  |     | NULL    |                |
| trace_legit_person                | varchar(30)  | YES  |     | NULL    |                |
| trace_subsidy_person              | varchar(30)  | YES  |     | NULL    |                |
| audit_join_meeting_people         | varchar(100) | YES  |     | NULL    |                |
| trace_apply_date                  | datetime     | YES  |     | NULL    |                |
| trace_review_date                 | datetime     | YES  |     | NULL    |                |
| trace_schedule_date               | datetime     | YES  |     | NULL    |                |
| trace_input_audit_date            | datetime     | YES  |     | NULL    |                |
| trace_join_review_date            | datetime     | YES  |     | NULL    |                |
| trace_bank_review_date            | datetime     | YES  |     | NULL    |                |
| trace_refund_date                 | datetime     | YES  |     | NULL    |                |
| trace_overdue_date                | datetime     | YES  |     | NULL    |                |
| trace_compensate_date             | datetime     | YES  |     | NULL    |                |
| trace_legit_date                  | datetime     | YES  |     | NULL    |                |
| trace_subsidy_date                | datetime     | YES  |     | NULL    |                |
| extended_original_tid             | bigint(20)   | YES  |     | NULL    |                |
| compensation_fund_id              | bigint(20)   | YES  |     | NULL    |                |
| bank_bondsman_id                  | varchar(30)  | YES  |     | NULL    |                |
| bank_bondsman_contact             | varchar(30)  | YES  |     | NULL    |                |
| bank_bondsman_work                | varchar(100) | YES  |     | NULL    |                |
| record_type                       | int(11)      | YES  |     | NULL    |                |
| bond_type                         | int(11)      | YES  |     | NULL    |                |
| property_type                     | varchar(50)  | YES  |     | NULL    |                |
| property_value                    | int(11)      | YES  |     | NULL    |                |
| property_details                  | varchar(200) | YES  |     | NULL    |                |
| company_social_loan_id            | varchar(30)  | YES  |     | NULL    |                |
| company_person_gender             | varchar(30)  | YES  |     | NULL    |                |
| company_address                   | varchar(100) | YES  |     | NULL    |                |
| company_area                      | varchar(100) | YES  |     | NULL    |                |
| company_tax_id                    | varchar(100) | YES  |     | NULL    |                |
| company_phone                     | varchar(100) | YES  |     | NULL    |                |
| company_open_date                 | datetime     | YES  |     | NULL    |                |
| company_fixed_asset               | varchar(100) | YES  |     | NULL    |                |
| company_liquid_asset              | varchar(100) | YES  |     | NULL    |                |
| company_liability_other           | varchar(100) | YES  |     | NULL    |                |
| company_liability_borrow          | varchar(100) | YES  |     | NULL    |                |
| company_new_hire_num              | int(11)      | YES  |     | NULL    |                |
| company_hire_social_insurance_num | int(11)      | YES  |     | NULL    |                |
| company_hire_unemployment_num     | int(11)      | YES  |     | NULL    |                |
| company_hire_graduate_num         | int(11)      | YES  |     | NULL    |                |
| company_is_labour                 | int(11)      | YES  |     | NULL    |                |
| company_purpose                   | varchar(200) | YES  |     | NULL    |                |
| loan_id_show                      | varchar(50)  | YES  |     | NULL    |                |
| subsidy_rate                      | float        | YES  |     | NULL    |                |
| bank_settlement_date              | datetime     | YES  |     | NULL    |                |
+-----------------------------------+--------------+------+-----+---------+----------------+

新增字段和表：
个人登记需新增：
户籍地址、住宅权属（下拉框（自有、租赁）） g_huji_address,g_zhuzhaiquanshu
配偶姓名、身份证号码、联系电话；（非必填项）g_partner_name, g_partner_id, g_partner_phone
经营类型（下拉框（个体工商户、小微企业、民办非企业单位、其他（请注明）：XXXX）） g_jingyingtype
年营业收入、经营地址、经营范围 g_nianincome, g_jingyingaddress, g_jingying_fanwei
银行类型（下拉框[国有商业银行、城市商业银行、农商银行、村镇银行、其他]） g_banktype
小微新增：
经办人姓名、经办人身份证号码、经办人联系电话 x_jingbanren_name, x_jingbanren_id, x_jingbanren_phone

alter table loan_transaction add column g_huji_address varchar(200), add column g_zhuzhaiquanshu varchar(30), add column g_partner_name varchar(30), add column g_partner_id varchar(30),add column g_partner_phone varchar(30),add column g_jingyingtype varchar(30),add column g_nianincome varchar(30),add column g_jingyingaddress varchar(200),add column g_jingying_fanwei varchar(200),add column g_banktype varchar(30),add column x_jingbanren_name varchar(30),add column x_jingbanren_id varchar(30),add column x_jingbanren_phone varchar(30);

alter table loan_transaction ADD INDEX recruite(loan_id_show);

20190920new:
中征码 bank_zhongzhengcode
基准利率 bank_jizhunlilv
实际利率 bank_shijililv

实际还款金额 bank_refundamount
还款本金金额 bank_refundbenjin
还款利息金额 bank_refundlixiamount

放贷利率 subsidy_fangdaililv
中央承担金额 subsidy_zhongyangjine
省级承担金额 subsidy_shengjijine
市级承担金额 subsidy_shijijine
县级承担金额 subsidy_xianjijine
中央贴息金额 subsidy_zhongyangtiexi
省级贴息金额 subsidy_shengjitiexi
市级贴息金额 subsidy_shijitiexi
县级贴息金额 subsidy_xianjitiexi
实际贴息总金额 subsidy_totaltiexi
中央承担比例 subsidy_zhongyangratio
省级承担比例 subsidy_shengjiratio
市级承担比例 subsidy_shijiratio
县级承担比例 subsidy_xianjiratio
贴息天数 subsidy_totaldays


新加带动就业人员信息表：
姓名、身份证号码、人员类别
新加当年新招符合条件人员信息表：
姓名、身份证号码、人员类别（下拉框）、劳动合同期限、新增缴纳社会保险起始时间
newrecruite
loan_id_show, name, id, type, contractperiod, insurancestartdate

create table newrecruite (loan_id_show varchar(50) default NULL, name varchar(30), id varchar(30) default NULL, type varchar(30), contractperiod varchar(30), insurancestartdate datetime);
alter table newrecruite drop primary key;
alter table newrecruite add primary key(loan_id_show,id);
ALTER TABLE newrecruite ALTER COLUMN loan_id_show SET DEFAULT NULL;




银行放贷中的 放贷利息 --> 改为  改成基准利率和实际利率两个字段
从放完款后的流程，都同时展示申贷期限、申贷金额、放贷期限、放贷金额吧，一目了然，现在都是展示的申贷信息

alter table loan_transaction add column bank_zhongzhengcode varchar(30),add column bank_jizhunlilv varchar(30),add column bank_shijililv varchar(30),add column bank_refundamount int(11),add column bank_refundbenjin int(11),add column bank_refundlixiamount int(11),add column subsidy_fangdaililv varchar(30),add column subsidy_zhongjiangjine int(11),add column subsidy_shengjijine int(11),add column subsidy_shijijine int(11),add column subsidy_zhongyangtiexi int(11),add column subsidy_shengjitiexi int(11),add column subsidy_shijitiexi int(11),add column subsidy_totaltiexi int(11);

alter table loan_transaction add column subsidy_xianjijine int(11) after subsidy_shijijine;
alter table loan_transaction add column subsidy_xianjitiexi int(11) after subsidy_shijitiexi;
alter table loan_transaction change subsidy_zhongjiangjine subsidy_zhongyangjine int(11);
alter table loan_transaction add column subsidy_totaldays int(11);


alter table loan_transaction add column hehuo1_name varchar(30),add column hehuo1_gender varchar(10), add column hehuo1_id varchar(30), add column hehuo2_name varchar(30),add column hehuo2_gender varchar(10), add column hehuo2_id varchar(30), add column hehuo3_name varchar(30),add column hehuo3_gender varchar(10), add column hehuo3_id varchar(30);

alter table loan_transaction add column x_newhiretotalratio varchar(30);

alter table loan_transaction add column subsidy_zhongyangratio varchar(30),add column subsidy_shengjiratio varchar(30),add column subsidy_shijiratio varchar(30),add column subsidy_xianjiratio varchar(30);

alter table investigation modify investigation_result longtext;


小微页面，旧字段信用的：
company_phone companyPhone  --》法人联系电话
companyIsLabour --》是否小微企业
//company_hire_graduate_num   companyHireGraduateNum ---》新招用符合条件人数占职工总数的比例


mysql> desc loan_transaction;
+-----------------------------------+--------------+------+-----+---------+----------------+
| Field                             | Type         | Null | Key | Default | Extra          |
+-----------------------------------+--------------+------+-----+---------+----------------+
| tid                               | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| apply_person_id                   | varchar(30)  | YES  |     | NULL    |                |
| apply_name                        | varchar(30)  | YES  |     | NULL    |                |
| apply_gender                      | varchar(10)  | YES  |     | NULL    |                |
| apply_person_type                 | varchar(500) | YES  |     | NULL    |                |
| apply_education                   | varchar(30)  | YES  |     | NULL    |                |
| apply_census_type                 | varchar(30)  | YES  |     | NULL    |                |
| apply_marriage                    | varchar(30)  | YES  |     | NULL    |                |
| apply_phone                       | varchar(30)  | YES  |     | NULL    |                |
| apply_home_address                | varchar(50)  | YES  |     | NULL    |                |
| apply_person_photo                | varchar(50)  | YES  |     | NULL    |                |
| company_person_name               | varchar(30)  | YES  |     | NULL    |                |
| company_person_id                 | varchar(30)  | YES  |     | NULL    |                |
| company_name                      | varchar(50)  | YES  |     | NULL    |                |
| company_region                    | varchar(30)  | YES  |     | NULL    |                |
| company_reg_id                    | varchar(50)  | YES  |     | NULL    |                |
| company_job_num                   | int(11)      | YES  |     | NULL    |                |
| company_business_scope            | varchar(150) | YES  |     | NULL    |                |
| company_employee_num              | int(11)      | YES  |     | NULL    |                |
| company_entity_type               | varchar(30)  | YES  |     | NULL    |                |
| loan_plan_period                  | int(11)      | YES  |     | NULL    |                |
| loan_plan_amount                  | int(11)      | YES  |     | NULL    |                |
| loan_qualification                | varchar(30)  | YES  |     | NULL    |                |
| loan_interest_subsidy             | varchar(30)  | YES  |     | NULL    |                |
| loan_bank                         | varchar(30)  | YES  |     | NULL    |                |
| apply_date                        | datetime     | YES  |     | NULL    |                |
| audit_fail_reason                 | varchar(200) | YES  |     | NULL    |                |
| join_audit_fail_reason            | varchar(200) | YES  |     | NULL    |                |
| check_people_list                 | varchar(50)  | YES  |     | NULL    |                |
| audit_amount                      | int(11)      | YES  |     | NULL    |                |
| audit_period                      | int(11)      | YES  |     | NULL    |                |
| audit_interest_rate               | float        | YES  |     | NULL    |                |
| bank_bondsman                     | varchar(200) | YES  |     | NULL    |                |
| bank_store                        | varchar(50)  | YES  |     | NULL    |                |
| bank_amount                       | int(11)      | YES  |     | NULL    |                |
| bank_period                       | int(11)      | YES  |     | NULL    |                |
| bank_interest_rate                | float        | YES  |     | NULL    |                |
| bank_refund_date                  | datetime     | YES  |     | NULL    |                |
| bank_post_real_refund             | int(11)      | YES  |     | NULL    |                |
| bank_post_real_refund_date        | datetime     | YES  |     | NULL    |                |
| bank_post_is_refound              | int(11)      | YES  |     | NULL    |                |
| subsidy_amount                    | int(11)      | YES  |     | NULL    |                |
| subsidy_account                   | varchar(200) | YES  |     | NULL    |                |
| overdue_amount                    | int(11)      | YES  |     | NULL    |                |
| overdue_refound_amount            | int(11)      | YES  |     | NULL    |                |
| overdue_refound_date              | datetime     | YES  |     | NULL    |                |
| blacklist_reason                  | varchar(200) | YES  |     | NULL    |                |
| blacklist_date                    | datetime     | YES  |     | NULL    |                |
| compensation_set                  | int(11)      | YES  |     | NULL    |                |
| compensation_amount               | int(11)      | YES  |     | NULL    |                |
| compensation_date                 | datetime     | YES  |     | NULL    |                |
| legis_set                         | int(11)      | YES  |     | NULL    |                |
| legis_amount                      | int(11)      | YES  |     | NULL    |                |
| legis_date                        | datetime     | YES  |     | NULL    |                |
| admin_region                      | varchar(30)  | YES  |     | NULL    |                |
| gmt_created                       | datetime     | YES  |     | NULL    |                |
| gmt_modified                      | datetime     | YES  |     | NULL    |                |
| current_step                      | varchar(30)  | YES  |     | NULL    |                |
| loan_type                         | varchar(30)  | YES  |     | NULL    |                |
| trace_apply_person                | varchar(30)  | YES  |     | NULL    |                |
| trace_review_person               | varchar(30)  | YES  |     | NULL    |                |
| trace_schedule_person             | varchar(30)  | YES  |     | NULL    |                |
| trace_input_audit_person          | varchar(30)  | YES  |     | NULL    |                |
| trace_join_review_person          | varchar(30)  | YES  |     | NULL    |                |
| trace_bank_review_person          | varchar(30)  | YES  |     | NULL    |                |
| trace_refund_person               | varchar(30)  | YES  |     | NULL    |                |
| trace_overdue_person              | varchar(30)  | YES  |     | NULL    |                |
| trace_compensate_person           | varchar(30)  | YES  |     | NULL    |                |
| trace_legit_person                | varchar(30)  | YES  |     | NULL    |                |
| trace_subsidy_person              | varchar(30)  | YES  |     | NULL    |                |
| audit_join_meeting_people         | varchar(100) | YES  |     | NULL    |                |
| trace_apply_date                  | datetime     | YES  |     | NULL    |                |
| trace_review_date                 | datetime     | YES  |     | NULL    |                |
| trace_schedule_date               | datetime     | YES  |     | NULL    |                |
| trace_input_audit_date            | datetime     | YES  |     | NULL    |                |
| trace_join_review_date            | datetime     | YES  |     | NULL    |                |
| trace_bank_review_date            | datetime     | YES  |     | NULL    |                |
| trace_refund_date                 | datetime     | YES  |     | NULL    |                |
| trace_overdue_date                | datetime     | YES  |     | NULL    |                |
| trace_compensate_date             | datetime     | YES  |     | NULL    |                |
| trace_legit_date                  | datetime     | YES  |     | NULL    |                |
| trace_subsidy_date                | datetime     | YES  |     | NULL    |                |
| extended_original_tid             | bigint(20)   | YES  |     | NULL    |                |
| compensation_fund_id              | bigint(20)   | YES  |     | NULL    |                |
| bank_bondsman_id                  | varchar(30)  | YES  |     | NULL    |                |
| bank_bondsman_contact             | varchar(30)  | YES  |     | NULL    |                |
| bank_bondsman_work                | varchar(100) | YES  |     | NULL    |                |
| record_type                       | int(11)      | YES  |     | NULL    |                |
| bond_type                         | int(11)      | YES  |     | NULL    |                |
| property_type                     | varchar(50)  | YES  |     | NULL    |                |
| property_value                    | int(11)      | YES  |     | NULL    |                |
| property_details                  | varchar(200) | YES  |     | NULL    |                |
| company_social_loan_id            | varchar(30)  | YES  |     | NULL    |                |
| company_person_gender             | varchar(30)  | YES  |     | NULL    |                |
| company_address                   | varchar(100) | YES  |     | NULL    |                |
| company_area                      | varchar(100) | YES  |     | NULL    |                |
| company_tax_id                    | varchar(100) | YES  |     | NULL    |                |
| company_phone                     | varchar(100) | YES  |     | NULL    |                |
| company_open_date                 | datetime     | YES  |     | NULL    |                |
| company_fixed_asset               | varchar(100) | YES  |     | NULL    |                |
| company_liquid_asset              | varchar(100) | YES  |     | NULL    |                |
| company_liability_other           | varchar(100) | YES  |     | NULL    |                |
| company_liability_borrow          | varchar(100) | YES  |     | NULL    |                |
| company_new_hire_num              | int(11)      | YES  |     | NULL    |                |
| company_hire_social_insurance_num | int(11)      | YES  |     | NULL    |                |
| company_hire_unemployment_num     | int(11)      | YES  |     | NULL    |                |
| company_hire_graduate_num         | int(11)      | YES  |     | NULL    |                |
| company_is_labour                 | int(11)      | YES  |     | NULL    |                |
| company_purpose                   | varchar(200) | YES  |     | NULL    |                |
| loan_id_show                      | varchar(50)  | YES  | MUL | NULL    |                |
| subsidy_rate                      | float        | YES  |     | NULL    |                |
| bank_settlement_date              | datetime     | YES  |     | NULL    |                |
| g_huji_address                    | varchar(200) | YES  |     | NULL    |                |
| g_zhuzhaiquanshu                  | varchar(30)  | YES  |     | NULL    |                |
| g_partner_name                    | varchar(30)  | YES  |     | NULL    |                |
| g_partner_id                      | varchar(30)  | YES  |     | NULL    |                |
| g_partner_phone                   | varchar(30)  | YES  |     | NULL    |                |
| g_jingyingtype                    | varchar(30)  | YES  |     | NULL    |                |
| g_nianincome                      | varchar(30)  | YES  |     | NULL    |                |
| g_jingyingaddress                 | varchar(200) | YES  |     | NULL    |                |
| g_jingying_fanwei                 | varchar(200) | YES  |     | NULL    |                |
| g_banktype                        | varchar(30)  | YES  |     | NULL    |                |
| x_jingbanren_name                 | varchar(30)  | YES  |     | NULL    |                |
| x_jingbanren_id                   | varchar(30)  | YES  |     | NULL    |                |
| x_jingbanren_phone                | varchar(30)  | YES  |     | NULL    |                |
| bank_zhongzhengcode               | varchar(30)  | YES  |     | NULL    |                |
| bank_jizhunlilv                   | varchar(30)  | YES  |     | NULL    |                |
| bank_shijililv                    | varchar(30)  | YES  |     | NULL    |                |
| bank_refundamount                 | int(11)      | YES  |     | NULL    |                |
| bank_refundbenjin                 | int(11)      | YES  |     | NULL    |                |
| bank_refundlixiamount             | int(11)      | YES  |     | NULL    |                |
| subsidy_fangdaililv               | varchar(30)  | YES  |     | NULL    |                |
| subsidy_zhongyangjine             | int(11)      | YES  |     | NULL    |                |
| subsidy_shengjijine               | int(11)      | YES  |     | NULL    |                |
| subsidy_shijijine                 | int(11)      | YES  |     | NULL    |                |
| subsidy_xianjijine                | int(11)      | YES  |     | NULL    |                |
| subsidy_zhongyangtiexi            | int(11)      | YES  |     | NULL    |                |
| subsidy_shengjitiexi              | int(11)      | YES  |     | NULL    |                |
| subsidy_shijitiexi                | int(11)      | YES  |     | NULL    |                |
| subsidy_xianjitiexi               | int(11)      | YES  |     | NULL    |                |
| subsidy_totaltiexi                | int(11)      | YES  |     | NULL    |                |
| hehuo1_name                       | varchar(30)  | YES  |     | NULL    |                |
| hehuo1_gender                     | varchar(10)  | YES  |     | NULL    |                |
| hehuo1_id                         | varchar(30)  | YES  |     | NULL    |                |
| hehuo2_name                       | varchar(30)  | YES  |     | NULL    |                |
| hehuo2_gender                     | varchar(10)  | YES  |     | NULL    |                |
| hehuo2_id                         | varchar(30)  | YES  |     | NULL    |                |
| hehuo3_name                       | varchar(30)  | YES  |     | NULL    |                |
| hehuo3_gender                     | varchar(10)  | YES  |     | NULL    |                |
| hehuo3_id                         | varchar(30)  | YES  |     | NULL    |                |
| x_newhiretotalratio               | varchar(30)  | YES  |     | NULL    |                |
| subsidy_zhongyangratio            | varchar(30)  | YES  |     | NULL    |                |
| subsidy_shengjiratio              | varchar(30)  | YES  |     | NULL    |                |
| subsidy_shijiratio                | varchar(30)  | YES  |     | NULL    |                |
| subsidy_xianjiratio               | varchar(30)  | YES  |     | NULL    |                |
| subsidy_totaldays                 | int(11)      | YES  |     | NULL    |                |
+-----------------------------------+--------------+------+-----+---------+----------------+
156 rows in set (0.00 sec)





contractperiod
insurancestartdate

subsidyZhongyangtiexi
subsidyShengjitiexi

subsidyTotaldays
subsidyTotaltiexi


问题：
1.小微页面 “新招用符合条件人数占职工总数的比例” 加校验 （输入 百分比？？）
2.受理登记--》受理登记查询--》个人修改页面 企业页面 小微  “带动就业人员信息”  “新招符合条件职工”    e-table 还要优化一下，v-for??
3.检查所有页面，搜索是否需要更新？？（目前看搜索的字段貌似也不需要更新。。。）
4.银行还款的弹框，每次弹出时置空一下，不要默认展示上次处理的记录的数据
5.还款页面  Cannot read property 'clearFiles' of undefined






npm install moment --save


370229198002281110
17222898767

370802197210303045  朱芝英  370827198309260059 曾涛
370802197008043016  王俊增
37080219760912425X 李丁华



insert into users(username,password,PHONE,EMAIL,REG_DATE,UPDATE_DATE,admin_region,gmt_created,gmt_modified,user_type,institution,realname,idnumber,admin_region_id,institution_id) values('admin','admin','17887657898','dfsafag@qq.com',now(),now(),'市直',now(),now(),'super','担保中心','admin','370127198801271217','1',null);

insert into user_role(username,password,role,admin_region,gmt_created,gmt_modified) values('admin','admin','super_check_user|super_admin_user|super_or_bank_investi_user|bank_admin_user|bank_reception_user|system_admin|super_system_admin','市直',now(),now());




alter table users change users admin_region varchar(30) character utf8;
alter table score change score score varchar(50) character utf8;



mysqldump -uroot -pQAZwsx123!@# -d RestServiceDB >db.sql;


Object doesn't support property or method 'from'
Object doesn't support property or method 'entries'
Syntax error in regular expression
RegExp.prototype.toString: 'this' is not a RegExp object
Object doesn't support property or method 'startsWith'

Object.keys: argument is not an Object
Unable to get property 'resolve' of undefined or null reference
Object doesn't support property or method 'startsWith'



modal-append-to-body="false"


listQuery: {
        page: 1,
        limit: 20,
        title: undefined,
        type: undefined,
        // traceApplyPerson: store.getters.name,
        applyName: undefined,
        applyPersonId:null,
		applyGender:null,
		companyName:null,
		companyRegion:null,
		recordType:null,
		isRefundCompleted:null,
		isAlreadyOverDue:null,
		isBadRecords:null,
		queryStartApplyDate:null,
		queryStartBankSettleDate:null,
		queryEndBankSettleDate:null,
		queryStartTraceLegitDate:null,
		queryEndTraceLegitDate:null,
        adminRegion: undefined,
        queryEndApplyDate:null,
        loanBank:null
      },
	  
	  
mysql> desc compensation_fund;
+-----------------+-------------+------+-----+---------+----------------+
| Field           | Type        | Null | Key | Default | Extra          |
+-----------------+-------------+------+-----+---------+----------------+
| fund_id         | bigint(20)  | NO   | PRI | NULL    | auto_increment |
| total           | int(11)     | YES  |     | NULL    |                |
| remain          | int(11)     | YES  |     | NULL    |                |
| admin_region    | varchar(30) | YES  |     | NULL    |                |
| gmt_created     | datetime    | YES  |     | NULL    |                |
| gmt_modified    | datetime    | YES  |     | NULL    |                |
| fund_name       | varchar(30) | YES  |     | NULL    |                |
| fund_bank       | varchar(30) | YES  |     | NULL    |                |
| admin_region_id | int(11)     | YES  |     | NULL    |                |
| institution_id  | int(11)     | YES  |     | NULL    |                |
+-----------------+-------------+------+-----+---------+----------------+


alter table compensation_fund add column lixi int(11),add column fengxianbuchang int(11);
alter table loan_transaction add column overdue_lixi int(11);

mysql> desc compensation_fund;
+-----------------+-------------+------+-----+---------+----------------+
| Field           | Type        | Null | Key | Default | Extra          |
+-----------------+-------------+------+-----+---------+----------------+
| fund_id         | bigint(20)  | NO   | PRI | NULL    | auto_increment |
| total           | int(11)     | YES  |     | NULL    |                |
| remain          | int(11)     | YES  |     | NULL    |                |
| admin_region    | varchar(30) | YES  |     | NULL    |                |
| gmt_created     | datetime    | YES  |     | NULL    |                |
| gmt_modified    | datetime    | YES  |     | NULL    |                |
| fund_name       | varchar(30) | YES  |     | NULL    |                |
| fund_bank       | varchar(30) | YES  |     | NULL    |                |
| admin_region_id | int(11)     | YES  |     | NULL    |                |
| institution_id  | int(11)     | YES  |     | NULL    |                |
| lixi            | int(11)     | YES  |     | NULL    |                |
| fengxianbuchang | int(11)     | YES  |     | NULL    |                |
+-----------------+-------------+------+-----+---------+----------------+


create table compensation_fund_log (tid bigint(20) PRIMARY KEY AUTO_INCREMENT, fund_id bigint(20), loan_id_show varchar(50), amount int(11), details longtext, updatedate datetime);

alter table compensation_fund_log drop primary key;
alter table compensation_fund_log add primary key(fund_id,loan_id_show,updatedate);
alter table compensation_fund_log modify details longtext;

