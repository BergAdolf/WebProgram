package com.how2java;
   
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.mapper.CategoryMapper;
import com.how2java.mapper.OrderMapper;
import com.how2java.mapper.ProductMapper;
import com.how2java.pojo.Category;
import com.how2java.pojo.Order;
import com.how2java.pojo.OrderItem;
import com.how2java.pojo.Product;
   
public class TestMybatis {
   
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session1 = sqlSessionFactory.openSession();
        
        Category c1 = session1.selectOne("getCategory", 1);
        System.out.println(c1);
        Category c2 = session1.selectOne("getCategory", 1);
        System.out.println(c2);
 
        session1.commit();
        session1.close();
         
        SqlSession session2 = sqlSessionFactory.openSession();
        Category c3 = session2.selectOne("getCategory", 1);
        System.out.println(c3);    
        session2.commit();
        session2.close();
    }
    private static void xmlWay(SqlSession session) {
        Map<String,Object> params = new HashMap<>();
        params.put("start", 0);
        params.put("count", 5);
        List<Category>  cs =session.selectList("listCategory", params);
        for (Category c : cs) {
            System.out.println(c);
        }
    }
    private static void annotationWay(SqlSession session) {
        CategoryMapper mapper = session.getMapper(CategoryMapper.class);
         
        List<Category>  cs =mapper.listByPage(0, 5);
        for (Category c : cs) {
            System.out.println(c);
        }
    }
    private static void update(CategoryMapper mapper) {
        Category c= mapper.get(8);
        c.setName("修改了的Category名稱");
        mapper.update(c);
        listAll(mapper);
    }
  
    private static void get(CategoryMapper mapper) {
        Category c= mapper.get(8);
        System.out.println(c.getName());
    }
  
    private static void delete(CategoryMapper mapper) {
        mapper.delete(2);
        listAll(mapper);
    }
  
    private static void add(CategoryMapper mapper) {
        Category c = new Category();
        c.setName("新增加的Category");
        mapper.add(c);
        listAll(mapper);
    }
   
    private static void listAll(CategoryMapper mapper) {
        List<Category> cs = mapper.list();
        for (Category c : cs) {
            System.out.println(c.getName());
        }
    }
    
    private static void listOrder(SqlSession session) {
        OrderMapper mapper =session.getMapper(OrderMapper.class);
        List<Order> os = mapper.list();
        for (Order o : os) {
            System.out.println(o.getCode());
            List<OrderItem> ois= o.getOrderItems();
            if(null!=ois){
                for (OrderItem oi : ois) {
                    System.out.format("\t%s\t%f\t%d%n", oi.getProduct().getName(),oi.getProduct().getPrice(),oi.getNumber());
                }              
            }
 
        }
    }
}