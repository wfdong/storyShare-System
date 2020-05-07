exports.notEmpty = name => {
  return v => {
    if (!v || v.trim === '') {
      //return `${name} is required`
      return '该字段不能为空'
    } else {
      return true
    }
  }
}
