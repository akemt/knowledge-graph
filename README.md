algoplat-web
开发注意事项

1.**所有service，controller方法必须有test方法**。
2.**判断字符串或者数组或对象等不得用==null ，到common中查看，用Assert.isEmpty() 方法**
3.**service每个接口都要有注释**
4.**service层抛出异常，controller拦截异常，定义提示信息到properties文件中**
5.**关联查询产生多字段结果时，创建VO结果集，extends model类**