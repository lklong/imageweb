package com.zhiguw.file.mapper;

import com.zhiguw.file.entity.ZhiguFile;

/**
 * @ClassName: ZhiguFileMapper.java
 * @Author: liukailong
 * @Description: 文件 上传数据层接口
 * @Date: 2015年4月7日
 * 
 */
public interface ZhiguFileMapper {

	/**
	 * 添加数据到数据库
	 * @param record
	 * @return
	 */
	int add(ZhiguFile record);

	int delete(Long id);

	ZhiguFile selectById(Long id);

	int update(ZhiguFile record);

}