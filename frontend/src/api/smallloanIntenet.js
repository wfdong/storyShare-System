import request from '@/utils/requestInternet'

export function getStepName(stepNum) {
  switch(stepNum) {
    case "2":
      return "已提交，未审核"

    case "3":
      return "审核通过"
      
    case 4:
      return "审核未通过"

    case 5:
      return "待分配调查人员"

    case "6":
      return "待联合会审"

    default:
      return "状态错误：" + stepNum
  }
}

//台账审核
export function listStandingBook(data) {
  return request({
    url: '/user/listpersonalloaninfo',
    method: 'post',
    params:data,
    // data
  })
}

//申请记录查询
export function listStandingBookMyself(data) {
  return request({
    //url: '/user/listmyselfrecord',
    url: '/user/listpersonalloaninfo',
    method: 'post',
    params: data
  })
}

//listpersonalloaninfoExport
export function listpersonalloaninfoExport(data) {
  return request({
    //url: '/user/listmyselfrecord',
    url: '/user/listpersonalloaninfoExport',
    method: 'post',
    params: data
  })
}

export function listpersonalloaninfoExportExcel(data) {
  return request({
    //url: '/user/listmyselfrecord',
    url: '/user/listpersonalloaninfoExportExcel',
    method: 'get',
    params: data
  })
}

//3/4 审核通过/失败
export function checkingpassorrefus(data) {
  return request({
    url: '/user/checkingpassorrefuse',
    method: 'post',
    data
  })
}

// 重新提交申请，更改状态到1
export function resubmitloan(data) {
  return request({
    url: '/user/resubmitloan',
    method: 'post',
    data
  })
}

//5 分配任务
export function allocateinvestigatetask(data) {
  return request({
    url: '/user/allocateinvestigatetask',
    method: 'post',
    data
  })
}

//6 提交考察信息接口
export function commitinvestigateinfo(data) {
  return request({
    url: '/user/commitinvestigateinfo',
    method: 'post',
    data
  })
}

//9_1 提交考察信息接口
export function commitinvestigateinfoafterloan(data) {
  return request({
    url: '/user/investigateInfoafterLoan',
    method: 'post',
    data
  })
}

//个人贷后调查历史列表
export function listInvestigationHistory(data) {
  return request({
    url: '/user/queryreviewlist',
    method: 'post',
    data
  })
}


//7 提交联合会审信息
export function commitjointreviewinfo(data) {
  return request({
    url: '/user/commitjointreviewinfo',
    method: 'post',
    data
  })
}

//9 提交银行放款确认信息
export function commitbankresult(data) {
  return request({
    url: '/user/commitbankresult',
    method: 'post',
    data
  })
}

//10 提交正常还款信息
export function commitnormalrefund(data) {
  return request({
    url: '/user/commitnormalrefund',
    method: 'post',
    data
  })
}

//10_1 提交正常还款信息+展期
export function commitnormalrefundandextend(data) {
  return request({
    url: '/user/commitnormalrefundandextend',
    method: 'post',
    data
  })
}

//11 提交贴息信息
export function commitsubsidy(data) {
  return request({
    url: '/user/commitsubsidy',
    method: 'post',
    data
  })
}

//12 提交逾期信息
export function commitoverdue(data) {
  return request({
    url: '/user/commitoverdue',
    method: 'post',
    data
  })
}

//12_1 查询所有逾期人员
export function listalloverdueinfo(data) {
  return request({
    url: '/user/listalloverdueinfo',
    method: 'post',
    data
  })
}

//13 逾期还款
export function commitoverduerefund(data) {
  return request({
    url: '/user/commitoverduerefund',
    method: 'post',
    data
  })
}

//13 部分逾期还款
export function commitpartoverduerefund(data) {
  return request({
    url: '/user/commitpartoverduerefund',
    method: 'post',
    data
  })
}

//14_1 查询黑名单列表
export function listblacklistpeople(data) {
  return request({
    url: '/user/listblacklistpeople',
    method: 'get',
    data
  })
}

//14_2 加入黑名单
export function commitblacklist(data) {
  return request({
    url: '/user/commitblacklist',
    method: 'post',
    data
  })
}

//15 基金代偿
export function commitcompensate(data) {
  return request({
    url: '/user/commitcompensate',
    method: 'post',
    data
  })
}

//15_1 查询已基金代偿列表
export function listcompensatedpeople(data) {
  return request({
    url: '/user/listcompensatedpeople',
    method: 'get',
    data
  })
}

//16 司法追缴
export function commitlegis(data) {
  return request({
    url: '/user/commitlegis',
    method: 'post',
    data
  })
}

//16_1 部分司法追缴
export function commitlegispart(data) {
  return request({
    url: '/user/commitlegispart',
    method: 'post',
    data
  })
}

//17 查询基金列表 
export function listfund(data) {
  return request({
    url: '/user/listfund',
    method: 'get',
    params:data
  })
}

//新增基金
export function addFund(data) {
  return request({
    url: '/user/addfund',
    method: 'post',
    data
  })
}

//查询单个申贷信息
export function singlepersonalloaninfo(data) {
  return request({
    url: '/user/singlepersonalloaninfo',
    method: 'post',
    data
  })
}

