package com.how2java.mapper;
 
import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.how2java.CategoryDynaSqlProvider;
import com.how2java.pojo.Category;
 
public interface CategoryMapper {
 
    @InsertProvider(type=CategoryDynaSqlProvider.class,method="add")  
    public int add(Category category);  
       
	@DeleteProvider(type=CategoryDynaSqlProvider.class,method="delete")
    public void delete(int id);  
       
    @SelectProvider(type=CategoryDynaSqlProvider.class,method="get")  
    public Category get(int id);  
     
    @UpdateProvider(type=CategoryDynaSqlProvider.class,method="update")  
    public int update(Category category);   
        
    @Select(" select * from category_ ")
    @Results({ 
                @Result(property = "id", column = "id"),
                @Result(property = "products", javaType = List.class, column = "id", many = @Many(select = "com.how2java.mapper.ProductMapper.listByCategory") )
            })
    public List<Category> list();  
    
    @Select(" select * from category_ limit #{start},#{count}")
    public List<Category> listByPage(@Param("start") int start, @Param("count")int count);
}
