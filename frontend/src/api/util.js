

export function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
      return (arr[2]);
    else
      return null;
  }
  
  
  export function setCookie(c_name, value, expiredays) {
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + expiredays);
    document.cookie = c_name + "=" + escape(value) + ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString());
  };
  
  
  export function delCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = getCookie(name);
    if (cval != null)
     document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
  };


export function calcSpiltTotalPerson(totalMoney, totalInterest, isShengGuanXian) {
    let obj = {};
    let money = {};
    let interest = {};
    let ratio = {};
    let provinceInteRatio = 0.3;
    let cityInteRatio =0.4;
    if(isShengGuanXian === "1") {
        provinceInteRatio = 0.4;
        cityInteRatio = 0.3;
    }
    console.log("1,2,3", isShengGuanXian, provinceInteRatio, cityInteRatio)
    if (totalMoney <= 150000) {
        money.nation = totalMoney;
        money.province = 0;
        money.city = 0;

        //TODO 遗留一个看不清楚的
        interest.nationInterest = totalInterest * 0.3;
        interest.provinceInterest = totalInterest * provinceInteRatio;
        interest.cityInterest = totalInterest * cityInteRatio;

        ratio.nationRatio = 30;
        ratio.provinceRatio = 30;
        ratio.cityRatio = 40;

        obj.money = money;
        obj.interest = interest;
        obj.ratio = ratio;

        return obj;
    }

    money.nation = 150000;
    money.province = 0;
    money.city = totalMoney - 150000;

    let t15Total = 150000 * totalInterest;

    interest.nationInterest = t15Total * 0.3 / totalMoney;
    interest.provinceInterest = t15Total * provinceInteRatio / totalMoney;
    interest.cityInterest = t15Total * cityInteRatio / totalMoney + (totalMoney - 150000) * 1.0 * totalInterest / totalMoney;

    ratio.nationRatio = 30;
    ratio.provinceRatio = 30;
    ratio.cityRatio = 40;

    obj.money = money;
    obj.interest = interest;
    obj.ratio = ratio;
    return obj;
}

export function calcSpiltTotalCompany(totalMoney, totalInterest, isShengGuanXian) {
    let obj = {};
    let money = {};
    let interest = {};
    let ratio = {};
    let provinceInteRatio = 0.3;
    let cityInteRatio =0.4;
    if(isShengGuanXian === "1") {
        provinceInteRatio = 0.4;
        cityInteRatio = 0.3;
    }
    if (totalMoney <= 150000) {
        money.nation = totalMoney;
        money.province = 0;
        money.city = 0;

        interest.nationInterest = totalInterest * 0.3;
        interest.provinceInterest = totalInterest * provinceInteRatio;
        interest.cityInterest = totalInterest * cityInteRatio;

        ratio.nationRatio = 30;
        ratio.provinceRatio = 30;
        ratio.cityRatio = 40;

        obj.money = money;
        obj.interest = interest;
        obj.ratio = ratio;
        return obj;
    }

    if (totalMoney <= 450000) {
        money.nation = 150000;
        money.province = totalMoney - 150000;
        money.city = 0;

        let t15Total = 150000 * totalInterest;
        interest.nationInterest = t15Total * 0.3 / totalMoney;
        interest.provinceInterest = t15Total * provinceInteRatio / totalMoney + (totalMoney - 150000) * 1.0 * totalInterest / totalMoney;
        interest.cityInterest = t15Total * cityInteRatio / totalMoney;

        ratio.nationRatio = 30;
        ratio.provinceRatio = 30;
        ratio.cityRatio = 40;

        obj.money = money;
        obj.interest = interest;
        obj.ratio = ratio;
        return obj;
    }

    money.nation = 150000;
    money.province = 300000;
    money.city = totalMoney - 450000;

    let t15Total = 150000 * totalInterest;
    interest.nationInterest = t15Total * 0.3 / totalMoney;
    interest.provinceInterest = t15Total * provinceInteRatio / totalMoney + 300000 * totalInterest * 1.0 / totalMoney;
    interest.cityInterest = t15Total * cityInteRatio / totalMoney + (totalMoney - 450000) * 1.0 * totalInterest / totalMoney;

    ratio.nationRatio = 30;
    ratio.provinceRatio = 30;
    ratio.cityRatio = 40;

    obj.money = money;
    obj.interest = interest;
    obj.ratio = ratio;
    return obj;
}


export function calcSpiltTotalMicro(totalMoney, totalInterest, isShengGuanXian) {
    let obj = {};
    let money = {};
    let interest = {};
    let ratio = {};
    let provinceInteRatio = 0.3;
    let cityInteRatio =0.4;
    if(isShengGuanXian === "1") {
        provinceInteRatio = 0.4;
        cityInteRatio = 0.3;
    }
    if (totalMoney <= 300 * 10000) {
        money.nation = totalMoney;
        money.province = 0;
        money.city = 0;

        interest.nationInterest = totalInterest * 0.3;
        interest.provinceInterest = totalInterest * provinceInteRatio;
        interest.cityInterest = totalInterest * cityInteRatio;

        ratio.nationRatio = 30;
        ratio.provinceRatio = 30;
        ratio.cityRatio = 40;

        obj.money = money;
        obj.interest = interest;
        obj.ratio = ratio;
        return obj;
    }

    money.nation = 300 * 10000;
    money.province = 0;
    money.city = totalMoney - money.nation;

    let t300Total = 3000000 * totalInterest;
    interest.nation = t300Total * 0.3 / totalMoney;
    interest.province = t300Total * provinceInteRatio / totalMoney;
    interest.city = t300Total * cityInteRatio / totalMoney + (totalMoney - 3000000) * 1.0 * totalInterest / totalMoney;;

    ratio.nationRatio = 30;
    ratio.provinceRatio = 30;
    ratio.cityRatio = 40;

    obj.money = money;
    obj.interest = interest;
    obj.ratio = ratio;

    return obj;

}

/**
     * 计算承担金额
     */
    export function calcSpiltTotal(recordType, totalMoney, totalInterest, isShengGuanXian) {
        console.log("calcSpiltTotal:", recordType, totalMoney, totalInterest, isShengGuanXian)
        if (recordType == 0) {
            return calcSpiltTotalPerson(totalMoney, totalInterest, isShengGuanXian);
        }
    
        if (recordType == 1) {
            return calcSpiltTotalCompany(totalMoney, totalInterest, isShengGuanXian);
        }
    
        if (recordType == 2) {
            return calcSpiltTotalMicro(totalMoney, totalInterest, isShengGuanXian);
        }
    }