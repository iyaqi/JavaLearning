package mybatis.mapper;

import mybatis.Domain.*;

public interface BlogMapper {
    int deleteByPrimaryKey(Integer bid);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectBlogById(Integer bid);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);
}