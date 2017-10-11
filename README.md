algoplat-web
开发注意事项

1.所有service，controller方法必须有test方法。
2.判断字符串或者数组或对象等不得用==null ，到common中查看，用Assert.isEmpty() 方法。