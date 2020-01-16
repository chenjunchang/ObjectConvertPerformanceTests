# Object Convert Performance Tests

### 使用微基准测试（JMH）对比以下几种对象转换的性能差异
Jmapper  
MapStruct  
Spring BeanCopier  
Spring BeanUtils  
Orika  
Fastjson 序列化反序列化  
Gson 序列化反序列化  
Jackson 序列化反序列化  
ModelMapper  
Dozer  

### 运行

 To run the performance benchmarks:

1: `mvn clean install`   
2: `java -jar target/benchmarks.jar`

### 结果

JMH version: 1.22  
VM version: JDK 1.8.0_231, Java HotSpot(TM) 64-Bit Server VM, 25.231-b11  
OS: MacOs 10.15.2  
HW: 2.2 GHz 六核Intel Core i7; 16 GB 2400 MHz DDR4   
在该环境下测试排名:  
1.Jmapper  
2.MapStruct  
3.Orika  
4.Spring BeanCopier  
5.Spring BeanUtils  
6.Fastjson 序列化反序列化  
8.Jackson 序列化反序列化  
7.Gson 序列化反序列化  
9.ModelMapper  
10.Dozer  
![image](https://github.com/HolidieJacqueline/ObjectConvertPerformanceTests/blob/master/image/20200116185208_java8.jpg)  
#  
JMH version: 1.22  
VM version: JDK 11.0.5, Java HotSpot(TM) 64-Bit Server VM, 11.0.5+10-LTS  
OS: MacOs 10.15.2  
HW: 2.2 GHz 六核Intel Core i7; 16 GB 2400 MHz DDR4   
在该环境下测试排名:  
1.MapStruct  
2.Jmapper  
3.Spring BeanCopier  
4.Orika  
5.Spring BeanUtils  
6.Fastjson 序列化反序列化  
8.Jackson 序列化反序列化  
7.Gson 序列化反序列化  
9.ModelMapper  
10.Dozer  
![image](https://github.com/HolidieJacqueline/ObjectConvertPerformanceTests/blob/master/image/20200116184722_java11.jpg)