//查询单个贷后调查信息
export function singlepersonalafterloaninfo(data) {
  return request({
    url: '/user/singlepersonalafterloaninfo',
    method: 'post',
    data
  })
}

//贷款申请
export function personApply(data) {
  return request({
    url: '/user/addpersonalloaninfo',
    method: 'post',
    data
  })
}


export function personApplyInternet(data) {
  return request({
    url: '/user/addpersonalloaninfoInternet',
    method: 'post',
    data
  })
}

//用户管理
export function addSystemUser(data) {
  return request({
    url: '/user/adduser',
    method: 'post',
    data
  })
}

//用户管理
export function listSystemUser(data) {
  return request({
    url: '/user/list',
    method: 'get',
    params: data
  })
}
export function modifyuser(data) {
  return request({
    url: '/user/modifyuser',
    method: 'post',
    data
  })
}
export function deleteuser(data) {
  return request({
    url: '/user/deleteuser',
    method: 'post',
    data
  })
}

export function canceluser(data) {
  return request({
    url: '/user/canceluser',
    method: 'post',
    data
  })
}

//黑名单列表
export function listBlacklist(data) {
  return request({
    url: '/user/blacklist',
    method: 'get',
    params:data
  })
}
//删除黑名单
export function deleteBlacklist(data) {
  return request({
    url: '/user/deleteblacklist',
    method: 'post',
    data
  })
}

//删除基金
export function deleteFund(data) {
  return request({
    url: '/user/deletefund',
    method: 'post',
    data
  })
}

//列出用户所在行政区域的基金
export function listUserRegionFund(data) {
  return request({
    url: '/user/listuserregionfund',
    method: 'post',
    data
  })
}


//list investigationinfo
export function getinvestigationinfo(data) {
  return request({
    url: '/user/getinvestigationinfo',
    method: 'post',
    data
  })
}

//区域管理
export function listRegion(data) {
  return request({
    url: '/region/list',
    method: 'get',
    // data
  })
}

//区域管理
export function listWithUserRegion(data) {
  return request({
    url: '/region/listWithUserRegion',
    method: 'get',
    // data
  })
}

//区域管理
export function listWithUserRegionInternet(data) {
  return request({
    url: '/region/listWithUserRegionInternet',
    method: 'get',
    // data
  })
}

//区域管理
export function addRegion(data) {
  return request({
    url: '/region/insert',
    method: 'post',
    data
  })
}

//区域管理
export function updateRegion(data) {
  return request({
    url: '/region/update',
    method: 'post',
    data
  })
}

//区域管理
export function deleteRegion(data) {
  return request({
    url: '/region/delete',
    method: 'post',
    data
  })
}

//机构管理
export function listInstitution(data) {
  return request({
    url: '/institution/list',
    method: 'post',
    data
  })
}
export function listInstitutionlistforuseradmin(data) {
  return request({
    url: '/institution/listforuseradmin',
    method: 'post',
    data
  })
}
export function listInstitutionInternet(data) {
  return request({
    url: '/institution/listinternet',
    method: 'post',
    data
  })
}

//机构管理
export function addInstitution(data) {
  return request({
    url: '/institution/insert',
    method: 'post',
    data
  })
}

//机构管理
export function updateInstitution(data) {
  return request({
    url: '/institution/update',
    method: 'post',
    data
  })
}

//机构管理
export function deleteInstitution(data) {
  return request({
    url: '/institution/delete',
    method: 'post',
    data
  })
}

//网点管理
export function listAgency(data) {
  return request({
    url: '/agency/list',
    method: 'post',
    data
  })
}

//机构管理
export function addAgency(data) {
  return request({
    url: '/agency/insert',
    method: 'post',
    data
  })
}

//机构管理
export function updateAgency(data) {
  return request({
    url: '/agency/update',
    method: 'post',
    data
  })
}

//机构管理
export function deleteAgency(data) {
  return request({
    url: '/agency/delete',
    method: 'post',
    data
  })
}

//统计查询，根据region分类
export function listAmountByRegion(data) {
  return request({
    url: '/statistic/listAmountByRegion',
    method: 'get',
    params:data,
    // data
  })
}

//统计查询，根据region和type分类
export function listAmountByRegionAndType(data) {
  return request({
    url: '/statistic/listAmountByRegionAndType',
    method: 'get',
    params:data,
    // data
  })
}

//统计查询，根据region分类
export function dynamicList(data) {
  return request({
    url: '/statistic/dynamicList',
    method: 'get',
    params:data,
    // data
  })
}

//政策管理
export function listArticle(data) {
  return request({
    url: '/article/list',
    method: 'get',
    params:data
  })
}

//政策管理
export function addArticle(data) {
  return request({
    url: '/article/insert',
    method: 'post',
    data
  })
}

//政策管理
export function updateArticle(data) {
  return request({
    url: '/article/update',
    method: 'post',
    data
  })
}

//政策管理
export function deleteArticle(data) {
  return request({
    url: '/article/delete',
    method: 'post',
    data
  })
}

//统计查询，根据region分类
export function uploadFileBase64(data) {
  return request({
    url: '/rest/v1/file/base64',
    method: 'post',
    // params:data,
    data
  })
}